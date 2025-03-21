package com.het.demotest.e100.分糖果;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong(); // 输入糖果数
        int steps = 0; // 记录操作次数

        while (n != 1) {
            if (n % 2 == 0) {
                // 如果是偶数，直接除以2
                n /= 2;
            } else {
                // 如果是奇数，选择加1或减1
                if (n == 3) {
                    // 特殊情况：3只能减1
                    n -= 1;
                } else if ((n + 1) % 4 == 0) {
                    // 如果加1后是4的倍数，选择加1
                    n += 1;
                } else {
                    // 否则选择减1
                    n -= 1;
                }
            }
            steps++; // 每次操作计数
        }

        System.out.println(steps); // 输出最少操作次数
    }
}