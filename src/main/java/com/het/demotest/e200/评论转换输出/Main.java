package com.het.demotest.e200.评论转换输出;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    private static Queue<String> queue = new LinkedList<>();

    public static void recursive(Queue<String> queue, int level, int childCount, List<List<String>> tree) {
        for (int i = 0; i < childCount; i++) {
            String comment = queue.poll();
            if (tree.size() < level) {
                tree.add(new ArrayList<>());
            }
            tree.get(level - 1).add(comment);
            int count = Integer.parseInt(queue.poll());
            if (count > 0) {
                recursive(queue, level + 1, count, tree);
            }
        }
    }

    public static void result() {
        List<List<String>> tree = new ArrayList<>();
        int level = 1;
        while (!queue.isEmpty()) {
            String comment = queue.poll();
            if (tree.size() < level) {
                tree.add(new ArrayList<>());
            }
            tree.get(0).add(comment);
            int childCount = Integer.parseInt(queue.poll());
            recursive(queue, level + 1, childCount, tree);
        }
        System.out.println(tree.size());
        for (List<String> nodes : tree) {
            System.out.println(String.join(" ", nodes));
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] parts = input.split(",");
        for (String part : parts) {
            queue.add(part.trim());
        }

        result();
    }
}