package com.het.demotest.e100.报数游戏;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // 创建扫描器对象，获取用户输入
        Scanner sc = new Scanner(System.in);
        // 获取输入的整数 M
        int M = sc.nextInt();
        // 输出计算结果
        System.out.println(solution(M));
    }

    /**
     * 解题函数，返回剩余人的编号
     * @param M 输入的数 M
     * @return 剩余人的编号字符串
     */
    public static String solution(int M) {
        // 检查输入是否符合要求
        if (M <= 1 || M > 100) {
            return "ERROR!";
        }

        // 初始化人的编号列表（1到100）
        List<Integer> people = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            people.add(i);
        }

        // 开始按照规则淘汰人
        int index = 0;
        while (people.size() >= M) {
            // 计算下一个被淘汰的人的索引
            index = (index + M - 1) % people.size();
            // 移除该人
            people.remove(index);
        }

        // 将剩余人的编号转换为逗号分隔的字符串
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < people.size(); i++) {
            result.append(people.get(i));
            if (i < people.size() - 1) {
                result.append(",");
            }
        }

        // 返回结果字符串
        return result.toString();
    }
}