package com.het.demotest.e200.最长子字符串的长度2;

import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next(); // 从输入读取字符串
        int cntl=0, cnto=0, cntx=0;
        // 遍历字符串计算 'l', 'o', 'x' 的总数
        for (char c : s.toCharArray()) {
            if (c == 'l') {
                cntl++;
            } else if (c == 'o') {
                cnto++;
            } else if (c == 'x') {
                cntx++;
            }
        }
        // 使用模运算确定字符出现次数的奇偶性
        cntl %= 2;
        cnto %= 2;
        cntx %= 2;
        // 如果所有特定字符均为偶数次，直接返回整个字符串长度
        if (cntl + cnto + cntx == 0) {
            System.out.println(s.length());
            return;
        }

        int minVal = s.length(); // 初始化最小值为字符串长度
        s = s + s; // 将字符串复制并拼接到自身末尾，模拟环形
        Map<List<Integer>, Integer> mp = new HashMap<>(); // 使用map记录每种字符奇偶性组合的最早出现位置
        int nowl=0, nowo=0, nowx=0; // 当前的 'l', 'o', 'x' 字符计数
        mp.put(Arrays.asList(0, 0, 0), -1); // 初始状态

        // 遍历扩展后的字符串
        for (int i = 0; i < s.length(); i++) {
            nowl += s.charAt(i) == 'l' ? 1 : 0;
            nowo += s.charAt(i) == 'o' ? 1 : 0;
            nowx += s.charAt(i) == 'x' ? 1 : 0;
            // 更新当前状态的奇偶性
            nowl %= 2;
            nowo %= 2;
            nowx %= 2;
            // 计算需要找的前缀状态
            int lastl = nowl ^ cntl, lasto = nowo ^ cnto, lastx = nowx ^ cntx;
            // 如果此前缀状态已经存在，则更新最小值
            if (mp.containsKey(Arrays.asList(lastl, lasto, lastx))) {
                minVal = Math.min(minVal, i - mp.get(Arrays.asList(lastl, lasto, lastx)));
            }
            // 记录当前状态的位置
            mp.put(Arrays.asList(nowl, nowo, nowx), i);
        }
        // 计算结果
        int ans = s.length() / 2 - minVal;
        System.out.println(ans);
    }
}