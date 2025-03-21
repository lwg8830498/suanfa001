package com.het.demotest.e100.猜字谜;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取谜面单词列表
        String[] puzzles = scanner.nextLine().split(",");
        // 读取谜底库单词列表
        String[] answers = scanner.nextLine().split(",");

        // 存储匹配结果
        List<String> result = new ArrayList<>();

        // 遍历每个谜面单词
        for (String puzzle : puzzles) {
            boolean found = false;
            // 遍历谜底库
            for (String answer : answers) {
                if (isMatch(puzzle, answer)) {
                    result.add(answer);
                    found = true;
                }
            }
            if (!found) {
                result.add("not found");
            }
        }

        // 输出结果
        System.out.println(String.join(",", result));
    }

    // 判断谜面和谜底是否匹配
    private static boolean isMatch(String puzzle, String answer) {
        // 条件1：变换顺序后相同
        if (sortString(puzzle).equals(sortString(answer))) {
            return true;
        }
        // 条件2：去重后相同
        if (removeDuplicates(puzzle).equals(removeDuplicates(answer))) {
            return true;
        }
        return false;
    }

    // 对字符串中的字母进行排序
    private static String sortString(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    // 去除字符串中的重复字母
    private static String removeDuplicates(String s) {
        Set<Character> set = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (set.add(c)) {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}