package com.het.demotest.e200.计算堆栈中的剩余数字;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 输入一行数字并转换为整数列表
        List<Integer> a = new ArrayList<>();
        while (sc.hasNextInt()) {
            a.add(sc.nextInt());
        }

        List<Integer> stk = new ArrayList<>();
        stk.add(a.get(0)); // 将第一个数字添加到栈中

        // 遍历输入列表的其余元素
        for (int i = 1; i < a.size(); i++) {
            push(a.get(i), stk); // 调用push函数将元素添加到栈中
        }

        // 逆序输出栈
        for (int i = stk.size() - 1; i >= 0; i--) {
            System.out.print(stk.get(i) + " ");
        }
    }

    // 定义push函数
    public static void push(int x, List<Integer> stk) {
        int total = x;
        int n = stk.size();

        // 从后向前遍历栈
        for (int i = n - 1; i >= 0; i--) {
            total -= stk.get(i); // 减去栈顶元素
            if (total == 0) {
                // 如果剩余为0，删除栈顶的元素并将x的两倍压入栈
                for (int j = stk.size() - 1; j >= i; j--) {
                    stk.remove(j);
                }
                push(x * 2, stk); // 递归调用
                return;
            } else if (total < 0) {
                break; // 如果剩余小于0，退出循环
            }
        }

        stk.add(x); // 将x添加到栈中
    }
}