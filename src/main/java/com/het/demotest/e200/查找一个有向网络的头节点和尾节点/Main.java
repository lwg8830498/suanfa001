package com.het.demotest.e200.查找一个有向网络的头节点和尾节点;

import java.util.*;

public class Main {

    static List<Integer> findEndpoints(List<int[]> edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> inDegree = new HashMap<>();
        Map<Integer, Integer> outDegree = new HashMap<>();

        // 构建图并计算每个节点的入度和出度
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
            inDegree.put(v, inDegree.getOrDefault(v, 0) + 1);
            outDegree.put(u, outDegree.getOrDefault(u, 0) + 1);
            inDegree.putIfAbsent(u, 0);
            outDegree.putIfAbsent(v, 0);
        }

        List<Integer> result = new ArrayList<>();
        // 检测环（拓扑排序）
        Queue<Integer> q = new LinkedList<>();
        for (Map.Entry<Integer, Integer> entry : inDegree.entrySet()) {
            if (entry.getValue() == 0) {
                result.add(entry.getKey());
                q.offer(entry.getKey());
            }
        }

        int count = 0;
        while (!q.isEmpty()) {
            int node = q.poll();
            count++;
            for (int neighbor : graph.getOrDefault(node, Collections.emptyList())) {
                int updatedInDegree = inDegree.get(neighbor) - 1;
                inDegree.put(neighbor, updatedInDegree);
                if (updatedInDegree == 0) {
                    q.offer(neighbor);
                }
            }
        }

        if (count != inDegree.size()) {
            return Collections.singletonList(-1);  // 图中有环
        }

        // 寻找尾节点
        TreeSet<Integer> tailNodes = new TreeSet<>();
        for (Map.Entry<Integer, Integer> entry : outDegree.entrySet()) {
            if (entry.getValue() == 0) {
                tailNodes.add(entry.getKey());  // 记录尾节点
            }
        }

        // 添加尾节点到结果中
        result.addAll(tailNodes);

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        List<int[]> edges = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            edges.add(new int[]{u, v});
        }

        List<Integer> endpoints = findEndpoints(edges);
        if(endpoints.size() == 0) {
            System.out.print(-1);
        }
        for (int i = 0; i < endpoints.size(); i++) {
            System.out.print(endpoints.get(i) + " ");
        }
    }
}