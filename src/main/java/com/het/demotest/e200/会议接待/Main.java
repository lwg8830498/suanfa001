package com.het.demotest.e200.会议接待;

import java.util.*;

public class Main {

    // 计算坐满车的方案数量
    public static int countWays(List<Integer> groups, int carCapacity) {
        // 初始化动态规划数组，dp[i] 表示是否可以坐满容量为 i 的车
        int[] dp = new int[carCapacity + 1];
        dp[0] = 1;  // 容量为 0 有 1 种方式，就是不选择任何代表团

        // 遍历每个代表团人数
        for (int group : groups) {
            // 从后往前遍历，确保每个代表团只使用一次
            for (int i = carCapacity; i >= group; i--) {
                dp[i] += dp[i - group];
            }
        }

        return dp[carCapacity];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取代表团人数列表
        String[] groupStrings = scanner.nextLine().split(",");
        List<Integer> groups = new ArrayList<>();
        for (String groupString : groupStrings) {
            groups.add(Integer.parseInt(groupString.trim()));
        }

        int carCapacity = scanner.nextInt();

        System.out.println(countWays(groups, carCapacity));
    }
}