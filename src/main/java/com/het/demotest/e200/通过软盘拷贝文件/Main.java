package com.het.demotest.e200.通过软盘拷贝文件;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // 输入物品数量
        int[] arr = new int[n]; // 创建数组存储每个物品的大小
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt(); // 逐个输入物品大小
        }

        int m = 1474560 / 512; // 背包的承重（块）

        // 创建DP数组，行表示物品，列表示当前背包容量
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            int w = (arr[i - 1] + 511) / 512; // 物品的重量（块）
            int v = arr[i - 1]; // 物品的价值（字节）
            for (int j = 0; j <= m; j++) {
                dp[i][j] = dp[i - 1][j]; // 不选当前物品的情况
                if (j >= w) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - w] + v); // 选或不选当前物品，取最大值
                }
            }
        }

        // 输出最大价值
        System.out.println(dp[n][m]);
    }
}