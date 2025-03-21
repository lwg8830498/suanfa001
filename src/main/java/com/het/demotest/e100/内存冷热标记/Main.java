package com.het.demotest.e100.内存冷热标记;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取访存序列的记录条数
        int N = scanner.nextInt();
        scanner.nextLine(); // 读取换行符

        // 读取访存序列
        String[] pages = scanner.nextLine().split(" ");
        int[] pageSequence = new int[N];
        for (int i = 0; i < N; i++) {
            pageSequence[i] = Integer.parseInt(pages[i]);
        }

        // 读取热内存页的频次阈值
        int T = scanner.nextInt();

        // 统计每个页框号的访问频次
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int page : pageSequence) {
            frequencyMap.put(page, frequencyMap.getOrDefault(page, 0) + 1);
        }

        // 筛选热内存页
        List<Map.Entry<Integer, Integer>> hotPages = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() >= T) {
                hotPages.add(entry);
            }
        }

        // 输出热内存页的个数
        System.out.println(hotPages.size());

        // 如果热内存页个数大于0，则输出排序后的页框号
        if (hotPages.size() > 0) {
            // 按照频次降序排序，频次相同的按页框号升序排序
            hotPages.sort((a, b) -> {
                if (b.getValue().equals(a.getValue())) {
                    return a.getKey().compareTo(b.getKey());
                } else {
                    return b.getValue().compareTo(a.getValue());
                }
            });

            // 输出排序后的页框号
            for (Map.Entry<Integer, Integer> entry : hotPages) {
                System.out.println(entry.getKey());
            }
        }
    }
}