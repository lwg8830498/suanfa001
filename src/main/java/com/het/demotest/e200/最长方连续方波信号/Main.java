package com.het.demotest.e200.最长方连续方波信号;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 输入信号串
        String s = scanner.nextLine();
        int start = -1, end = -1; // 当前信号段的起始和结束位置
        int idx = 0;
        int n = s.length();
        int l = -1, r = -1; // 用于记录最长合法交替方波的起始和结束位置

        // 遍历整个字符串
        while (idx < n) {
            // 找到合法信号段的起始点：必须是 '01' 开头
            while (idx < n - 1 && (s.charAt(idx) != '0' || s.charAt(idx + 1) != '1')) {
                idx++;
            }

            // 如果到达最后一个字符，结束
            if (idx == n - 1) {
                break;
            }
            start = idx; // 记录信号段的起始位置

            // 查找信号段的结束点，找到 '00' 作为结束
            while (idx < n - 1 && (s.charAt(idx) != '0' || s.charAt(idx + 1) != '0')) {
                idx++;
            }

            // 如果到达最后一个字符且没有以 '0' 结束，则跳出循环
            if (idx == n - 1 && s.charAt(idx) != '0') {
                break;
            }
            end = idx; // 记录信号段的结束位置

            // 检查信号段是否是合法的完全交替方波（没有连续的 '1'）
            boolean valid = true;
            for (int i = start; i < end; i++) {
                if (s.charAt(i) == '1' && s.charAt(i + 1) == '1') {
                    valid = false;
                    break;
                }
            }

            // 如果是合法的并且长度比当前记录的最长交替方波更长，更新记录
            if (valid && (r - l < end - start)) {
                l = start;
                r = end;
            }
        }

        // 输出结果
        if (l == -1) {
            System.out.println(-1);
        } else {
            System.out.println(s.substring(l, r + 1));
        }

        scanner.close();
    }
}