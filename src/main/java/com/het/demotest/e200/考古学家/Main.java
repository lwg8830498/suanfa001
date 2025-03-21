package com.het.demotest.e200.考古学家;

import java.util.*;
public class Main {
    public static void main(String[] args) {
        // 使用Scanner类从标准输入读取数据
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // 读取石碑碎片的数量
        TreeSet<String> st = new TreeSet<>(); // 使用TreeSet以确保结果的唯一性和自动排序
        boolean[] vis = new boolean[n]; // 访问数组，标记每个碎片是否被使用过
        String[] v = new String[n]; // 存储每个碎片的文字内容
        for(int i = 0; i < n; i++) {
            v[i] = in.next(); // 读取每个碎片的内容
        }
        dfs(0, v, vis, new StringBuffer(), st, n); // 开始深度优先搜索生成排列
        for (String x : st) {
            System.out.println(x); // 输出所有排列结果
        }
    }

    // 深度优先搜索函数，用于生成所有可能的字符串排列
    static void dfs(int cnt, String[] v, boolean[] vis, StringBuffer tmp, TreeSet<String> st, int n)
    {
        if (cnt == n) // 如果当前已排列的字符串数等于碎片数
        {
            st.add(tmp.toString()); // 将生成的字符串添加到结果集中
            return; // 返回终止当前递归
        }
        for (int i = 0; i < n; i++) // 遍历每个碎片
        {
            if (!vis[i]) // 如果当前碎片未被使用
            {
                vis[i] = true; // 标记为已使用
                tmp.append(v[i]); // 将碎片内容添加到当前排列字符串中
                dfs(cnt + 1, v, vis, tmp, st, n); // 递归继续添加下一个碎片
                vis[i] = false; // 回溯，撤销当前碎片的使用标记
                tmp.delete(tmp.length() - v[i].length(), tmp.length()); // 从排列字符串中移除当前碎片内容
            }
        }
    }
}