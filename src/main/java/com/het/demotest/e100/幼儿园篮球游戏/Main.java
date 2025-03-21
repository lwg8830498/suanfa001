package com.het.demotest.e100.幼儿园篮球游戏;

import java.util.*; // 导入Java的工具包，包含Scanner等类

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();  // 读取第一行输入，即老师放入的篮球编号
        String[] numStrings = line.split(","); // 分割输入的字符串，获取篮球编号数组
        Deque<Integer> dq = new ArrayDeque<>(); // 使用双端队列来模拟篮球的放入和取出
        Queue<Integer> a = new LinkedList<>(); // 队列a用于存放篮球的初始放入顺序
        for (String x: numStrings)
            a.add(Integer.parseInt(x)); // 将字符串转为整数并放入队列a
        line = in.nextLine(); // 读取第二行，即要检查的取出顺序
        numStrings = line.split(",");
        int[] b = new int[numStrings.length]; // 数组b用于存放需要检查的取出顺序
        for (int i = 0; i < numStrings.length; i++) {
            b[i] = Integer.parseInt(numStrings[i]); // 转换并存储到数组b
        }

        StringBuffer res = new StringBuffer(); // 用于存储取出篮球的操作序列（左或右）
        boolean f = true; // 标志变量，表示是否可以按要求顺序取出篮球

        for (int x : b) { // 遍历要检查的取出顺序
            while (f) {
                if (dq.size() > 0 && dq.peekFirst() == x) { // 检查队首元素是否匹配
                    dq.pollFirst(); // 如果匹配，从队首取出
                    res.append("L"); // 记录操作为从左边取出
                    break;
                } else if (dq.size() > 0 && dq.peekLast() == x) { // 检查队尾元素是否匹配
                    dq.pollLast(); // 如果匹配，从队尾取出
                    res.append("R"); // 记录操作为从右边取出
                    break;
                } else if (a.size() > 0) {
                    dq.offerLast(a.poll()); // 如果当前队列中没有匹配的，继续从a中放入篮球到队尾
                } else {
                    f = false; // 如果无法继续放入，且没有找到匹配的篮球，设置f为false
                }
            }
            if (!f) // 如果已确定无法按要求取出，中断循环
                break;
        }
        System.out.println((f ? res.toString() : "NO")); // 根据f的值输出结果或“NO”
    }
}