package com.het.demotest.e100.玩牌高手;

import java.util.*;

public class Main {
    // 把逗号分隔的字符串转换成整数数组，比如 "1,2,3" -> [1, 2, 3]
    public static int[] parseInput(String input) {
        String[] tokens = input.split(",");  // 分割字符串
        int[] scores = new int[tokens.length];  // 存储整数分数

        for (int i = 0; i < tokens.length; i++) {
            scores[i] = Integer.parseInt(tokens[i]);  // 转换为整数
        }
        return scores;
    }

    // 计算选手在所有轮次结束后能得到的最高分
    public static int maxScore(int[] scores) {
        int n = scores.length;  // 总轮次数
        int[] dp = new int[n];  // 存储每一轮的最高分

        for (int i = 0; i < n; i++) {
            // 如果选择当前轮的牌面分数
            int take = scores[i] + (i > 0 ? dp[i - 1] : 0);

            // 如果跳过当前轮，就重置为3轮前的分数
            int skip = (i >= 3 ? dp[i - 3] : 0);

            // 当前轮的最高分是"选择"或"跳过"中更大的那个
            dp[i] = Math.max(take, skip);
        }
        return dp[n - 1];  // 返回最后一轮的最高分
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();  // 从标准输入读取牌面分数
        int[] scores = parseInput(input);  // 转换成整数数组
        System.out.println(maxScore(scores));  // 输出最高分
    }
}