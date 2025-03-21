package com.het.demotest.e100.解压报文;

import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static String decompress(String s) {
        Stack<Integer> counts = new Stack<>();  // 用于存储重复次数的栈
        Stack<String> resultStack = new Stack<>();  // 用于存储中间结果的栈
        StringBuilder current = new StringBuilder();  // 当前正在处理的字符串
        int i = 0;

        while (i < s.length()) {
            if (Character.isDigit(s.charAt(i))) {  // 如果是数字，计算重复次数
                int count = 0;
                while (Character.isDigit(s.charAt(i))) {  // 处理多位数字
                    count = count * 10 + (s.charAt(i) - '0');
                    i++;
                }
                counts.push(count);  // 将重复次数压入栈中
            } else if (s.charAt(i) == '[') {  // 遇到 '['，将当前字符串压入栈，并准备处理内部的子字符串
                resultStack.push(current.toString());
                current = new StringBuilder();
                i++;
            } else if (s.charAt(i) == ']') {  // 遇到 ']'，开始重复处理
                String temp = current.toString();
                current = new StringBuilder(resultStack.pop());  // 获取前一个上下文字符串
                int count = counts.pop();  // 获取重复次数
                for (int j = 0; j < count; j++) {  // 将当前字符串重复指定次数
                    current.append(temp);
                }
                i++;
            } else {  // 普通字符直接添加到当前字符串
                current.append(s.charAt(i));
                i++;
            }
        }

        return current.toString();  // 返回最终解压后的字符串
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String compressedMessage = scanner.next();  // 输入压缩报文
        System.out.println(decompress(compressedMessage));  // 输出解压后的报文
        scanner.close();
    }
}