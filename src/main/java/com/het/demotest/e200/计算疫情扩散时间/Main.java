package com.het.demotest.e200.计算疫情扩散时间;

import java.util.*;

public class Main {
    // 计算全部区域被感染所需的天数
    public static int daysToInfectAll(int[][] grid) {
        int n = grid.length;  // 地图的大小 n x n
        Queue<int[]> queue = new LinkedList<>();  // 广度优先搜索队列，用于存储当前感染区域的坐标
        int days = 0;  // 用于计数感染所需的天数
        int totalZeros = 0;  // 用于计数未感染的区域数量

        // 初始化队列并统计初始状态的未感染区域数量
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    queue.offer(new int[]{i, j});  // 将已感染的区域坐标加入队列
                } else if (grid[i][j] == 0) {
                    totalZeros++;  // 统计未感染区域数量
                }
            }
        }

        // 如果没有未感染的区域或者所有区域都已感染，直接返回 -1
        if (totalZeros == 0) {
            return -1;  // 所有区域初始时就已感染
        }
        if (queue.isEmpty()) {
            return -1;  // 没有初始感染的区域
        }

        // 用于表示四个方向的移动（上、下、左、右）
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        // 开始广度优先搜索，扩散感染
        while (!queue.isEmpty() && totalZeros > 0) {
            int currentLevelSize = queue.size();  // 当前感染区域的数量
            days++;  // 计算天数，每扩散一轮算一天

            // 处理当前所有感染区域
            for (int i = 0; i < currentLevelSize; i++) {
                int[] current = queue.poll();
                int x = current[0];
                int y = current[1];

                // 尝试扩展到四个方向
                for (int[] direction : directions) {
                    int nx = x + direction[0];
                    int ny = y + direction[1];

                    // 检查边界并且检查目标区域是否未被感染
                    if (nx >= 0 && ny >= 0 && nx < n && ny < n && grid[nx][ny] == 0) {
                        grid[nx][ny] = 1;  // 将区域标记为感染
                        totalZeros--;  // 减少未感染区域的计数
                        queue.offer(new int[]{nx, ny});  // 将新感染的区域加入队列
                    }
                }
            }
        }

        // 如果所有区域都被感染，返回天数；否则返回 -1
        return totalZeros == 0 ? days : -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        String[] cells = input.split(",");  // 根据逗号分隔输入数据
        int n = (int) Math.sqrt(cells.length);  // 计算地图大小 n x n
        int[][] grid = new int[n][n];

        // 构建地图 grid
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(cells[i * n + j]);
            }
        }

        // 调用函数计算结果并输出
        int result = daysToInfectAll(grid);
        System.out.println(result);

        scanner.close();
    }
}