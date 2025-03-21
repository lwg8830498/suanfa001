package com.het.demotest.e200.导师请吃火锅;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // 读取菜的数量
        int m = scanner.nextInt(); // 读取手速

        int top = 20005; // 数组的最大范围
        int[] v = new int[top]; // 初始化数组

        for (int i = 0; i < n; i++) { // 遍历所有的菜
            int x = scanner.nextInt(); // 下菜的时间
            int y = scanner.nextInt(); // 煮熟所需的时间
            v[x + y]++; // 标记“刚好合适”的时间点
        }

        int last = -m - 5; // 记录上次捞菜的时间，初始值设置比第一秒前更早
        int ans = 0; // 记录捞到的“刚好合适”的菜的数量

        for (int i = 0; i < top; i++) { // 遍历时间数组
            if (v[i] > 0 && i - last >= m) { // 检查是否可以捞菜
                last = i; // 更新上次捞菜的时间
                ans++; // 计数器加1
            }
        }

        System.out.println(ans); // 输出最终结果
    }
}