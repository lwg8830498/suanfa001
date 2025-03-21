package com.het.demotest.e200.不含101的数;

import java.util.Scanner;

public class Main {
    static int[][] f = new int[35][4];

    static int query(int r) {
        int res = 0;
        // 从高位到低位处理
        for (int i = 31; i >= 0; i--) {
            int u = (r >> i) & 1;
            if (u == 1) {
                res += f[i][(r >> i) & 2];
            }
            if (((r >> i) & 7) == 5) {
                return res;
            }
        }
        return res + 1;
    }

    public static void main(String[] args) {
        // 初始化f数组
        for (int i = 0; i < 4; i++) {
            f[0][i] = 1;
        }

        // 动态规划填表
        for (int i = 1; i < 32; i++) {
            for (int j = 0; j < 4; j++) {
                // 添加0的情况
                f[i][j] = f[i-1][(j&1)<<1];
                // 添加1的情况，但要避免形成101
                if (((j << 1) | 1) != 5) {
                    f[i][j] += f[i-1][((j&1)<<1)|1];
                }
            }
        }

        // 读取输入并计算结果
        Scanner scanner = new Scanner(System.in);
        int l = scanner.nextInt();
        int r = scanner.nextInt();
        System.out.println(query(r) - query(l - 1));
        scanner.close();
    }
}