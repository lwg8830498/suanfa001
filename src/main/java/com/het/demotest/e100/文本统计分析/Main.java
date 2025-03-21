package com.het.demotest.e100.文本统计分析;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // 读取所有的字符串
        Scanner in = new Scanner(System.in);
        StringBuilder s = new StringBuilder();
        // 将所有输入内容逐行读取并存入StringBuilder s中
        while (in.hasNext()) {
            s.append(in.nextLine() + "\n");
        }
        int n = s.length(); // 获取字符串s的长度
        int[] tag = new int[n]; // 用于标记字符串中的字符是否属于字符串或注释
        int last = -1; // 记录上一个字符串的起始位置

        // 先找到字符串并标记
        for (int i = 0; i < n; i++) {
            if (last == -1) { // 当前未在字符串内
                if (s.charAt(i) == '\'' || s.charAt(i) == '"') { // 遇到单引号或双引号
                    if (ck(i, s))
                        last = i; // 记录字符串的起始位置
                }
            } else {
                // 匹配到结束的引号
                if (s.charAt(i) == s.charAt(last) && ck(i, s)) {
                    // 将字符串内的字符全部标记为1
                    for (int p = last; p <= i; p++) {
                        tag[p] = 1; // 标记为1
                    }
                    last = -1; // 重置last，表示当前不在字符串内
                }
            }
        }

        // 寻找注释并标记
        for (int i = 0; i < n - 1; i++) {
            if (tag[i] > 0) // 如果是字符串，跳过
            {
                continue;
            }
            // 检查注释的开始'--'
            if (s.charAt(i) == '-' && s.charAt(i + 1) == '-' && ck(i, s)) {
                int p = i;
                // 将注释内的字符标记为2，直到行尾
                while (p + 1 < n && s.charAt(p) != '\n') {
                    p++;
                }
                for (int j = i; j <= p; j++) {
                    tag[j] = 2; // 标记为2
                }
            }
        }

        List<Integer> fen = new ArrayList<>(); // 记录分号的位置
        fen.add(-1); // 在开头添加一个-1，方便后续处理
        for (int i = 0; i < n; i++) {
            if (tag[i] > 0) // 如果是字符串或注释，跳过
            {
                continue;
            }
            if (s.charAt(i) == ';') // 找到分号
            {
                fen.add(i); // 记录分号的位置
            }
        }
        fen.add(n); // 在末尾添加一个n，方便后续处理

        int ans = 0; // 记录非空语句的数量
        // 遍历每个分号间的区域
        for (int i = 1; i < fen.size(); i++) {
            int l = fen.get(i - 1) + 1, r = fen.get(i) - 1; // 取当前分号区间的左右边界
            int cod = 0; // 标记该区间是否包含非空字符
            for (int j = l; j <= r; j++) {
                // 如果该字符不是空格、制表符、注释或换行符
                if (s.charAt(j) != ' ' && s.charAt(j) != '\t' && tag[j] != 2 && s.charAt(j) != '\n') {
                    cod = 1; // 标记该区间为包含非空字符
                }
            }
            ans += cod; // 将该区间的结果加到ans中
        }

        System.out.println(ans); // 输出非空语句的数量
    }

    // 判断是否是转义字符，如果不是返回true，是返回false
    static boolean ck(int p, StringBuilder s) {
        int res = 0; // 记录反斜杠的数量
        // 从位置p-1开始向前检查，统计连续反斜杠的数量
        for (int i = p - 1; i >= 0 && s.charAt(i) == '\\'; i--) {
            res++;
        }
        // 如果反斜杠数量为偶数，则返回true，否则返回false
        return res % 2 == 0;
    }
}