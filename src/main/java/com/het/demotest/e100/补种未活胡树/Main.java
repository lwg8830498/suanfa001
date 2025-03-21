package com.het.demotest.e100.补种未活胡树;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static int maxContinuousTrees(int N, int M, Set<Integer> dead, int K) {
        int left = 0, right = 0;  // 滑动窗口的左右边界
        int maxLen = 0;  // 最大连续胡杨数
        int usedK = 0;  // 当前窗口内补种的次数

        // 滑动窗口遍历所有树
        while (right < N) {
            // 如果当前胡杨未成活（编号在dead集合中），则需要补种
            if (dead.contains(right + 1)) {
                usedK++;
            }

            // 如果补种的树数超过K，移动左边界
            while (usedK > K) {
                if (dead.contains(left + 1)) {
                    usedK--;
                }
                left++;
            }

            // 更新最大连续树的数量
            maxLen = Math.max(maxLen, right - left + 1);
            right++;
        }

        return maxLen;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 输入 N, M, K
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        Set<Integer> dead = new HashSet<>();
        for (int i = 0; i < M; i++) {
            dead.add(scanner.nextInt());
        }
        int K = scanner.nextInt();

        // 计算并输出最大连续树的数量
        System.out.println(maxContinuousTrees(N, M, dead, K));

        scanner.close();
    }
}