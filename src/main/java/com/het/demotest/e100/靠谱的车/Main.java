package com.het.demotest.e100.靠谱的车;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long N = scanner.nextLong(); // 输入表面读数
        long actualCost = calculateActualCost(N); // 计算实际费用
        System.out.println(actualCost); // 输出结果
    }

    // 计算实际费用
    private static long calculateActualCost(long N) {
        long actualCost = 0;
        long base = 1; // 当前位的基数

        while (N > 0) {
            long digit = N % 10; // 当前位的数字
            if (digit > 4) {
                digit--; // 跳过数字4
            }
            actualCost += digit * base; // 累加实际费用
            base *= 9; // 基数乘以9（9进制）
            N /= 10; // 处理下一位
        }

        return actualCost;
    }
}