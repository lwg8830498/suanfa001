package com.het.demotest.e100.战场索敌;

import java.util.*;

public class Main {
    static int n, m, k;
    static char[][] battlefield;
    static boolean[][] visited;

    // 定义方向数组，表示上下左右四个方向
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    // BFS，用于遍历整个区域
    static int bfs(int startX, int startY) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startX, startY});
        visited[startX][startY] = true;
        int enemyCount = 0;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            // 如果当前格子是敌人'E'，计数+1
            if (battlefield[x][y] == 'E') {
                enemyCount++;
            }

            // 遍历四个方向
            for (int i = 0; i < 4; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];

                // 判断新坐标是否在地图内，且是否未访问过，且不是墙壁
                if (newX >= 0 && newX < n && newY >= 0 && newY < m && !visited[newX][newY] && battlefield[newX][newY] != '#') {
                    visited[newX][newY] = true;
                    queue.add(new int[]{newX, newY});
                }
            }
        }

        return enemyCount;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();
        k = scanner.nextInt();
        scanner.nextLine();

        battlefield = new char[n][m];
        visited = new boolean[n][m];

        // 输入地图
        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            for (int j = 0; j < m; j++) {
                battlefield[i][j] = line.charAt(j);
            }
        }

        int count = 0;

        // 遍历每个格子，找到未访问的空地区域
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && battlefield[i][j] != '#') {
                    int enemyCount = bfs(i, j);
                    if (enemyCount < k) {
                        count++;
                    }
                }
            }
        }

        // 输出符合条件的区域数量
        System.out.println(count);
    }
}