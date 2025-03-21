package com.het.demotest.e200.水仙花数;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 预先存储的水仙花数表
        Map<Integer, int[]> table = new HashMap<>();
        table.put(3, new int[]{153, 370, 371, 407});
        table.put(4, new int[]{1634, 8208, 9474});
        table.put(5, new int[]{54748, 92727, 93084});
        table.put(6, new int[]{548834});
        table.put(7, new int[]{1741725, 4210818, 9800817, 9926315});

        // 创建扫描器获取用户输入
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        // 检查 n 是否在 3 到 7 之间，或者 m 是否为负数
        if (n < 3 || n > 7 || m < 0) {
            System.out.println(-1);  // 非法输入
        } else if (m >= table.get(n).length) {
            // 如果 m 超出该位数水仙花数的数量，输出最后一个水仙花数乘以 m
            System.out.println((long)table.get(n)[table.get(n).length - 1] * m);
        } else {
            // 否则，输出第 m 个水仙花数
            System.out.println(table.get(n)[m]);
        }

        // 关闭扫描器
        scanner.close();
    }
}