package com.het.demotest.e100.最大矩阵和;

import java.util.Scanner;

public class Main {
    static final int N = 105;
    static int[][] sum = new int[N][N];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                int x = scanner.nextInt();
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + x;// 初始化二位前缀和
            }
        }
        int ans = Integer.MIN_VALUE;// 初始化最小值
        // (x1,y1)(x2,y2)分别表示子矩阵的左上角坐标和右下角坐标，遍历所有子矩阵
        for (int x1 = 1; x1 <= n; x1++) {
            for (int y1 = 1; y1 <= m; y1++) {
                for (int x2 = x1; x2 <= n; x2++) {// 右下角行坐标从x1开始遍历
                    for (int y2 = y1; y2 <= m; y2++) {// 右下角列坐标从y1开始遍历
                        ans = Math.max(ans, sum[x2][y2] - sum[x1 - 1][y2] - sum[x2][y1 - 1] + sum[x1 - 1][y1 - 1]);
                    }
                }
            }
        }
        System.out.println(ans);
        scanner.close();
    }
}