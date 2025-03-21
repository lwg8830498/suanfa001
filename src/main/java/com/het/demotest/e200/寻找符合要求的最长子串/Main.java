package com.het.demotest.e200.寻找符合要求的最长子串;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static int findLongestSubstring(char forbiddenChar, String s) {
        Map<Character, Integer> charCount = new HashMap<>(); // 记录窗口中每个字符出现的次数
        int left = 0; // 滑动窗口左指针
        int maxLength = 0; // 记录满足条件的最长子串长度

        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);

            // 跳过不允许的字符
            if (currentChar == forbiddenChar) {
                // 重置窗口，从right + 1位置开始新的窗口
                charCount.clear();
                left = right + 1;
                continue;
            }

            // 更新字符的出现次数
            charCount.put(currentChar, charCount.getOrDefault(currentChar, 0) + 1);

            // 如果某个字符的出现次数超过 2 次，则缩小窗口
            while (charCount.get(currentChar) > 2) {
                char leftChar = s.charAt(left);
                charCount.put(leftChar, charCount.get(leftChar) - 1);
                if (charCount.get(leftChar) == 0) {
                    charCount.remove(leftChar); // 如果计数为0，移除该字符
                }
                left++; // 缩小窗口
            }

            // 计算满足条件的最大长度
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 输入指定不允许的字符
        char forbiddenChar = scanner.next().charAt(0);

        // 输入字符串
        String s = scanner.next();

        // 计算并输出结果
        int result = findLongestSubstring(forbiddenChar, s);
        System.out.println(result);

        scanner.close();
    }
}