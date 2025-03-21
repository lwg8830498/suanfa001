package com.het.demotest.e100.打印任务排序;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim();
        scanner.close();

        // 解析输入的优先级队列
        String[] inputArray = input.split(",");
        int[] priorities = Arrays.stream(inputArray).mapToInt(Integer::parseInt).toArray();

        // 使用队列保存打印任务的信息，pair中第一个是优先级，第二个是其在原始队列中的位置
        Deque<int[]> printerQueue = new ArrayDeque<>();
        for (int i = 0; i < priorities.length; i++) {
            printerQueue.add(new int[]{priorities[i], i});
        }

        // 保存打印顺序结果
        int[] printOrder = new int[priorities.length];
        int currentOrder = 0;

        while (!printerQueue.isEmpty()) {
            int[] currentTask = printerQueue.pollFirst();

            // 检查队列中是否存在优先级比当前任务更高的任务
            boolean hasHigherPriority = printerQueue.stream().anyMatch(task -> task[0] > currentTask[0]);

            if (hasHigherPriority) {
                printerQueue.addLast(currentTask);
            } else {
                printOrder[currentTask[1]] = currentOrder;
                currentOrder++;
            }
        }

        // 输出打印顺序
        for (int i = 0; i < printOrder.length; i++) {
            if (i > 0) {
                System.out.print(",");
            }
            System.out.print(printOrder[i]);
        }
        System.out.println();
    }
}