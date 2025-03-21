package com.het.demotest.e100.zzcryfgzc;

import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s1 = sc.next();
        String s2 = sc.next();
        int k = sc.nextInt();
        System.out.println(findLeftmostCover(s1, s2, k));
    }

    public static int findLeftmostCover(String s1, String s2, int k) {
        int n1 = s1.length(); // s1的长度
        int n2 = s2.length(); // s2的长度
        int targetLength = n1 + k; // 滑动窗口的长度

// 如果s2长度不够形成这样一个子串，直接返回-1
        if (n2 < targetLength) {
            return -1;
        }

// 统计s1中的字符出现频率
        Map<Character, Integer> s1Count = new HashMap<>();
        for (char c : s1.toCharArray()) {
            s1Count.put(c, s1Count.getOrDefault(c, 0) + 1);
        }

// 维护窗口中的字符频率
        Map<Character, Integer> windowCount = new HashMap<>();

// 初始化滑动窗口，遍历s2的前targetLength个字符
        for (int i = 0; i < targetLength; i++) {
            char c = s2.charAt(i);
            windowCount.put(c, windowCount.getOrDefault(c, 0) + 1);
        }

// 检查最初的窗口是否满足条件
        if (isValid(s1Count, windowCount)) {
            return 0;
        }

// 滑动窗口开始从左到右移动
        for (int i = targetLength; i < n2; i++) {

// 移除窗口最左侧字符
            char leftChar = s2.charAt(i - targetLength);
            if (windowCount.get(leftChar) == 1) {
                windowCount.remove(leftChar);
            } else {
                windowCount.put(leftChar, windowCount.get(leftChar) - 1);
            }

// 增加窗口最右侧字符
            char rightChar = s2.charAt(i);
            windowCount.put(rightChar, windowCount.getOrDefault(rightChar, 0) + 1);

// 检查当前窗口是否满足条件
            if (isValid(s1Count, windowCount)) {
                return i - targetLength + 1;
            }
        }

// 如果没有符合条件的子串，返回-1
        return -1;
    }

    // 辅助函数，判断窗口是否满足条件
    private static boolean isValid(Map<Character, Integer> s1Count, Map<Character, Integer> windowCount) {
        for (Map.Entry<Character, Integer> entry : s1Count.entrySet()) {
            char key = entry.getKey();
            int count = entry.getValue();
            if (windowCount.getOrDefault(key, 0) < count) {
                return false;
            }
        }
        return true;
    }
}