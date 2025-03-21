package com.het.demotest.e200.数字加减游戏;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int s = scanner.nextInt(); // 初始数字
        int t = scanner.nextInt(); // 目标数字
        int a = scanner.nextInt(); // 数字 a
        int b = scanner.nextInt(); // 数字 b

        int res = solveMethod(s, t, a, b);
        System.out.println(res);
    }

    public static int solveMethod(int s, int t, int a, int b) {
        int diff = Math.abs(s - t); // 计算 s 和 t 的差值
        int min1 = 0; // 第一种情况：使用减法
        int tmp = diff;

        // 尝试通过减法调整差值
        while (tmp % b != 0) {
            tmp -= a;
            min1 += 1;
        }

        int min2 = 0; // 第二种情况：使用加法
        tmp = diff;

        // 尝试通过加法调整差值
        while (tmp % b != 0) {
            tmp += a;
            min2 += 1;
        }

        // 返回两种情况中的最小值
        return Math.min(min1, min2);
    }
}