package com.het.demotest.e100.分割数组的最大值;

import java.util.Scanner;

public class Main {

    // 函数：计算数组划分后左右子数组和的最大差值
    public static int maxDifference(int[] nums) {
        int totalSum = 0, leftSum = 0, maxDiff = 0;  // 初始化变量

        // 计算数组的总和
        for (int num : nums) {
            totalSum += num;
        }

        // 遍历所有划分点
        for (int i = 0; i < nums.length - 1; i++) {
            leftSum += nums[i];  // 累积左边的和
            int rightSum = totalSum - leftSum;  // 右边的和为总和减去左边的和
            maxDiff = Math.max(maxDiff, Math.abs(leftSum - rightSum));  // 计算并更新最大差值
        }

        return maxDiff;  // 返回最大差值
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);  // 读取输入
        int n = scanner.nextInt();  // 输入数组长度
        int[] nums = new int[n];  // 初始化数组

        // 输入数组的值
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }

        // 输出计算的最大差值
        System.out.println(maxDifference(nums));
    }
}