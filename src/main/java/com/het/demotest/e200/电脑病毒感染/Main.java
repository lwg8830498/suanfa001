package com.het.demotest.e200.电脑病毒感染;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), m = in.nextInt(); // 输入电脑数量n和连接数量m
        List<List<int[]>> g = new ArrayList<>(); // 用于存储邻接表
        for (int i = 0; i <= n; i++) {
            g.add(new ArrayList<>()); // 初始化邻接表
        }
        while (m-- > 0) {
            int u = in.nextInt(), v = in.nextInt(), w = in.nextInt(); // 输入连接信息
            g.get(u).add(new int[]{w, v}); // 将连接加入邻接表
        }
        int s = in.nextInt(); // 输入病毒初始感染的电脑编号
        System.out.println(dj(s, n, g)); // 输出最短时间内感染所有电脑所需的时间
    }

    // 迪杰斯特拉算法函数，计算从起始点s出发的最短感染时间
    static int dj(int s, int n, List<List<int[]>> g)
    {
        boolean[] vis = new boolean[n+1]; // 记录每台电脑是否已被访问
        int[] dis = new int[n+1]; // 记录每台电脑的最短感染时间
        Arrays.fill(dis, 1<<30); // 初始化所有电脑的最短感染时间为无穷大
        // 优先队列，用于选择当前感染时间最短的电脑 {感染时间, 电脑编号}
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        q.add(new int[]{0, s}); // 将起始点加入优先队列，初始感染时间为0
        dis[s] = 0; // 起始点的感染时间为0

        while (!q.isEmpty())
        {
            int[] cur = q.poll(); // 选择感染时间最短的电脑
            int u = cur[1];
            if (vis[u]) continue; // 如果该电脑已被访问过，则跳过
            vis[u] = true; // 标记该电脑已被访问

            // 遍历与该电脑相邻的所有电脑
            for (int[] next : g.get(u))
            {
                int w = next[0], v = next[1];
                // 如果从当前电脑到相邻电脑的感染时间小于已知的最短感染时间，则更新
                if (dis[u] + w < dis[v])
                {
                    dis[v] = dis[u] + w; // 更新相邻电脑的最短感染时间
                    q.add(new int[]{dis[v], v}); // 将相邻电脑加入优先队列
                }
            }
        }

        int ans = 0, cnt = 0; // ans存储最大感染时间，cnt记录被感染的电脑数量
        for (int i = 1; i <= n; i++)
        {
            ans = Math.max(ans, dis[i]); // 计算所有电脑中的最大感染时间
            cnt += vis[i] ? 1 : 0; // 统计被访问的电脑数量
        }
        if (cnt < n) // 如果有电脑未被感染，则返回-1
            return -1;
        return ans; // 返回最大感染时间
    }
}