package com.het.demotest.e100.连续字母长度;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 输入字符串和 k 值
        String s = scanner.nextLine();
        int k = scanner.nextInt();

        // 用来存储每个字母的最长连续子串长度
        Map<Character, Integer> maxLengths = new HashMap<>();

        // 遍历字符串，统计每个字母的最长连续出现次数
        int count = 1;  // 记录当前字母的连续长度
        for (int i = 1; i <= s.length(); i++) {
            if (i < s.length() && s.charAt(i) == s.charAt(i - 1)) {
                count++;  // 当前字母与前一个字母相同，增加计数
            } else {
                // 检查是否需要更新该字母的最长连续子串长度
                char prevChar = s.charAt(i - 1);
                maxLengths.put(prevChar, Math.max(maxLengths.getOrDefault(prevChar, 0), count));
                count = 1;  // 重置计数
            }
        }

        // 将所有最长的连续子串长度存入一个 list
        List<Integer> lengths = new ArrayList<>(maxLengths.values());

        // 对子串长度进行排序，从大到小排序
        Collections.sort(lengths, Collections.reverseOrder());

        // 如果 k 大于可用的子串数，直接输出 -1
        if (k <=0 || k > lengths.size()) {
            System.out.println(-1);
        } else {
            // 输出第 k 长的子串长度
            System.out.println(lengths.get(k - 1));
        }

        scanner.close();
    }
}