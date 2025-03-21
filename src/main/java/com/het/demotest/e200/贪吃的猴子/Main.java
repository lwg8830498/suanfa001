package com.het.demotest.e200.贪吃的猴子;

import java.util.*; // 导入Java的工具包，包含扫描器等工具

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in); // 创建一个扫描器对象以读取输入数据
        int n = in.nextInt(); // 读取第一行输入，即香蕉串的总数
        int[] a = new int[n+5]; // 创建一个数组a，长度稍大于n，用来存储每串香蕉的数量
        int[] pre = new int[n+5]; // 创建一个数组pre，用来存储前缀和

        for (int i = 1; i <= n; i++) { // 从1开始迭代到n，更符合题意中的描述
            a[i] = in.nextInt(); // 读取每串香蕉的数量
            pre[i] = pre[i-1] + a[i]; // 计算前缀和，pre[i]代表从第1串到第i串香蕉的总数
        }

        int m = in.nextInt(); // 读取猴子可以获取的次数N
        int ans = 0; // 初始化答案变量，用来存储可能的最大香蕉数量

        for (int l = 0; l <= m; l++) { // 遍历所有可能的从行的开头取的次数l
            int r = m - l; // 从行末尾取的次数r
            int res = pre[l] + pre[n] - pre[n-r]; // 计算当前策略下取香蕉的总数
            ans = Math.max(ans, res); // 更新最大香蕉数
        }

        System.out.println(ans); // 输出最大香蕉数
    }
}