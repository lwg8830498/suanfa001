package com.het.demotest.e100.执行时长;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 输入GPU一次最多执行的任务个数
        int maxTasks = scanner.nextInt();
        // 输入任务数组的长度
        int numTasks = scanner.nextInt();
        // 输入任务数组
        int[] tasks = new int[numTasks];
        for (int i = 0; i < numTasks; i++) {
            tasks[i] = scanner.nextInt();
        }

        // 调用计算最少时间的函数
        int result = calculateMinTime(maxTasks, tasks);
        System.out.println(result);

        scanner.close();
    }

    public static int calculateMinTime(int maxTasks, int[] tasks) {
        int time = 0;
        int remainingTasks = 0;

        for (int task : tasks) {
            remainingTasks += task; // 累加当前时刻新增的任务

            // 如果剩余任务超过了GPU一次能执行的任务数
            if (remainingTasks > maxTasks) {
                time += 1;
                remainingTasks -= maxTasks; // 执行maxTasks个任务
            } else {
                time += 1;
                remainingTasks = 0; // 执行所有剩余任务
            }
        }

        // 如果循环结束后还有未完成的任务
        if (remainingTasks > 0) {
            time += (remainingTasks + maxTasks - 1) / maxTasks; // 向上取整
        }

        return time;
    }
}