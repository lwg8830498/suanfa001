package com.het.demotest.e200.亲子游戏;

import java.util.*;

public class Main {
    private static final int INF = Integer.MAX_VALUE / 2; // 定义一个大数作为无限表示
    private static final int[] dx = {1, 0, -1, 0}; // x 方向的移动数组
    private static final int[] dy = {0, 1, 0, -1}; // y 方向的移动数组

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // 读取网格的大小
        int[][] a = new int[n][n]; // 创建存储网格状态的数组
        int[][] dp = new int[n][n]; // 创建dp数组，存储到每个点的最大得分
        int[][] step = new int[n][n]; // 创建step数组，存储到每个点的最小步数

        int startX = -1, startY = -1, endX = -1, endY = -1; // 初始化起点和终点坐标

        // 读取网格信息并找出起点与终点
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = scanner.nextInt();
                if (a[i][j] == -3) {
                    startX = i;
                    startY = j;
                }
                if (a[i][j] == -2) {
                    endX = i;
                    endY = j;
                }
                step[i][j] = INF; // 初始化步数为无穷大
            }
        }

        // 使用队列实现BFS
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(startX, startY));
        step[startX][startY] = 0; // 起点步数为0

        while (!queue.isEmpty()) {
            Point current = queue.poll();
            int x = current.x;
            int y = current.y;

            // 如果当前点的步数已经大于终点的步数，则继续
            if (step[x][y] >= step[endX][endY]) {
                continue;
            }

            for (int i = 0; i < 4; i++) { // 检查四个方向
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 确保新的位置在网格内并且不是障碍物
                if (nx >= 0 && nx < n && ny >= 0 && ny < n && a[nx][ny] != -1) {
                    if (step[nx][ny] > step[x][y] + 1) {
                        queue.add(new Point(nx, ny));
                        step[nx][ny] = step[x][y] + 1;
                        dp[nx][ny] = dp[x][y] + a[nx][ny];
                    } else if (step[nx][ny] == step[x][y] + 1) {
                        dp[nx][ny] = Math.max(dp[nx][ny], dp[x][y] + a[nx][ny]);
                    }
                }
            }
        }

        // 检查是否能到达终点
        if (step[endX][endY] == INF) {
            System.out.println(-1); // 输出无法到达
        } else {
            System.out.println(dp[endX][endY] + 2); // 输出最大得分，注意加2
        }

        scanner.close();
    }

    // 辅助类，用于表示点
    static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}