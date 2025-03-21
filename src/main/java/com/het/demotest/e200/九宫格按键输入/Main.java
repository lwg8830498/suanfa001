package com.het.demotest.e200.九宫格按键输入;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();  // 输入按键序列

        // T9键盘映射
        Map<Character, String> t9 = new HashMap<>();
        t9.put('1', ",.");
        t9.put('2', "abc");
        t9.put('3', "def");
        t9.put('4', "ghi");
        t9.put('5', "jkl");
        t9.put('6', "mno");
        t9.put('7', "pqrs");
        t9.put('8', "tuv");
        t9.put('9', "wxyz");
        t9.put('0', " ");  // 0 对应空格

        boolean isNumericMode = true;  // 默认模式是数字模式
        StringBuilder output = new StringBuilder();  // 用于存储输出结果
        char prevChar = '\0';  // 用于记录上一个按键字符
        int repeatCount = 0;   // 用于记录按键重复次数

        // 遍历输入的字符
        for (char c : input.toCharArray()) {
            if (c == '#') {
                // 切换模式前，如果在字母模式且有未输出的字符，先输出该字符
                if (!isNumericMode && prevChar != '\0') {
                    output.append(t9.get(prevChar).charAt(repeatCount % t9.get(prevChar).length()));
                    prevChar = '\0';  // 重置上一个按键
                    repeatCount = 0;  // 重置按键次数
                }
                // 切换数字模式和字母模式
                isNumericMode = !isNumericMode;
            } else if (c == '/') {
                // 字母模式下，遇到 '/' 输出当前按键所对应的字符
                if (!isNumericMode && prevChar != '\0') {
                    output.append(t9.get(prevChar).charAt(repeatCount % t9.get(prevChar).length()));
                    prevChar = '\0';  // 重置上一个按键
                    repeatCount = 0;  // 重置按键次数
                }
            } else if (isNumericMode) {
                // 数字模式下，直接输出按键对应的字符
                output.append(c);
            } else {
                // 字母模式下
                if (c == prevChar) {
                    // 如果是同一个按键，增加重复次数，循环字母
                    repeatCount++;
                } else {
                    // 如果是不同的按键，输出前一个按键的结果
                    if (prevChar != '\0') {
                        output.append(t9.get(prevChar).charAt(repeatCount % t9.get(prevChar).length()));
                    }
                    // 开始记录新的按键
                    prevChar = c;
                    repeatCount = 0;  // 重置按键次数
                }
            }
        }

        // 如果最后的按键还有未输出的字符，输出它
        if (!isNumericMode && prevChar != '\0') {
            output.append(t9.get(prevChar).charAt(repeatCount % t9.get(prevChar).length()));
        }

        // 输出结果
        System.out.println(output.toString());

        sc.close();
    }
}