package com.het.demotest.e100.最佳种树距离;

import java.util.*;
//最佳植树距离
public class Main {
    // 检查是否可以在给定的最小间距下放置指定数量的树
    public static boolean canPlaceTrees(List<Integer> positions, int numTrees, int minDist) {
        int count = 1;  // 放置第一棵树
        int lastPos = positions.get(0);

        for (int i = 1; i < positions.size(); i++) {
            if (positions.get(i) - lastPos >= minDist) {
                count++;
                lastPos = positions.get(i);
                if (count >= numTrees) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        List<Integer> positions = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            positions.add(scanner.nextInt());
        }

        int numTrees = scanner.nextInt();

        // 对坐标进行排序
        Collections.sort(positions);

        // 二分查找最大最小间距
        int left = 1;
        int right = positions.get(positions.size() - 1) - positions.get(0);
        int result = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (canPlaceTrees(positions, numTrees, mid)) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(result);

        scanner.close();
    }
}