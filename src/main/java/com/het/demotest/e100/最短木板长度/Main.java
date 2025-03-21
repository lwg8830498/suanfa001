package com.het.demotest.e100.最短木板长度;

import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // 木板数
        int m = scanner.nextInt(); // 木料总长度
        int[] number = new int[2000010]; // 用于记录每个长度木板的数量
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // 小顶堆，存储木板长度，优先取最小值

        // 读入木板长度，并记录每种长度木板的数量
        for (int i = 0; i < n; i++) {
            int x = scanner.nextInt();
            if (number[x] == 0) {
                pq.offer(x); // 如果长度 x 的木板数量为 0，则将其加入优先队列
            }
            number[x]++; // 记录长度 x 的木板数量
        }

        // 如果只有一块木板，则直接加上全部木料长度，输出结果
        if (n == 1) {
            System.out.println(pq.peek() + m);
            return;
        }

        // 开始循环分配木料，尽量提高最短木板的长度
        while (m > 0) {
            int minLen = pq.poll(); // 当前最短木板的长度
            int num = number[minLen]; // 当前最短木板的数量

            // 如果优先队列为空或无法将所有最短木板提升到下一个长度
            if (pq.isEmpty() || (pq.peek() - minLen) * num >= m) {
                // 计算在剩余木料下可以将最短木板提升的最大高度
                System.out.println(minLen + m / num);
                return;
            } else {
                // 如果可以提升到下一个长度
                m -= (pq.peek() - minLen) * num; // 减去将最短木板提升到下一个长度所需的木料
                number[pq.peek()] += num; // 更新下一个长度的木板数量
            }
        }
    }
}