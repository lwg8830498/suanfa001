package com.het.demotest.e100.寻找最大价值的矿堆;

import java.util.*;

public class Main {
    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};

    private static int bfs(int x, int y, int[][] map, boolean[][] visited) {
        int value = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int cx = cell[0], cy = cell[1];

            // 增加当前点的矿堆价值
            if (map[cx][cy] == 1) value += 1;
            else if (map[cx][cy] == 2) value += 2;

            // 扩展到四个方向
            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i], ny = cy + dy[i];
                if (nx >= 0 && nx < map.length && ny >= 0 && ny < map[0].length
                        && !visited[nx][ny] && map[nx][ny] != 0) {
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
        return value;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> lines = new ArrayList<>();

        // 读取输入
        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }

        int n = lines.size();
        int m = lines.get(0).length();
        int[][] map = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = lines.get(i).charAt(j) - '0';
            }
        }

        boolean[][] visited = new boolean[n][m];
        int max_value = 0;

        // 遍历地图查找最大矿堆的价值
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && map[i][j] != 0) {
                    max_value = Math.max(max_value, bfs(i, j, map, visited));
                }
            }
        }

        System.out.println(max_value);
    }
}