package com.het.demotest.e200.服务器广播;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<List<Integer>> matrix = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.isEmpty()) {
                break;  // 如果读取到空行则结束输入
            }
            String[] parts = line.split("\\s+");
            List<Integer> row = new ArrayList<>();
            for (String part : parts) {
                row.add(Integer.parseInt(part));
            }
            matrix.add(row);
        }
        scanner.close();

        int N = matrix.size(); // 获取服务器数量
        boolean[] visited = new boolean[N];  // 访问标记数组
        int broadcastCount = 0;  // 需要广播的服务器数量

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                dfs(i, matrix, visited);
                broadcastCount++;
            }
        }

        System.out.println(broadcastCount);
    }

    private static void dfs(int node, List<List<Integer>> matrix, boolean[] visited) {
        visited[node] = true;
        List<Integer> connections = matrix.get(node);
        for (int i = 0; i < connections.size(); i++) {
            if (connections.get(i) == 1 && !visited[i]) {
                dfs(i, matrix, visited);
            }
        }
    }
}