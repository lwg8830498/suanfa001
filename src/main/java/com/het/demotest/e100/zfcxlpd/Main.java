package com.het.demotest.e100.zfcxlpd;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next(); // 读取第一个字符串到s
        String l = scanner.next(); // 读取第二个字符串到l
        scanner.close();

        int lastIdx = -1; // 初始化lastIdx为-1，表示找不到时的返回值
        int m = s.length(); // 获取字符串s的长度
        int n = l.length(); // 获取字符串l的长度
        int toMatch = 0;   // 初始化要匹配的位置为0

        // 遍历字符串l
        for (int i = 0; i < n; i++) {
            if (toMatch < m && s.charAt(toMatch) == l.charAt(i)) { // 如果当前字符匹配
                toMatch++; // 增加匹配的位置
                lastIdx = i; // 更新最后匹配的索引
            }
            if (toMatch >= m) {
                break; // 如果全部字符都已匹配，则退出循环
            }
        }

        System.out.println(lastIdx);
    }
}