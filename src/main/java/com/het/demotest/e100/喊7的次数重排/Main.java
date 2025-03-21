package com.het.demotest.e100.喊7的次数重排;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    // 判断数字是否是7的倍数或包含7
    private static boolean isBuzz(int number) {
        return number % 7 == 0 || String.valueOf(number).contains("7");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();  // 读取输入
        String[] parts = input.split(" ");
        List<Integer> counts = new ArrayList<>();  // 存储输入的“过”的次数
        int totalBuzzes = 0;  // 记录总共的“过”的次数

        // 解析输入并计算总“过”的次数
        for (String part : parts) {
            int count = Integer.parseInt(part);
            counts.add(count);
            totalBuzzes += count;
        }

        int N = counts.size();
        int[] result = new int[N];  // 初始化结果数组

        // 遍历1到K，分配“过”的次数
        int k = 1;
        int idx = 0;
        while (totalBuzzes > 0) {
            if (isBuzz(k++)) {  // 如果需要喊“过”
                result[idx]++;  // 增加该人的“过”的次数
                totalBuzzes--;  // 更新总次数
            }
            idx = (idx + 1) % N;  // 更新下一个喊的人
        }

        // 输出结果
        for (int i = 0; i < N; i++) {
            System.out.print(result[i]);
            if (i < N - 1) {
                System.out.print(" ");  // 输出空格
            }
        }
        System.out.println();  // 换行

        scanner.close();  // 关闭扫描器
    }
}