package com.het.demotest.e100.传递悄悄话;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] str = in.nextLine().split(" ");  // 读取输入并按空格拆分成字符串数组
        int[] v = new int[str.length+1];  // 创建一个比输入长度多1的数组，用于存储节点值
        for (int i = 0; i < str.length; i++) {
            v[i+1] = Integer.parseInt(str[i]);  // 将字符串数组转换为整数数组，并从索引1开始存储
        }
        System.out.println(dfs(1, v));  // 从根节点开始调用dfs函数，并输出结果
    }

    static int dfs(int u, int[] v) {
        // 如果u超出范围或者u是空节点
        if (u >= v.length || v[u] == -1) {
            return 0;
        }
        // 返回u节点的值加上左右子树的最大值
        return v[u] + Math.max(dfs(u*2, v), dfs(u*2+1, v));
    }
}