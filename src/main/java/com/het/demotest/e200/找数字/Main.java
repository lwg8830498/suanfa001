package com.het.demotest.e200.找数字;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.close();


        // 找到最右边的0，且这个0右边至少有一个1
        int c = n;
        int c0 = 0; // 计数器，右边0的数量
        int c1 = 0; // 计数器，右边1的数量

        // 统计最低位连续的0的数量
        while (((c & 1) == 0) && (c != 0)) {
            c0++;
            c >>= 1;
        }

        // 统计接下来的1的数量
        while ((c & 1) == 1) {
            c1++;
            c >>= 1;
        }

        // 如果没有0左边的1，说明已经是最大的数
        if (c0 + c1 == 31 || c0 + c1 == 0) {
            System.out.println("-1"); // 表示不能找到更大的数
            return;
        }

        // 位置 p 是我们需要翻转的最右边的0的位置
        int p = c0 + c1;
        n |= (1 << p); // 翻转最右边的0
        n &= ~((1 << p) - 1); // 将p右边的所有位清零
        n |= (1 << (c1 - 1)) - 1; // 在右边放入c1-1个1

        System.out.println(n);
    }
}