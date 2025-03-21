package com.het.demotest.e200.两个字符串间的最短路径问题;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] strArray = scanner.nextLine().split(" ");

        String s1 = "o" + strArray[0];
        String s2 = "o" + strArray[1];

        char[] arr1 = s1.toCharArray();
        char[] arr2 = s2.toCharArray();

        int m = arr1.length;
        int n = arr2.length;

        boolean[][] used = new boolean[m][n];
        int[][] dp = new int[m][n];
        for (int i = 1; i < n; i++) {
            dp[0][i] = i;
        }
        for (int i = 0; i < m; i++) {
            dp[i][0] = i;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if(arr1[i] == arr2[j]) {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i- 1 ][j - 1]) + 1;
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
                }
            }
        }
        System.out.println(dp[m - 1][n - 1]);
    }
}