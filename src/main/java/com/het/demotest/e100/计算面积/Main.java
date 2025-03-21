package com.het.demotest.e100.计算面积;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int e = scanner.nextInt();
        int[][] nums = new int[n + 1][2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2; j++) {
                nums[i][j] = scanner.nextInt();
            }
        }
        int[] height = new int[n];
        for (int i = 0; i < n; i++) {
            if(i == 0) {
                height[i] = nums[i][1];
            } else {
                height[i] = height[i - 1] + nums[i][1];
            }
        }
        int sum = 0;
        for (int i = 0; i < n - 1; i++) {
            sum += Math.abs(height[i] * (nums[i + 1][0] - nums[i][0]));
        }
        sum += Math.abs(height[n - 1] * (e - nums[n - 1][0]));
        System.out.println(sum);
    }
}