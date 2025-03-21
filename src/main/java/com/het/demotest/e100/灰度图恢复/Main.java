package com.het.demotest.e100.灰度图恢复;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取压缩数据
        String[] compressedData = scanner.nextLine().split(" ");
        int rows = Integer.parseInt(compressedData[0]);
        int cols = Integer.parseInt(compressedData[1]);

        // 初始化灰度矩阵
        int[][] grayMatrix = new int[rows][cols];

        // 填充灰度矩阵
        int index = 2;
        int currentRow = 0, currentCol = 0;
        while (index < compressedData.length) {
            int grayValue = Integer.parseInt(compressedData[index]);
            int count = Integer.parseInt(compressedData[index + 1]);
            for (int i = 0; i < count; i++) {
                grayMatrix[currentRow][currentCol] = grayValue;
                currentCol++;
                if (currentCol == cols) {
                    currentCol = 0;
                    currentRow++;
                }
            }
            index += 2;
        }

        // 读取指定的像素位置
        int targetRow = scanner.nextInt();
        int targetCol = scanner.nextInt();

        // 输出指定像素的灰阶值
        System.out.println(grayMatrix[targetRow][targetCol]);
    }
}