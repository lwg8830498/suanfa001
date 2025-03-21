package com.het.demotest.e100.最大报酬;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取总的可用工作时间 T 和任务数量 n
        int T = scanner.nextInt();
        int n = scanner.nextInt();

        // 初始化 DP 数组，长度为 T + 1，初始值为 0
        int[] dp = new int[T + 1];

        // 遍历每个任务，读取任务所需时间 t 和报酬 w
        for (int i = 0; i < n; i++) {
            int t = scanner.nextInt();
            int w = scanner.nextInt();

            // 从后向前更新 DP 数组
            for (int j = T; j >= t; j--) {
                dp[j] = Math.max(dp[j], dp[j - t] + w);
            }
        }

        // 输出在 T 时间内能获得的最大报酬
        System.out.println(dp[T]);

        scanner.close();
    }
}