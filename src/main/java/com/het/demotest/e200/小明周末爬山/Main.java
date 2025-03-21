package com.het.demotest.e200.小明周末爬山;

import java.util.*;

public class Main {

    static int m, n, k;
    static int[][] grid;
    static boolean[][] visited;

    // 定义四个方向的移动
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    // BFS函数，返回能到达的最高峰和最短步数
    public static int[] bfs() {
        Queue<int[]> q = new LinkedList<>();  // 队列存储：x, y, 当前步数
        q.offer(new int[]{0, 0, 0});  // 初始位置： (0,0), 步数为0
        visited[0][0] = true;

        int max_height = 0;  // 最高山峰的高度
        int min_steps = 0;   // 到达最高山峰的最短步数

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int x = curr[0], y = curr[1], steps = curr[2];

            // 如果当前高度比已知的最大高度更大，更新
            if (grid[x][y] > max_height) {
                max_height = grid[x][y];
                min_steps = steps;
            } else if (grid[x][y] == max_height && steps < min_steps) {
                // 如果高度相同，更新最短步数
                min_steps = steps;
            }

            // 向四个方向移动
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 检查边界和是否已访问过
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && !visited[nx][ny]) {
                    // 检查高度差是否在k以内
                    if (Math.abs(grid[nx][ny] - grid[x][y]) <= k) {
                        visited[nx][ny] = true;
                        q.offer(new int[]{nx, ny, steps + 1});
                    }
                }
            }
        }

        return new int[]{max_height, min_steps};
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 输入m, n, k
        m = sc.nextInt();
        n = sc.nextInt();
        k = sc.nextInt();

        grid = new int[m][n];
        visited = new boolean[m][n];

        // 输入地图
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = sc.nextInt();
            }
        }

        // 从左上角开始广度优先搜索，获取结果
        int[] result = bfs();

        int max_height = result[0];
        int min_steps = result[1];

        // 如果从未移动，设置最高点为0
        if (min_steps == 0) {
            max_height = 0;
        }

        // 输出结果
        System.out.println(max_height + " " + min_steps);
    }
}