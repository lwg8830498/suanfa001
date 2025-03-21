package com.het.demotest.e200.最少面试官数;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt(); // 每个面试官最多面试人次
        int n = scanner.nextInt(); // 总面试场次
        int[][] interviews = new int[n][2];

        for (int i = 0; i < n; i++) {
            interviews[i][0] = scanner.nextInt(); // 开始时间
            interviews[i][1] = scanner.nextInt(); // 结束时间
        }

        // 按开始时间排序
        Arrays.sort(interviews, (a, b) -> a[0] - b[0]);

        // 最小堆，存储面试官的结束时间和面试次数
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        int interviewers = 0;

        for (int[] interview : interviews) {
            int start = interview[0];
            int end = interview[1];

            // 移除已经完成面试的面试官
            while (!heap.isEmpty() && heap.peek()[0] <= start) {
                int[] finished = heap.poll();
                if (finished[1] < m) {
                    // 如果面试次数未达到上限，可以继续使用
                    heap.offer(new int[]{end, finished[1] + 1});
                    break;
                }
            }

            // 如果没有可用的面试官，新增一个
            if (heap.isEmpty() || heap.peek()[0] > start) {
                heap.offer(new int[]{end, 1});
                interviewers++;
            }
        }

        System.out.println(interviewers);
    }
}