package com.het.demotest.e200.最长的指定瑕疵度的元音子串;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // 定义一个HashSet存储元音字符，用于快速查找
        HashSet<Character> st = new HashSet<>();
        st.add('a');
        st.add('A');
        st.add('e');
        st.add('E');
        st.add('i');
        st.add('I');
        st.add('o');
        st.add('O');
        st.add('u');
        st.add('U');

        // 使用Scanner来读取输入
        Scanner in = new Scanner(System.in);
        int cnt = in.nextInt(); // 读取预期的瑕疵度
        StringBuilder sb = new StringBuilder(in.next()); // 读取字符串
        int n = sb.length(); // 字符串长度
        sb.insert(0, ' '); // 前面插入空格，使索引从1开始
        sb.append(' '); // 后面插入空格，使索引到n结束

        // 创建一个列表存储所有非元音字符的位置
        List<Integer> v = new ArrayList<>();
        v.add(0); // 初始化，加入0作为首元素
        for (int i = 1; i <= n; i++) {
            // 如果当前字符不是元音，则记录其位置
            if (!st.contains(sb.charAt(i))) {
                v.add(i);
            }
        }
        v.add(n+1);

        int ans = 0; // 初始化答案为0
        // 遍历非元音字符的位置，找出最长的满足瑕疵度的元音子串
        for (int p = 1; p + cnt < v.size(); p++) {
            int pl = v.get(p-1)+1, pr = v.get(p+cnt)-1;
            if (st.contains(sb.charAt(pl)) && st.contains(sb.charAt(pr)))   // 保证两端是元音字符
                ans = Math.max(ans, pr - pl + 1);
        }

        // 输出结果
        System.out.println(ans);
    }
}