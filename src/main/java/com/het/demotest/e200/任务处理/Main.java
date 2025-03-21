package com.het.demotest.e200.任务处理;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in); // 创建扫描器对象以接收输入
        List<Integer>[] cod = new List[100005]; // 创建一个列表数组，存储每个开始时间对应的结束时间
        for (int i=0; i<cod.length; i++) {
            cod[i] = new ArrayList<>(); // 初始化每个列表
        }
        int n = in.nextInt(); // 读取任务数量
        for (int i = 0; i < n; i++) {
            int s = in.nextInt(), t = in.nextInt(); // 读取每个任务的开始和结束时间
            cod[s].add(t); // 将结束时间添加到对应的开始时间列表中
        }
        int ans = 0; // 用于记录可以完成的最大任务数
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // 创建一个优先队列（最小堆）来存储当前可完成任务的结束时间
        for (int i = 1; i <= (int)1e5; i++) { // 遍历每一天
            for (int t : cod[i]) // 将当前天数开始的所有任务的结束时间加入到最小堆中
            {
                minHeap.add(t);
            }
            while (!minHeap.isEmpty() && minHeap.peek() < i) // 清除所有已经不能完成的任务（结束时间已经过去的任务）
            {
                minHeap.poll();
            }
            if (!minHeap.isEmpty()) { // 如果还有可以完成的任务
                ans++; // 完成任务数加一
                minHeap.poll(); // 移除已完成的任务（结束时间最早的任务）
            }
        }
        System.out.println(ans); // 输出可以完成的最大任务数
    }
}