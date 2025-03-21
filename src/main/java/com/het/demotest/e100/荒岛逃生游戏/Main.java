package com.het.demotest.e100.荒岛逃生游戏;

import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Stack<Integer> right = new Stack<>();
        Stack<Integer> left = new Stack<>();

        while (scanner.hasNextInt()) {
            int x = scanner.nextInt();
            if (x == 0) { // 检查输入异常
                System.out.println(-1);
                return;
            }
            if (x > 0) {
                // 向右逃生，入栈
                right.push(x);
            } else {
                // 向左逃生，处理与右逃生者的决斗
                int power = -x; // 战斗力为正数
                while (!right.isEmpty() && power > 0) {
                    if (right.peek() > power) {
                        // 右逃生者胜出
                        right.push(right.pop() - power);
                        power = 0;
                    } else if (right.peek() < power) {
                        // 左逃生者胜出
                        power -= right.pop();
                    } else {
                        // 战斗力相同，两者均死去
                        right.pop();
                        power = 0;
                    }
                }
                // 如果仍有剩余的战斗力，说明这个人逃生成功
                if (power > 0) {
                    left.push(-power); // 负数表示向左逃生者
                }
            }
        }

        // 所有成功逃生的人数是右逃生者和左逃生者
        int count = right.size() + left.size();
        System.out.println(count);
    }
}