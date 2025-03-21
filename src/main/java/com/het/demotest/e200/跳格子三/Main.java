package com.het.demotest.e200.跳格子三;

import java.util.Scanner;
import java.util.Deque;
import java.util.LinkedList;

public class Main {
    // 使用一个较大的常量N来定义数组最大长度
    private static final int N = 100010;
    private static int[] a = new int[N];
    private static long[] dp = new long[N];
    private static Deque<Integer> q = new LinkedList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // 读取元素数量
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt(); // 输入n个元素到数组a中
        }

        int k = scanner.nextInt(); // 读取滑动窗口的大小
        dp[0] = a[0]; // 初始状态，只有一个元素时的最大和即为该元素本身

        // 初始化单调队列
        q.offer(0); // 初始时，队列中仅包含第一个元素的索引

        for (int i = 1; i < n; i++) {
            // 如果队列不为空且队头元素索引超出了滑动窗口范围，移出队头元素
            if (!q.isEmpty() && q.peek() < i - k) {
                q.poll();
            }

            // 计算dp[i]，即到达第i个元素的最大和
            dp[i] = dp[q.peek()] + a[i];

            // 维护队列的单调递减性
            while (!q.isEmpty() && dp[q.peekLast()] <= dp[i]) {
                q.pollLast();
            }

            // 将当前元素索引加入队列
            q.offer(i);
        }

        // 输出到达最后一个元素的最大和
        System.out.println(dp[n-1]);

        scanner.close(); // 关闭扫描器资源
    }
}