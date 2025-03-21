package com.het.demotest.e100.租车骑绿道;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int result = 0;
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int[] weight = new int[n];
        for (int i = 0; i < n; i++) {
            weight[i] = sc.nextInt();
        }

        // 对weight排序
        Arrays.sort(weight);

        // 双游标从左右向中间找相加小于等于m的数
        int lp = 0;
        int rp = n - 1;
        while (lp <= rp) {
            if (weight[lp] + weight[rp] <= m) {
                lp++;
            }
            rp--;
            result++;
        }
        System.out.println(result);
    }
}