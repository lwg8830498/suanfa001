package com.het.demotest.e200.MELON的难题;

import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取输入
        int n = scanner.nextInt();
        int[] weights = new int[n];
        int totalWeight = 0;

        for (int i = 0; i < n; i++) {
            weights[i] = scanner.nextInt();
            totalWeight += weights[i];
        }

        // 如果总重量是奇数，无法平分
        if (totalWeight % 2 != 0) {
            System.out.println(-1);
            return;
        }

        int target = totalWeight / 2;

        // dp[i] 表示凑出重量 i 所需的最少雨花石数量
        int[] dp = new int[target + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0; // 凑出重量 0 需要 0 块雨花石

        // 遍历每个雨花石重量
        for (int weight : weights) {
            for (int j = target; j >= weight; j--) {
                if (dp[j - weight] != Integer.MAX_VALUE) {
                    dp[j] = Math.min(dp[j], dp[j - weight] + 1);
                }
            }
        }

        // 如果 dp[target] 仍然是 Integer.MAX_VALUE，说明无法平分
        if (dp[target] == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(dp[target]); // 输出最少需要的雨花石数量
        }

        scanner.close();
    }
}