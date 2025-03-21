package com.het.demotest.e200.找最小数;

import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static String removeNDigits(String num, int n) {
        Stack<Character> stack = new Stack<>();
        int toRemove = n;

        for (char digit : num.toCharArray()) {
            while (!stack.isEmpty() && toRemove > 0 && stack.peek() > digit) {
                stack.pop();
                toRemove--;
            }
            stack.push(digit);
        }

        // 如果还有剩余的需要移除的字符
        while (toRemove > 0 && !stack.isEmpty()) {
            stack.pop();
            toRemove--;
        }

        // 将栈中的字符重新组成结果
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.insert(0, stack.pop());
        }

        // 移除结果中的前导零
        int start = 0;
        while (start < result.length() && result.charAt(start) == '0') {
            start++;
        }
        result = new StringBuilder(result.substring(start));

        // 如果结果为空，则返回 "0"
        return result.length() == 0 ? "0" : result.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String num = scanner.next();
        int n = scanner.nextInt();
        System.out.println(removeNDigits(num, n));
    }
}