package com.het.demotest.e200.Linux发行版的数量;

import java.util.Scanner;

public class Main {

    private static int dfs(int node, int[][] isConnected, boolean[] visited) {
        visited[node] = true;  // 标记当前节点为已访问
        int count = 1;  // 当前连通分量的节点数量加一
        for (int i = 0; i < isConnected.length; i++) {
            if (isConnected[node][i] == 1 && !visited[i]) {  // 如果节点 i 与当前节点相连且未访问
                count += dfs(i, isConnected, visited);  // 递归访问节点 i
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();  // 输入发行版的数量
        int[][] isConnected = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                isConnected[i][j] = scanner.nextInt();  // 输入每个发行版之间的直接关联关系
            }
        }

        boolean[] visited = new boolean[N];  // 初始化所有节点为未访问状态
        int maxGroupSize = 0;  // 记录最大的发行版集大小

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {  // 如果当前节点未被访问
                int count = dfs(i, isConnected, visited);  // 通过 DFS 查找所有与当前节点相连的节点
                maxGroupSize = Math.max(maxGroupSize, count);  // 更新最大连通分量大小
            }
        }

        System.out.println(maxGroupSize);  // 输出最大的发行版集大小
    }
}