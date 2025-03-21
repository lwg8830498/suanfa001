package com.het.demotest.e100.流浪地球;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt(); // 发动机的总个数
        int E = scanner.nextInt(); // 手动启动的发动机总个数

        int[] activationTime = new int[N]; // 初始化每个发动机的启动时间为-1，表示未启动
        boolean[] vist = new boolean[N];   // 布尔数组，判断发动机是否启动过
        Arrays.fill(activationTime, -1);   // 使用-1初始化activationTime数组

        // 优先队列（最小堆），按启动时间排序
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        // 读取手动启动事件
        for (int i = 0; i < E; i++) {
            int T = scanner.nextInt();
            int P = scanner.nextInt();
            if (activationTime[P] == -1) { // 如果发动机P未启动过
                activationTime[P] = T;     // 更新发动机P的启动时间
                pq.offer(new int[]{T, P}); // 将事件加入优先队列
            }
        }

        // 根据关联启动规则传播启动状态
        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int time = current[0];
            int pos = current[1];

            if (vist[pos]) continue; // 如果已经处理过该发动机，跳过
            vist[pos] = true;        // 标记发动机pos为已启动

            int nextTime = time + 1;

            // 检查相邻的发动机（环形结构）
            int[] neighbors = {(pos - 1 + N) % N, (pos + 1) % N};
            for (int neighbor : neighbors) {
                if (activationTime[neighbor] == -1 || activationTime[neighbor] > nextTime) {
                    // 仅当相邻发动机未启动或者可以更早启动时，才更新其启动时间
                    activationTime[neighbor] = nextTime;
                    if (!vist[neighbor]) {  // 如果相邻发动机没启动过，才加入队列
                        pq.offer(new int[]{nextTime, neighbor});
                    }
                }
            }
        }

        // 找出最后被启动的发动机
        int maxTime = Arrays.stream(activationTime).max().orElse(-1);
        List<Integer> latestEngines = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            if (activationTime[i] == maxTime) {
                latestEngines.add(i);
            }
        }

        // 输出结果
        System.out.println(latestEngines.size()); // 输出最后被启动的发动机数量
        for (int engine : latestEngines) {
            System.out.print(engine + " "); // 输出发动机编号，按升序排序
        }
        System.out.println();
    }
}