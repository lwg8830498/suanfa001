package com.het.demotest.e100.wonderland游乐园;

import java.util.Scanner;
import java.util.HashSet;
import java.util.Arrays;

public class Main {

    public static int mincostTickets(int[] costs, int[] days) {
        // 找到最后一个游玩的日子
        int lastDay = days[days.length - 1];
        // 创建动态规划表，dp[i] 表示到第 i 天的最低消费
        int[] dp = new int[lastDay + 1];
        // 用HashSet存储游玩日，便于快速查找
        HashSet<Integer> daySet = new HashSet<>();
        for (int day : days) {
            daySet.add(day);
        }

        // 遍历从第一天到最后一天
        for (int day = 1; day <= lastDay; day++) {
            if (!daySet.contains(day)) {
                // 如果这天不需要游玩，消费和前一天相同
                dp[day] = dp[day - 1];
            } else {
                // 否则，考虑买各种票的情况
                int cost1 = dp[Math.max(0, day - 1)] + costs[0];
                int cost3 = dp[Math.max(0, day - 3)] + costs[1];
                int cost7 = dp[Math.max(0, day - 7)] + costs[2];
                int cost30 = dp[Math.max(0, day - 30)] + costs[3];
                // 取四种情况的最小值
                dp[day] = Math.min(Math.min(cost1, cost3), Math.min(cost7, cost30));
            }
        }
        // 返回最后一天的最低消费
        return dp[lastDay];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取价格数组，按一日票、三日票、周票、月票的顺序
        int[] costs = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        // 读取小王计划的游玩日期数组

        int[] days = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        // 计算并打印最低消费
        System.out.println(mincostTickets(costs, days));
    }
}