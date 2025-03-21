package com.het.demotest.e100.用户调度问题;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 首先读取用户数量
        int userCount = scanner.nextInt();
        // 创建一个二维数组来存储每个用户的三种策略的系统消耗
        int[][] resources = new int[userCount][3];

        // 逐行读取每个用户的三个策略消耗值
        for (int i = 0; i < userCount; i++) {
            resources[i][0] = scanner.nextInt(); // A策略的消耗
            resources[i][1] = scanner.nextInt(); // B策略的消耗
            resources[i][2] = scanner.nextInt(); // C策略的消耗
        }
        scanner.close(); // 读取完毕，关闭输入

        // 用一个二维数组来记录到达每个用户时的最小消耗
        int[][] dp = new int[userCount][3];

        // 初始化第一个用户的策略，因为这是我们的起点
        dp[0][0] = resources[0][0]; // 第一个用户选择A策略的总消耗
        dp[0][1] = resources[0][1]; // 第一个用户选择B策略的总消耗
        dp[0][2] = resources[0][2]; // 第一个用户选择C策略的总消耗

        // 从第二个用户开始，逐步计算每种策略的最小消耗
        for (int i = 1; i < userCount; i++) {
            // 如果当前用户选择A策略，那上一个用户只能选B或C，取较小的消耗
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + resources[i][0];
            // 如果当前用户选择B策略，那上一个用户只能选A或C，取较小的消耗
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + resources[i][1];
            // 如果当前用户选择C策略，那上一个用户只能选A或B，取较小的消耗
            dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + resources[i][2];
        }

        // 现在已经到达最后一个用户，我们需要从他的三个策略中选出最低消耗
        int minimumResource = Math.min(dp[userCount - 1][0],
                Math.min(dp[userCount - 1][1], dp[userCount - 1][2]));

        // 打印出最小的系统资源消耗
        System.out.println(minimumResource);
    }
}