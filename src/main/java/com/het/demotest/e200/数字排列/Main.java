package com.het.demotest.e200.数字排列;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Set<Integer> numbers = new HashSet<>(); // 用于存储不重复的数字
        int maxNum = -1;

        // 读取输入并处理
        String input = scanner.nextLine();
        String[] tokens = input.split(",");

        for (String token : tokens) {
            int t = Integer.parseInt(token.trim());

            // 检查数字范围
            if (t < 1 || t > 9 || numbers.contains(t) ||
                    (t == 2 && numbers.contains(5)) || (t == 5 && numbers.contains(2)) ||
                    (t == 6 && numbers.contains(9)) || (t == 9 && numbers.contains(6))) {
                System.out.println("-1");
                return;
            }
            numbers.add(t); // 插入到集合中
            maxNum = Math.max(maxNum, t); // 更新最大数字
        }

        if (numbers.size() != 4) { // 确保有四个不同的数字
            System.out.println("-1");
            return;
        }

        // 替换 5 为 2 和 9 为 6
        if (numbers.contains(5)) {
            numbers.remove(5);
            numbers.add(2);
        }
        if (numbers.contains(9)) {
            numbers.remove(9);
            numbers.add(6);
        }

        List<Integer> numList = new ArrayList<>(numbers); // 将集合转换为列表
        List<Integer> results = new ArrayList<>(); // 存储生成的数字

        // 添加一位数字
        for (int num : numList) {
            results.add(num);
            if (num == 2) {
                results.add(5); // 添加 5
            } else if (num == 6) {
                results.add(9); // 添加 9
            }
        }

        // 生成两位数字
        for (int i = 0; i < numList.size(); i++) {
            for (int j = 0; j < numList.size(); j++) {
                if (i == j) {
                    continue; // 避免使用相同的数字
                }
                results.add(numList.get(i) * 10 + numList.get(j)); // 组合数字
                if (numList.get(i) == 2) {
                    results.add(5 * 10 + numList.get(j)); // 2 替换为 5
                } else if (numList.get(i) == 6) {
                    results.add(9 * 10 + numList.get(j)); // 6 替换为 9
                } else {
                    if (numList.get(j) == 2) {
                        results.add(numList.get(i) * 10 + 5); // 2 替换为 5
                    } else if (numList.get(j) == 6) {
                        results.add(numList.get(i) * 10 + 9); // 6 替换为 9
                    }
                }
            }
        }

        Collections.sort(results); // 排序
        if (maxNum > results.size()) {
            System.out.println(results.get(results.size() - 1)); // 如果 N 超过大小，返回最后一个元素
        } else {
            System.out.println(results.get(maxNum - 1)); // 输出第 N 个数字
        }

        scanner.close(); // 关闭扫描器
    }
}