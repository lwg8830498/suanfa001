package com.het.demotest.e100.仿LISP计算;

import java.util.Scanner;

public class Main {

    // 跳过字符串中的空格
    private static void skipSpaces(String s, int[] idx) {
        while (idx[0] < s.length() && Character.isWhitespace(s.charAt(idx[0]))) {
            idx[0]++;
        }
    }

    // 实现向下取整的除法
    private static int floorDiv(int a, int b) {
        // 前提条件: b != 0
        int q = a / b;
        int r = a % b;
        if ((r != 0) && ((r < 0) != (b < 0))) {
            q--;
        }
        return q;
    }

    // 递归解析表达式
    private static int parseExpression(String s, int[] idx, boolean[] error) {
        skipSpaces(s, idx);
        if (idx[0] >= s.length()) {
            error[0] = true;
            return 0;
        }

        if (s.charAt(idx[0]) == '(') {
            idx[0]++; // 跳过 '('
            skipSpaces(s, idx);

            // 解析操作符
            StringBuilder op = new StringBuilder();
            while (idx[0] < s.length() && Character.isAlphabetic(s.charAt(idx[0]))) {
                op.append(s.charAt(idx[0]++));
            }

            if (!op.toString().equals("add") && !op.toString().equals("sub") &&
                    !op.toString().equals("mul") && !op.toString().equals("div")) {
                error[0] = true;
                return 0;
            }

            skipSpaces(s, idx);

            // 解析第一个参数
            int val1 = parseExpression(s, idx, error);
            if (error[0]) return 0;

            skipSpaces(s, idx);

            // 解析第二个参数
            int val2 = parseExpression(s, idx, error);
            if (error[0]) return 0;

            skipSpaces(s, idx);

            if (idx[0] >= s.length() || s.charAt(idx[0]) != ')') {
                error[0] = true;
                return 0;
            }
            idx[0]++; // 跳过 ')'

            // 计算
            switch (op.toString()) {
                case "add":
                    return val1 + val2;
                case "sub":
                    return val1 - val2;
                case "mul":
                    return val1 * val2;
                case "div":
                    if (val2 == 0) {
                        error[0] = true;
                        return 0;
                    } else {
                        return floorDiv(val1, val2);
                    }
                default:
                    error[0] = true;
                    return 0;
            }
        } else {
            // 解析整数
            return parseInteger(s, idx, error);
        }
    }

    // 解析整数，包括负数
    private static int parseInteger(String s, int[] idx, boolean[] error) {
        int sign = 1;
        if (s.charAt(idx[0]) == '-') {
            sign = -1;
            idx[0]++;
        }
        if (idx[0] >= s.length() || !Character.isDigit(s.charAt(idx[0]))) {
            error[0] = true;
            return 0;
        }
        int num = 0;
        while (idx[0] < s.length() && Character.isDigit(s.charAt(idx[0]))) {
            num = num * 10 + (s.charAt(idx[0]++) - '0');
        }
        return sign * num;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        int[] idx = {0};
        boolean[] error = {false};
        int result = parseExpression(s, idx, error);
        if (error[0]) {
            System.out.println("error");
        } else {
            System.out.println(result);
        }
    }
}