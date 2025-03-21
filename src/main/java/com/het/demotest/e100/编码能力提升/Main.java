package com.het.demotest.e100.编码能力提升;

import java.util.*;

public class Main {
    // 判断是否可以在指定天数内完成任务，每天的最大耗时不超过 maxTime
    public static boolean canDivide(List<Integer> time, int days, int maxTime) {
        int currentSum = 0;  // 当前天的总耗时
        int dayCount = 1;    // 当前天数
        int maxTask = 0;     // 当前天中最大任务耗时
        boolean isSkip = false; // 是否已跳过最大任务

        for (int i = 0; i < time.size(); i++) {
            int t = time.get(i);
            maxTask = Math.max(maxTask, t); // 更新最大任务耗时
            if (currentSum + t <= maxTime) {
                currentSum += t; // 如果加入当前任务不会超过最大耗时，累加
            } else if (!isSkip) {
                currentSum -= maxTask; // 如果可以跳过最大任务，跳过今天的最大任务
                currentSum += t;
                isSkip = true; // 标记已跳过
            } else {
                // 如果不能跳过最大任务，则进入下一天
                isSkip = false;
                currentSum = 0;
                maxTask = 0;
                dayCount++;
                i--; // 回退，重新考虑当前任务
                if (dayCount > days) {
                    return false; // 如果超过了允许的天数，则不能完成任务
                }
            }
        }
        return true; // 可以在指定天数内完成任务
    }

    // 二分查找最小的最大耗时
    public static int minMaxTime(List<Integer> time, int m) {
        int left = 0;
        int right = time.stream().mapToInt(Integer::intValue).sum(); // 左边界为0，右边界为所有任务的总耗时

        while (left < right) {
            int mid = (left + right) / 2; // 计算中间值
            if (canDivide(time, m, mid)) {
                right = mid; // 如果可以完成，则尝试更小的最大耗时
            } else {
                left = mid + 1; // 如果不可以完成，则增加最大耗时
            }
        }

        return left; // 返回最小的最大耗时
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] timeStrings = input.split(",");
        List<Integer> time = new ArrayList<>();

        for (String temp : timeStrings) {
            time.add(Integer.parseInt(temp)); // 读取任务耗时，存入列表
        }

        int m = scanner.nextInt();
        if (m >= time.size()) {
            System.out.println(0); // 如果天数大于等于任务数，则每个任务单独完成，最小耗时为0
        } else {
            System.out.println(minMaxTime(time, m)); // 输出最小的最大耗时
        }
    }
}