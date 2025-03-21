package com.het.demotest.e100.最长连续子序列;

import java.util.Scanner;

public class Main {
    // 辅助函数：将输入字符串解析为整数数组
    public static int[] parseInput(String input) {
        String[] parts = input.split(",");
        int[] nums = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            nums[i] = Integer.parseInt(parts[i]);
        }
        return nums;
    }

    // 使用滑动窗口寻找和为 targetSum 的最长连续子序列长度
    public static int longestSubsequenceWithSum(int[] nums, int targetSum) {
        int n = nums.length;
        int start = 0, end = 0;
        int currentSum = 0;
        int maxLength = -1;

        while (end < n) {
            // 扩大窗口，加入当前 end 元素
            currentSum += nums[end];

            // 如果 currentSum 超过 targetSum，收缩窗口
            while (currentSum > targetSum && start <= end) {
                currentSum -= nums[start];
                start++;
            }

            // 如果 currentSum 等于 targetSum，更新最大长度
            if (currentSum == targetSum) {
                maxLength = Math.max(maxLength, end - start + 1);
            }

            // 移动 end 向右扩展窗口
            end++;
        }

        return maxLength == -1 ? -1 : maxLength;
    }

    public static void main(String[] args) {
        // 输入处理
        Scanner scanner = new Scanner(System.in);

        // 读取输入序列
        String inputSequence = scanner.nextLine();

        // 读取目标和
        int targetSum = scanner.nextInt();

        // 检查目标和是否为 0
        if (targetSum == 0) {
            System.out.println(-1);
            scanner.close();
            return;
        }

        // 解析输入序列为整数数组
        int[] nums = parseInput(inputSequence);

        // 获取结果
        int result = longestSubsequenceWithSum(nums, targetSum);

        // 输出结果
        System.out.println(result);

        scanner.close();
    }
}