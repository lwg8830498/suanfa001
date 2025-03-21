package com.het.demotest.e100.箱子之字形摆放;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] a = new char[1005][1005];
        for (int i = 0; i < 1005; i++) {
            for (int j = 0; j < 1005; j++) {
                a[i][j] = ' ';
            }
        }

        String s = scanner.next();
        int m = s.length();
        int n = scanner.nextInt();
        int x = 1, y = 1, idx = 0, f = 1;  // (x,y)表示空地坐标，初始是左上角，idx用于获取箱子号，f表示行走方向
        while (idx < m) {
            a[x][y] = s.charAt(idx++);  // 填值
            x += f;                     // 上下走，f=1往下，f=-1往上
            if (x < 1 || x > n) {       // 出界了
                y++;                    // 往右走
                x -= f;                 // 上下的退一步
                f = -f;                 // 变方向
            }
        }

        for (int i = 1; i <= n; i++) {
            if (a[i][1] == ' ') break; // 多余的空格不输出
            for (int j = 1; j <= (m + n - 1) / n; j++) {
                System.out.print(a[i][j]);
            }
            System.out.println();
        }

        scanner.close();
    }
}