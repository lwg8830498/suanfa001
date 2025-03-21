package com.het.demotest.e100.数字序列比大小;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int[] A = new int[N];
        int[] B = new int[N];

        for (int i = 0; i < N; i++) {
            A[i] = scanner.nextInt();
        }

        for (int i = 0; i < N; i++) {
            B[i] = scanner.nextInt();
        }

        // 对两个序列分别排序
        Arrays.sort(A);
        Arrays.sort(B);

        int score = 0; // A的得分
        int A_min = 0, B_min = 0; // A和B的最小数字指针
        int A_max = N - 1, B_max = N - 1; // A和B的最大数字指针

        // 使用双指针方式来进行比较
        while (A_min <= A_max) {
            if (A[A_min] < B[B_min]) { // 如果A的最小数字小于B的最小数字，A输1分，两个最小的数字直接匹配
                score--;
                A_min++;
                B_max--;
            } else if (A[A_min] > B[B_min]) { // 如果A的最小数字大于B的最小数字，A赢1分，两个最小的数字直接匹配
                score++;
                A_min++;
                B_min++;
            } else { // 如果A和B的最小数字相等
                if (A[A_max] > B[B_max]) { // 如果A的最大数字大于B的最大数字，A最大和B最大直接匹配，A赢1分
                    score++;
                    A_max--;
                    B_max--;
                } else { // 否则A最小和B最大匹配
                    if (A[A_min] < B[B_max])
                        score--;
                    A_min++;
                    B_max--;
                }
            }
        }

        // 输出最终的分数
        System.out.println(score);
    }
}