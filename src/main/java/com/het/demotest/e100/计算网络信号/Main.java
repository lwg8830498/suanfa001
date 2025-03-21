package com.het.demotest.e100.计算网络信号;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    // 方向数组，用于表示上下左右四个方向
    static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取输入的网格大小 m 和 n
        int m = scanner.nextInt();
        int n = scanner.nextInt();

        // 初始化网格
        int[][] array = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                array[i][j] = scanner.nextInt();
            }
        }

        // 读取目标位置
        int targetI = scanner.nextInt();
        int targetJ = scanner.nextInt();

        // 初始化信号强度数组，-1表示未访问
        int[][] signal = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                signal[i][j] = -1;
            }
        }

        // BFS队列，队列中存储 (x, y, signal_strength)
        Queue<int[]> queue = new LinkedList<>();

        // 寻找信号源，将信号源的位置加入队列
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (array[i][j] > 0) {  // 找到信号源
                    queue.offer(new int[]{i, j, array[i][j]});
                    signal[i][j] = array[i][j];  // 初始信号值为信号源的值
                }
            }
        }

        // 开始BFS
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            int currentSignal = current[2];

            // 遍历四个方向
            for (int[] dir : directions) {
                int newX = x + dir[0];
                int newY = y + dir[1];

                // 判断是否越界或遇到阻隔物
                if (newX >= 0 && newX < m && newY >= 0 && newY < n && array[newX][newY] != -1) {
                    int newSignal = currentSignal - 1;
                    // 只有信号强度大于0并且比当前信号值大时才更新
                    if (newSignal > 0 && newSignal > signal[newX][newY]) {
                        signal[newX][newY] = newSignal;
                        queue.offer(new int[]{newX, newY, newSignal});
                    }
                }
            }
        }

        // 输出指定位置的信号值，如果未覆盖到，输出0
        System.out.println(signal[targetI][targetJ] != -1 ? signal[targetI][targetJ] : 0);
    }
}