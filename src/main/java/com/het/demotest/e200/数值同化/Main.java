package com.het.demotest.e200.数值同化;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        int[][] matrix = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        // Initialize the queue with the starting point
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        matrix[0][0] = 1;

        // Directions for up, down, left, right
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            for (int[] dir : directions) {
                int x = current[0] + dir[0];
                int y = current[1] + dir[1];

                if (x >= 0 && x < m && y >= 0 && y < n && matrix[x][y] == 0) {
                    matrix[x][y] = 1;
                    queue.add(new int[]{x, y});
                }
            }
        }

        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] != 1) {
                    count++;
                }
            }
        }

        System.out.println(count);
    }
}