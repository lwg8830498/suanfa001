package com.het.demotest.e100.预定酒店;

import java.util.*;

public class Main {
    private static PriorityQueue<Integer> bestPrice(int[] prices, int k, int x) {
        PriorityQueue<Integer> heap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (Math.abs(x - o1) == Math.abs(x - o2)) {
                    return o1 < o2 ? o1 : o2;
                } else {
                    return Math.abs(x - o1) - Math.abs(x - o2);
                }
            }
        });
        for (int i = 0; i < prices.length; i++) {
            heap.add(prices[i]);
        }
        return heap;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            int k = sc.nextInt();
            int x = sc.nextInt();
            int[] prices = new int[n];
            for (int i = 0; i < n; i++) {
                prices[i] = sc.nextInt();
            }
            PriorityQueue<Integer> heap = bestPrice(prices, k, x);
            int[] res = new int[k];
            for (int i = 0; i < k; i++) {
                res[i] = heap.poll();
            }
            Arrays.sort(res);
            for (int i = 0; i < k; i++) {
                System.out.print(res[i] + " ");
            }
        }
    }
}