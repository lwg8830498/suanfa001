package com.het.demotest.e100.最少量线段覆盖;

import java.util.*;

class Main {
    // 定义线段结构体
    static class Line {
        int l, r;
        public Line(int l, int r) {
            this.l = l;
            this.r = r;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 读取线段数量
        Line[] lines = new Line[n];

        // 读取每条线段的起点和终点
        for (int i = 0; i < n; i++) {
            String[] input = sc.next().split(",");
            int l = Integer.parseInt(input[0]);
            int r = Integer.parseInt(input[1]);
            lines[i] = new Line(l, r);
        }

        // 对线段按照左端点升序，右端点降序排序
        Arrays.sort(lines, (a, b) -> {
            if (a.l == b.l) {
                return b.r - a.r;
            }
            return a.l - b.l;
        });

        // 使用栈进行线段覆盖判断
        Stack<int[]> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            int l = lines[i].l;
            int r = lines[i].r;

            if (stack.isEmpty()) {
                stack.push(new int[]{l, r});
                continue;
            }

            int lastR = stack.peek()[1];
            if (l >= lastR) {
                // 当前线段与栈顶线段无交集
                stack.push(new int[]{l, r});
            } else if (r > lastR) {
                // 当前线段与栈顶线段有交集
                while (l <= stack.peek()[0]) {
                    stack.pop();
                }
                stack.push(new int[]{stack.peek()[1], r});
            }
        }

        // 输出最少线段数量
        System.out.println(stack.size());
    }
}