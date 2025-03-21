package com.het.demotest.e100.太阳能板最大面积;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 读取输入行
        String inputLine = scanner.nextLine();
        // 按逗号分割输入字符串
        String[] parts = inputLine.split(",");
        int n = parts.length;
        long[] pillars = new long[n];
        for (int i = 0; i < n; i++) {
            // 去除可能存在的空格，并转换为 long 型
            pillars[i] = Long.parseLong(parts[i].trim());
        }

        // 双指针法求最大面积
        int left = 0, right = n - 1;
        long maxArea = 0;
        while (left < right) {
            long height = Math.min(pillars[left], pillars[right]);
            long width = right - left;
            long area = height * width;
            maxArea = Math.max(maxArea, area);

            // 移动较矮一侧的指针
            if (pillars[left] < pillars[right])
                left++;
            else
                right--;
        }

        // 输出最大太阳能板面积
        System.out.println(maxArea);
        scanner.close();
    }
}