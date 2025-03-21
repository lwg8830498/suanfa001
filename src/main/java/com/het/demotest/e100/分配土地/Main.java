package com.het.demotest.e100.分配土地;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取地图的行数和列数
        int m = scanner.nextInt();
        int n = scanner.nextInt();

        // 读取地图的具体标识
        int[][] map = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = scanner.nextInt();
            }
        }

        // 记录每个数字的位置
        Map<Integer, List<int[]>> positions = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int num = map[i][j];
                if (num != 0) {
                    positions.computeIfAbsent(num, k -> new ArrayList<>()).add(new int[]{i, j});
                }
            }
        }

        // 计算每个数字的最小包围矩阵面积
        int maxArea = 0;
        for (Map.Entry<Integer, List<int[]>> entry : positions.entrySet()) {
            List<int[]> points = entry.getValue();
            if (points.size() < 2) {
                continue; // 至少需要两个点才能形成矩阵
            }

            int minRow = Integer.MAX_VALUE;
            int maxRow = Integer.MIN_VALUE;
            int minCol = Integer.MAX_VALUE;
            int maxCol = Integer.MIN_VALUE;

            for (int[] point : points) {
                minRow = Math.min(minRow, point[0]);
                maxRow = Math.max(maxRow, point[0]);
                minCol = Math.min(minCol, point[1]);
                maxCol = Math.max(maxCol, point[1]);
            }

            int area = (maxRow - minRow + 1) * (maxCol - minCol + 1);
            maxArea = Math.max(maxArea, area);
        }

        // 输出最大面积
        System.out.println(maxArea);
    }
}