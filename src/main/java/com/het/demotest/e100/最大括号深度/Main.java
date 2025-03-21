package com.het.demotest.e100.最大括号深度;

import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        Stack<Character> stack = new Stack<>();
        int maxDepth = 0;

        for (char ch : s.toCharArray()) {
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
                maxDepth = Math.max(maxDepth, stack.size());
            } else {
                if (stack.isEmpty()) {
                    System.out.println(0);
                    return;
                }
                char top = stack.pop();
                if ((ch == ')' && top != '(') ||
                        (ch == '}' && top != '{') ||
                        (ch == ']' && top != '[')) {
                    System.out.println(0);
                    return;
                }
            }
        }

        // 如果栈非空，说明左括号多于右括号
        System.out.println(stack.isEmpty() ? maxDepth : 0);
    }
}