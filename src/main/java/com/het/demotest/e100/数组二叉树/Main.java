package com.het.demotest.e100.数组二叉树;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    // 用于存储从根节点到最小叶子节点的路径
    private static List<Integer> minPath = new ArrayList<>();
    private static int minLeafValue = Integer.MAX_VALUE;

    // 递归函数，用于查找最小叶子节点的路径
    public static void findMinLeafPath(List<Integer> tree, int index, List<Integer> currentPath) {
        // 检查是否超出边界或是空节点
        if (index >= tree.size() || tree.get(index) == -1) return;

        // 添加当前节点到路径
        currentPath.add(tree.get(index));

        // 计算左子节点和右子节点的索引
        int leftIndex = 2 * index;
        int rightIndex = 2 * index + 1;

        // 判断是否为叶子节点
        boolean isLeaf = (leftIndex >= tree.size() || tree.get(leftIndex) == -1) &&
                (rightIndex >= tree.size() || tree.get(rightIndex) == -1);

        if (isLeaf) {
            // 更新最小叶子节点及路径
            if (tree.get(index) < minLeafValue) {
                minLeafValue = tree.get(index);
                minPath = new ArrayList<>(currentPath);
            }
        } else {
            // 递归遍历左右子节点
            findMinLeafPath(tree, leftIndex, currentPath);
            findMinLeafPath(tree, rightIndex, currentPath);
        }

        // 回溯，移除当前节点
        currentPath.remove(currentPath.size() - 1);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        scanner.close();

        // 解析输入，将树存储在List中，第一个元素为空位填充
        List<Integer> tree = new ArrayList<>();
        tree.add(0); // 下标0作为占位符
        for (String s : input.split(" ")) {
            tree.add(Integer.parseInt(s));
        }

        // 从根节点开始搜索最小叶子节点的路径
        List<Integer> currentPath = new ArrayList<>();
        findMinLeafPath(tree, 1, currentPath);

        // 输出最小路径
        for (int i = 0; i < minPath.size(); i++) {
            if (i > 0) System.out.print(" ");
            System.out.print(minPath.get(i));
        }
        System.out.println();
    }
}