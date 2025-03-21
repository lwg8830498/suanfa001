package com.het.demotest.e100.密码输入检测;

import java.util.*;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '<') {
                if (!stack.isEmpty()){
                    stack.pop();
                }
            } else {
                stack.push(String.valueOf(s.charAt(i)));
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        while (!stack.isEmpty()) {
            stringBuilder.append(stack.pop());
        }
        StringBuilder result = stringBuilder.reverse();
        boolean flag = true;
        if (result.length() < 8) {
            flag = false;
        }
        int d = 0, n = 0, w = 0, m = 0;
        for (int i = 0; i < result.length(); i++) {
            if (Character.isDigit(result.charAt(i))) {
                d++;
            }
            if (Character.isLowerCase(result.charAt(i))) {
                n++;
            }
            if (Character.isUpperCase(result.charAt(i))) {
                w++;
            }
            if (Character.isSpaceChar(result.charAt(i))) {
                m++;
            }
        }

        if (d == 0 || n == 0 || w == 0 || result.length() - d - n - w - m == 0) {
            flag = false;
        }
        System.out.println(result + "," + flag);
    }
}