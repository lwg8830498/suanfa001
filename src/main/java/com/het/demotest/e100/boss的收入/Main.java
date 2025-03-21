package com.het.demotest.e100.boss的收入;

import java.util.*;

public class Main {
    static List<List<Integer>> g;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        final int N = (int)1e6 + 5;
        g = new ArrayList<>();
        for (int i=0; i<N; i++) {
            g.add(new ArrayList<>());
        }
        int[] du = new int[N], val = new int[N];
        int n=0, m=in.nextInt();
        while (m-- > 0)
        {
            int u = in.nextInt(), v = in.nextInt(), w = in.nextInt();
            n = Math.max(n, Math.max(u, v));        // 手动计算 n 值
            g.get(u).add(v);                // 建图
            du[v]++;                        // 记录每个点的入度
            val[u] += w;                    // 记录收入
        }
        Deque<Integer> q = new ArrayDeque<>();
        // 将入度为 0 的点入队
        for (int i=0; i<=n; i++)
        {
            if (du[i] == 0) {
                q.add(i);
            }
        }
        // 拓扑排序
        while (q.size() > 0)
        {
            int u = q.poll();
            for (int v : g.get(u))
            {
                du[v]--;        // 入度-1
                val[v] += val[u] / 100 * 15;        // 计算收入
                if (du[v] == 0)     // 入度为 0 的点入队
                {
                    q.add(v);
                }
            }
            // 最后剩下的点一定是 boss
            if (q.size() == 0) {
                System.out.println(u + " " + val[u]);
            }
        }
    }
}