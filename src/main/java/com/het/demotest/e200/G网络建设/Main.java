package com.het.demotest.e200.G网络建设;

import java.util.*;

public class Main {
    static class Edge {
        int to, cost;

        Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();

        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int z = scanner.nextInt();
            int p = scanner.nextInt();
            if (p == 1) {
                z = 0; // 已存在的连接成本为0
            }
            graph.get(x).add(new Edge(y, z));
            graph.get(y).add(new Edge(x, z));
        }

        int totalCost = prim(graph, N);
        System.out.println(totalCost);
    }

    private static int prim(List<List<Edge>> graph, int N) {
        boolean[] visited = new boolean[N + 1];
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.cost));
        pq.add(new Edge(1, 0)); // 从基站1开始

        int totalCost = 0;
        int nodesInMST = 0;

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            if (visited[edge.to]) continue;

            visited[edge.to] = true;
            totalCost += edge.cost;
            nodesInMST++;

            for (Edge neighbor : graph.get(edge.to)) {
                if (!visited[neighbor.to]) {
                    pq.add(neighbor);
                }
            }
        }

        if (nodesInMST == N) {
            return totalCost;
        } else {
            return -1;
        }
    }
}