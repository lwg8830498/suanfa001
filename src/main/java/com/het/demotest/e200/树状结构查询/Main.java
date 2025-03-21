package com.het.demotest.e200.树状结构查询;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
    // 用来存储节点与其子节点列表的映射
    private static HashMap<String, List<String>> tree = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        // 读取节点关系，并构建树
        for (int i = 0; i < n; i++) {
            String[] relation = scanner.nextLine().split(" ");
            String child = relation[0];
            String parent = relation[1];
            tree.computeIfAbsent(parent, k -> new ArrayList<>()).add(child);
        }

        // 读取需要查询的节点
        String query = scanner.nextLine();
        List<String> result = new ArrayList<>();
        dfs(query, result);

        // 排序
        Collections.sort(result);

        // 输出结果
        for (String node : result) {
            System.out.println(node);
        }

        scanner.close();
    }

    // DFS方法用于收集所有下层节点
    private static void dfs(String node, List<String> result) {
        List<String> children = tree.get(node);
        if (children != null) {
            for (String child : children) {
                result.add(child);
                dfs(child, result);
            }
        }
    }
}