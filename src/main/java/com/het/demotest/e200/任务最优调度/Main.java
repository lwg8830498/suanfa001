package com.het.demotest.e200.任务最优调度;

import java.util.*;

public class Main {
    public static int leastInterval(int[] tasks, int n) {
        Map<Integer, Integer> taskCount = new HashMap<>();
        // 统计每种任务的数量
        for (int task : tasks) {
            taskCount.put(task, taskCount.getOrDefault(task, 0) + 1);
        }

        int maxCount = 0;
        // 找到任务数量的最大值
        for (int count : taskCount.values()) {
            maxCount = Math.max(maxCount, count);
        }

        int maxCountTasks = 0;
        // 统计数量等于最大值的任务个数
        for (int count : taskCount.values()) {
            if (count == maxCount) {
                maxCountTasks++;
            }
        }

        // 计算空闲槽位的数量
        int partCount = maxCount - 1;
        int partLength = n - (maxCountTasks - 1);
        int emptySlots = partCount * partLength;
        int availableTasks = tasks.length - maxCount * maxCountTasks;
        int idles = Math.max(0, emptySlots - availableTasks);

        // 返回总时间 = 任务数量 + 空闲时间
        return tasks.length + idles;
    }

    public static void main(String[] args) {
        // 输入
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] inputArray = input.split(",");
        int[] tasks = new int[inputArray.length];
        for (int i = 0; i < inputArray.length; i++) {
            tasks[i] = Integer.parseInt(inputArray[i]);
        }
        int n = scanner.nextInt();
        scanner.close();

        // 输出
        System.out.println(leastInterval(tasks, n));
    }
}