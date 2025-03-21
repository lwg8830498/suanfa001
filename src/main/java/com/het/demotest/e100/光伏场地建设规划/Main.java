package com.het.demotest.e100.光伏场地建设规划;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 输入矩阵的长、宽、电站的边长和最低发电量要求
        int regionLength = scanner.nextInt();
        int regionWidth = scanner.nextInt();
        int squareSide = scanner.nextInt();
        int minPower = scanner.nextInt();

        int[][] powerGrid = new int[regionLength + 1][regionWidth + 1];  // 增加1行1列用于前缀和计算简化

        // 读取每个位置的发电量
        for (int i = 1; i <= regionLength; i++) {
            for (int j = 1; j <= regionWidth; j++) {
                powerGrid[i][j] = scanner.nextInt();
            }
        }

        // 构建前缀和矩阵直接在原始矩阵上，从1开始避免越界和多余的判断
        for (int i = 1; i <= regionLength; i++) {
            for (int j = 1; j <= regionWidth; j++) {
                powerGrid[i][j] += powerGrid[i - 1][j] + powerGrid[i][j - 1] - powerGrid[i - 1][j - 1];
            }
        }

        int count = 0; // 满足条件的区域数量

        // 遍历所有可能的正方形子区域的左上角坐标 (i, j)
        for (int i = 1; i <= regionLength - squareSide + 1; i++) {
            for (int j = 1; j <= regionWidth - squareSide + 1; j++) {
                // 子矩阵的右下角坐标
                int x2 = i + squareSide - 1;
                int y2 = j + squareSide - 1;

                // 计算子矩阵的总发电量
                int totalPower = powerGrid[x2][y2] - powerGrid[i - 1][y2] - powerGrid[x2][j - 1] + powerGrid[i - 1][j - 1];

                // 检查总发电量是否满足条件
                if (totalPower >= minPower) {
                    count++;
                }
            }
        }

        // 输出满足条件的区域数量
        System.out.println(count);

        scanner.close();
    }
}