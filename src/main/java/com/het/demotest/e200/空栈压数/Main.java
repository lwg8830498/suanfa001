package com.het.demotest.e200.空栈压数;

import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String[] numbersStr = line.split(" ");

        Stack<Integer> stack = new Stack<>();
        for (String numStr : numbersStr) {
            int num = Integer.parseInt(numStr);
            stack.push(num);

            boolean merged = false;
            do {
                merged = false;
                int n = stack.size();
                num = stack.peek();
                // 规则1：检查栈顶两个元素是否相同
                if (n >= 2 && stack.peek() == stack.get(n - 2)) {
                    stack.pop();
                    stack.pop();
                    stack.push(2 * num);
                    merged = true;
                }
                // 规则2：检查栈顶的一系列元素的和是否等于栈顶元素
                else if (n >= 3) {
                    int sum = 0;
                    int cnt = 1;
                    for (int i = n - 2; i >= 0; i--) {
                        sum += stack.get(i);
                        cnt ++;
                        if (sum > num) break;
                        else if (sum == num) {
                            while (cnt-- > 0) {
                                stack.pop();
                            }
                            stack.push(2 * num);
                            merged = true;
                            break;
                        }
                    }
                }
            } while (merged);
        }

        // 输出结果
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
        System.out.println();
    }
}