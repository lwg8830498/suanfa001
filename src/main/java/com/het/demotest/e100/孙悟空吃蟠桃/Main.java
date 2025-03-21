package com.het.demotest.e100.孙悟空吃蟠桃;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取每棵桃树上的桃子数量
        String[] pilesStr = scanner.nextLine().split(" ");
        int[] piles = new int[pilesStr.length];
        for (int i = 0; i < pilesStr.length; i++) {
            piles[i] = Integer.parseInt(pilesStr[i]);
        }

        // 读取守卫离开的时间H
        int H = scanner.nextInt();

        // 如果H小于桃树的数量，直接返回0
        if (H < piles.length) {
            System.out.println(0);
            return;
        }

        // 二分查找最小速度K
        int left = 1;
        int right = getMax(piles);
        int result = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (canFinish(piles, mid, H)) {
                result = mid; // 记录当前可行的速度
                right = mid - 1; // 尝试更小的速度
            } else {
                left = mid + 1; // 需要更大的速度
            }
        }

        System.out.println(result);
    }

    // 获取数组中的最大值
    private static int getMax(int[] piles) {
        int max = 0;
        for (int pile : piles) {
            if (pile > max) {
                max = pile;
            }
        }
        return max;
    }

    // 判断以速度K是否能在H小时内吃完所有桃子
    private static boolean canFinish(int[] piles, int K, int H) {
        int time = 0;
        for (int pile : piles) {
            time += (pile + K - 1) / K; // 向上取整
        }
        return time <= H;
    }
}