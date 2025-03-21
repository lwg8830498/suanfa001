package com.het.demotest.e200.找城市;

import java.util.*;

// 主类
public class Main {
    // 定义一个邻接表来存储图的结构，每个城市对应一个列表，列表中存储与该城市直接相连的其他城市
    static List<List<Integer>> g;

    // 程序的主入口
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // 读取城市的数量
        g = new ArrayList<>();
        for (int i = 0; i <= n; i++)
            g.add(new ArrayList<>()); // 初始化邻接表

        // 读取城市之间的道路信息
        for (int i = 1; i < n; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            g.get(u).add(v); // 将v加到u的邻接列表中
            g.get(v).add(u); // 将u加到v的邻接列表中，因为是无向图
        }

        int[] dp = new int[n + 5]; // 用于存储每个城市的聚集度
        // 计算每个城市的聚集度
        for (int i = 1; i <= n; i++) {
            for (int x : g.get(i)) {
                dp[i] = Math.max(dp[i], dfs(x, i)); // 执行深度优先搜索计算去除当前城市后其他城市组成的最大连通城市数
            }
        }

        // 找出聚集度最小的城市
        int minval = dp[1];
        for (int i = 1; i <= n; i++)
            minval = Math.min(minval, dp[i]); // 比较找出最小聚集度

        StringBuffer sb = new StringBuffer(); // 用于存储结果
        for (int i = 1; i <= n; i++) {
            if (dp[i] == minval)
                sb.append(i).append(" "); // 如果城市的聚集度等于最小值，则加入到结果中
        }

        System.out.println(sb.toString()); // 输出结果
    }

    // 深度优先搜索函数，用于计算从某个城市开始不经过其父城市能到达的城市数量
    static int dfs(int u, int fr) {
        int res = 1; // 包含当前城市
        for (int v : g.get(u)) {
            if (v == fr)
                continue; // 避免返回到父城市
            res += dfs(v, u); // 递归调用以计算每个子节点的城市数量，并累加
        }
        return res; // 返回连通城市的数量
    }
}