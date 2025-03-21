package com.het.demotest.e200.园区参观路径;

import java.util.Scanner;

public class Main {
    public static long uniquePaths(int m, int n, int[][] grid) {
        if (grid[0][0] == 1 || grid[m-1][n-1] == 1) {
            return 0; // 起点或终点不能参观
        }

        long[][] dp = new long[m][n];
        dp[0][0] = 1; // 起点路径数为1

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    dp[i][j] = 0; // 不能参观的园区路径数为0
                } else {
                    if (i > 0) {
                        dp[i][j] += dp[i-1][j];
                    }
                    if (j > 0) {
                        dp[i][j] += dp[i][j-1];
                    }
                }
            }
        }

        return dp[m-1][n-1];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        int[][] grid = new int[m][n];

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                grid[i][j] = scanner.nextInt();
            }
        }

        scanner.close();
        System.out.println(uniquePaths(m, n, grid));
    }
}