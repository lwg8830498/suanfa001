package com.het.demotest.e100.恢复数字序列;

import java.util.Scanner;

public class Main {
    static int[] a = new int[10]; // 用于存储打乱字符串中数字的出现次数
    static int[] current = new int[10]; // 用于存储当前连续正整数的数字出现次数

    // 检查当前数字计数是否与目标计数一致
    static boolean check() {
        for (int i = 0; i < 10; i++) {
            if (a[i] != current[i]) // 如果任一数字的计数不匹配，返回 false
            {
                return false;
            }
        }
        return true; // 所有数字的计数匹配，返回 true
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next(); // 读取打乱的字符串
        int n = scanner.nextInt(); // 读取序列长度

        // 统计打乱字符串中每个数字的出现次数
        for (char c : s.toCharArray()) {
            a[c - '0']++; // 将字符转换为对应的整数，并增加计数
        }

        int start = 1; // 序列的起始值
        int end = n; // 序列的结束值

        // 初始化 current 数组，统计从 1 到 n 的所有数字的出现次数
        for (int i = 1; i <= n; i++) {
            String numStr = String.valueOf(i); // 将当前数字转换为字符串
            for (char c : numStr.toCharArray()) {
                current[c - '0']++; // 增加当前数字中每个字符的计数
            }
        }

        // 当 current 数组与 a 数组不一致时，继续调整
        while (!check()) {
            String startStr = String.valueOf(start++); // 将起始值转换为字符串并增加
            for (char c : startStr.toCharArray()) {
                current[c - '0']--; // 减少对应数字的计数
            }
            String endStr = String.valueOf(++end); // 将结束值加 1 并转换为字符串
            for (char c : endStr.toCharArray()) {
                current[c - '0']++; // 增加对应数字的计数
            }
        }

        System.out.println(start); // 输出最小的连续正整数
        scanner.close(); // 关闭扫描器
    }
}