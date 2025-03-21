package com.het.demotest.e100.按身高和体重排队;

import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] height = new int[n];
        int[] weight = new int[n];
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            height[i] = in.nextInt();
        }
        for (int i = 0; i < n; i++) {
            weight[i] = in.nextInt();
        }
        res = sortStudents(height, weight, n);
        for (int i = 0; i < n; i++) {
            System.out.print(res[i]);
            System.out.print(" ");
        }

    }
    public static int[] sortStudents(int[] height, int[] weight, int n) {
        if (height == null || weight == null || height.length == 0 ||
                weight.length == 0 || height.length != weight.length) return new int[0];
        int[] res = new int[n];
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<Integer> tmp = new ArrayList<>();
            tmp.add(i + 1);
            tmp.add(height[i]);
            tmp.add(weight[i]);
            list.add(new ArrayList<>(tmp));
        }
        Collections.sort(list, new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                if (o1.get(1) != o2.get(1)) {
                    return o1.get(1) - o2.get(1);
                } else if (o1.get(2) != o2.get(2)) {
                    return o1.get(2) - o2.get(2);
                } else {
                    return o1.get(0) - o2.get(0);
                }
            }
        });
        for (int i = 0; i < n; i++) {
            res[i] = list.get(i).get(0);
        }
        return res;
    }
}
 