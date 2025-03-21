package com.het.demotest.e100.找朋友;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取小朋友的数量
        int N = scanner.nextInt();

        // 读取小朋友的身高
        int[] heights = new int[N];
        for (int i = 0; i < N; i++) {
            heights[i] = scanner.nextInt();
        }

        // 使用单调栈找到每个小朋友的朋友
        int[] friends = new int[N];
        Stack<Integer> stack = new Stack<>();

        // 从右向左遍历
        for (int i = N - 1; i >= 0; i--) {
            // 弹出栈顶比当前小朋友矮的元素
            while (!stack.isEmpty() && heights[stack.peek()] <= heights[i]) {
                stack.pop();
            }

            // 如果栈不为空，栈顶元素就是当前小朋友的朋友
            if (!stack.isEmpty()) {
                friends[i] = stack.peek() + 1; // 转换为1-based索引
            } else {
                friends[i] = 0; // 没有朋友
            }

            // 将当前小朋友的位置压入栈中
            stack.push(i);
        }

        // 输出结果
        for (int friend : friends) {
            System.out.print(friend + " ");
        }
    }
}