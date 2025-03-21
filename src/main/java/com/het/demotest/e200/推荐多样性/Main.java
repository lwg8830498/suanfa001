package com.het.demotest.e200.推荐多样性;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取N和K
        int N = scanner.nextInt();
        int K = scanner.nextInt();
        scanner.nextLine(); // 读取换行符

        // 读取多个列表
        List<List<Integer>> lists = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.isEmpty()) {
                break;
            }
            List<Integer> list = new ArrayList<>();
            for (String num : line.split(" ")) {
                list.add(Integer.parseInt(num));
            }
            lists.add(list);
        }

        // 初始化N个窗口
        List<List<Integer>> windows = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            windows.add(new ArrayList<>());
        }

        // 分配元素到窗口
        for (List<Integer> list : lists) {
            int listSize = list.size();
            int elementsPerWindow = listSize / N;
            int remainder = listSize % N;

            int index = 0;
            for (int i = 0; i < N; i++) {
                int elementsToAdd = elementsPerWindow + (i < remainder ? 1 : 0);
                for (int j = 0; j < elementsToAdd; j++) {
                    if (index < listSize) {
                        windows.get(i).add(list.get(index));
                        index++;
                    }
                }
            }
        }

        // 输出每个窗口的元素
        for (int i = 0; i < N; i++) {
            List<Integer> window = windows.get(i);
            for (int j = 0; j < K; j++) {
                if (j < window.size()) {
                    System.out.print(window.get(j) + " ");
                } else {
                    System.out.print("0 "); // 如果元素不足，用0填充
                }
            }
            System.out.println();
        }
    }
}