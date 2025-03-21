package com.het.demotest.e200.抢7游戏;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    private static BigInteger[] a = new BigInteger[10010];
    private static BigInteger[] b = new BigInteger[10010];

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        // 初始化数组中的所有BigInteger为0
        for (int i = 0; i < 10010; i++) {
            a[i] = BigInteger.ZERO;
            b[i] = BigInteger.ZERO;
        }

        // 设置a[m] = 1
        a[m] = BigInteger.ONE;

        // 执行给定的操作
        for (int i = m; i > 7; i--) {
            for (int j = 1; j < 3; j++) {
                a[i - j] = a[i - j].add(b[i]);
                b[i - j] = b[i - j].add(a[i]);

            }
        }

        // 输出结果
        System.out.println(b[7]);

    }
}