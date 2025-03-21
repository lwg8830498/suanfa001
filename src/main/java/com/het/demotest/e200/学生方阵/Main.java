package com.het.demotest.e200.学生方阵;

import java.util.Scanner;

public class Main {

    // 函数：找出矩阵中最大的连续男生数量
    public static int findMaxConsecutiveBoys(char[][] matrix, int rows, int cols) {
        int maxCount = 0; // 用于存储最大连续男生数量

        // 四个方向的移动：右、下、对角线、反对角线
        int[] dx = {0, 1, 1, 1};
        int[] dy = {1, 0, 1, -1};

        // 遍历矩阵的每一个元素
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 'M') { // 仅在当前元素是男生('M')时进行检查
                    for (int d = 0; d < 4; d++) { // 检查四个方向
                        int count = 1; // 初始化当前方向的连续男生数量
                        int x = i + dx[d], y = j + dy[d];

                        // 在当前方向上扩展查找连续的男生('M')
                        while (x >= 0 && x < rows && y >= 0 && y < cols && matrix[x][y] == 'M') {
                            count++; // 增加计数器
                            x += dx[d]; // 更新下一个检查的坐标
                            y += dy[d];
                        }

                        // 更新最大连续男生数量
                        maxCount = Math.max(maxCount, count);
                    }
                }
            }
        }

        return maxCount; // 返回最大连续男生数量
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取第一行，获取矩阵的行数和列数
        String[] dimensions = scanner.nextLine().split(",");
        int rows = Integer.parseInt(dimensions[0]);
        int cols = Integer.parseInt(dimensions[1]);

        char[][] matrix = new char[rows][cols]; // 初始化矩阵

        // 逐行读取矩阵数据
        for (int i = 0; i < rows; i++) {
            String line = scanner.nextLine();
            String[] items = line.split(",");
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = items[j].charAt(0); // 填充矩阵
            }
        }

        int result = findMaxConsecutiveBoys(matrix, rows, cols); // 查找最大连续男生数量
        System.out.println(result); // 输出结果

        scanner.close();
    }
}