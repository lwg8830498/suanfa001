package com.het.demotest.e200.快递员的烦恼;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static final int INF = (int) 1e9; // 定义一个足够大的数作为无穷大
    static int n, m; // 客户数量和客户间路线数量
    static int[][] path = new int[15][15]; // 存储所有节点之间的最短路径
    static int num = 1; // 用于记录下一个可用的节点索引，起始为1（0是起点）
    static int[] hash = new int[1010]; // 映射客户ID到节点索引
    static boolean[] vist = new boolean[15]; // 标记数组，用于DFS中标记哪些节点已访问
    static int ans = INF; // 存储最短路径的结果

    // 深度优先搜索，计算从起点访问所有客户并返回的最短路径
    public static void dfs(int u, int k, int t) {
        if (k > ans) return; // 如果当前路径长度已超过已知最短路径，则停止搜索
        if (t == num - 1) { // 所有客户访问完毕，计算回起点的总距离，并更新最短路径
            ans = Math.min(ans, k + path[u][0]);
            return;
        }
        for (int i = 1; i < num; i++) {
            if (!vist[i]) {
                vist[i] = true;
                dfs(i, k + path[u][i], t + 1);
                vist[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();
        Arrays.fill(hash, -1); // 初始化hash映射表
        for (int i = 0; i < path.length; i++) {
            Arrays.fill(path[i], INF); // 初始化路径长度为无穷大
        }

        // 读取客户和起点之间的距离
        for (int i = 0; i < n; i++) {
            int id = scanner.nextInt();
            int distance = scanner.nextInt();
            if (hash[id] == -1) {
                hash[id] = num++;
            }
            int idx = hash[id];
            path[0][idx] = Math.min(distance, path[0][idx]);
            path[idx][0] = path[0][idx];
        }

        // 读取客户间的距离
        for (int i = 0; i < m; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int distance = scanner.nextInt();
            x = hash[x];
            y = hash[y];
            path[x][y] = Math.min(path[x][y], distance);
            path[y][x] = path[x][y];
        }

        // 将自己到自己的距离设为0
        for (int i = 0; i < num; i++) {
            path[i][i] = 0;
        }

        // 使用Floyd-Warshall算法计算所有节点对之间的最短路径
        for (int k = 0; k < num; k++) {
            for (int i = 0; i < num; i++) {
                for (int j = 0; j < num; j++) {
                    path[i][j] = Math.min(path[i][j], path[i][k] + path[k][j]);
                }
            }
        }

        // 从起点开始进行深度优先搜索
        dfs(0, 0, 0);

        // 输出计算得到的最短路径长度
        System.out.println(ans);

        scanner.close();
    }
}