package com.het.demotest.e100.最大N个数与最小N个数的和;

import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt(); // 读取数组的大小 M
        TreeSet<Integer> st = new TreeSet<>(); // 使用 TreeSet 自动去重和排序
        boolean flag = true;
        for (int i = 0; i < m; i++)
        {
            int x = in.nextInt(); // 读取每个数组元素
            st.add(x); // 将元素添加到 TreeSet 中，自动去重
            if (x<0 || x>1000)      // 判断输入的数是否在0-1000之间
                flag = false;
        }
        int n = in.nextInt(); // 读取 N，表示需要计算的最大、最小N个数
        if (n<0)        // 判断输入的数是否大于0
        {
            flag = false;
        }
        if (st.size() < 2 * n || !flag)
        {
            System.out.println(-1); // 如果去重后的元素少于2N，则输出-1
            return; // 结束程序
        }
        int ans = 0; // 用于存储和的变量
        Iterator<Integer> iterator = st.iterator(); // 创建迭代器用于遍历 TreeSet
        for (int i = 0; i < n; i++) {
            ans += iterator.next(); // 获取迭代器指向的当前最小值
        }
        Iterator<Integer> reverseIterator = st.descendingIterator();        // 创建逆序迭代器用于遍历 TreeSet
        for (int i = 0; i < n; i++) {
            ans += reverseIterator.next(); // 获取迭代器指向的当前最大值
        }
        System.out.println(ans); // 输出计算结果
    }
}