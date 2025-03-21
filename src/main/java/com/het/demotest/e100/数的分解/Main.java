package com.het.demotest.e100.数的分解;

import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in); // 创建Scanner对象用于读取输入
        long sum = in.nextLong(); // 读取输入的整数n
        boolean f = false; // 标志变量，用于记录是否找到了分解方式
        List<Integer> ans = new ArrayList<>(); // 存储分解的连续整数序列

        for (long m = 2; ; m++) // 从2开始尝试每个可能的m值，即连续整数的数量
        {
            if ((2 * sum) % m != 0) // 如果2*sum不是m的倍数，则该m不可能作为分解的连续数个数
            {
                continue; // 跳过当前循环，尝试下一个m
            }

            long y = 2 * sum / m - m + 1; // 计算可能的序列的第一个数字
            if (y <= 0) {
                break; // 如果y小于等于0，则说明无法再进行分解，终止循环
            }

            if (y % 2 == 0) // y必须是偶数，以确保从正整数开始
            {
                f = true; // 设置找到分解的标志为true
                System.out.print(sum + "=" + y/2); // 输出结果的格式，首先是原始数与第一个数字
                for (long x = y / 2 + 1; x < y / 2 + m; x++) // 从计算出的起始数字开始，连续添加m个数
                    System.out.print("+" + x); // 输出剩余的加号和数字
                break; // 找到了最小的m后退出循环
            }
        }

        if (!f) // 如果没有找到分解的序列
        {
            System.out.println("N"); // 如果没有找到分解的序列，输出"N"
        }
    }
}