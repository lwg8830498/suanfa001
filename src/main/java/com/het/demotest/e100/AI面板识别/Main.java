package com.het.demotest.e100.AI面板识别;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();

        // 存储灯的信息：id, x1, y1, x2, y2
        int[][] lights = new int[N][5];
        boolean[] used = new boolean[N];
        List<Integer> result = new ArrayList<>();

        // 读取输入
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 5; j++) {
                lights[i][j] = scanner.nextInt();
            }
        }

        // 计算灯的高度的一半，用于判断是否在同一行
        double gap = (lights[0][4] - lights[0][2]) / 2.0;

        while (result.size() < N) {
            // 找出未使用的最高的灯
            int minYIndex = -1;
            double minY = 1e9;

            for (int i = 0; i < N; i++) {
                if (!used[i]) {
                    if (lights[i][2] < minY) {
                        minY = lights[i][2];
                        minYIndex = i;
                    }
                }
            }

            used[minYIndex] = true;

            // 找出同一行的所有灯
            List<int[]> sameRow = new ArrayList<>();
            sameRow.add(new int[]{lights[minYIndex][1], minYIndex});  // 先加入基准灯

            for (int i = 0; i < N; i++) {
                if (!used[i] && Math.abs(lights[i][2] - lights[minYIndex][2]) <= gap) {
                    sameRow.add(new int[]{lights[i][1], i});
                    used[i] = true;
                }
            }

            // 按x坐标排序
            sameRow.sort(Comparator.comparingInt(o -> o[0]));  // 按x1升序排序

            // 加入结果
            for (int[] lamp : sameRow) {
                result.add(lights[lamp[1]][0]);  // 加入编号
            }
        }

        // 输出结果
        for (int i = 0; i < N; i++) {
            System.out.print(result.get(i));
            if (i < N - 1) {
                System.out.print(" ");
            }
        }
    }
}