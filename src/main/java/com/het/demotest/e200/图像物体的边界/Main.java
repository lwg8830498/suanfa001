package com.het.demotest.e200.图像物体的边界;

import java.util.Scanner;

public class Main {
    // 定义二维数组的最大大小
    static final int MAX_M = 110;
    static final int MAX_N = 100;

    // 是否为边界的标记数组
    static boolean[][] wall = new boolean[MAX_M][MAX_N];
    // 输入数组，存储像素值
    static int[][] a = new int[MAX_M][MAX_N];
    // 是否已访问的标记数组
    static boolean[][] vist = new boolean[MAX_M][MAX_N];
    // 方向数组，表示八个方向（上下左右及对角线）
    static int[] dx = {0, 1, 0, -1, 1, 1, -1, -1};
    static int[] dy = {1, 0, -1, 0, 1, -1, 1, -1};

    // 行数和列数
    static int n, m;

    // 深度优先搜索（DFS）函数
    public static void dfs(int x, int y) {
        vist[x][y] = true;
        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            // 检查新坐标是否在范围内，是否为边界，且未被访问过
            if (nx >= 0 && nx < n && ny >= 0 && ny < m && wall[nx][ny] && !vist[nx][ny]) {
                dfs(nx, ny);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();

        // 读入像素值，并初始化边界
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                a[i][j] = scanner.nextInt();

                if (a[i][j] == 5) {
                    wall[i][j] = false;
                    for (int k = 0; k < 8; k++) {
                        int x = i + dx[k];
                        int y = j + dy[k];
                        // 如果相邻位置在数组范围内，且不是5，则标记为边界
                        if (x >= 0 && x < n && y >= 0 && y < m && a[x][y] != 5) {
                            wall[x][y] = true;
                        }
                    }
                }
            }
        }

        int cnt = 0;  // 边界数量计数
        // 遍历整个数组，计算边界个数
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (wall[i][j] && !vist[i][j]) {  // 如果是未访问的边界
                    cnt++;
                    dfs(i, j);  // 进行DFS，标记所有连续的边界
                }
            }
        }

        System.out.println(cnt);  // 输出边界数量
        scanner.close();
    }
}