package com.het.demotest.e100.最大花费金额;

import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    // 计算最大花费金额的函数
    public static int maximizeSpending(ArrayList<Integer> prices, int budget) {
        int maxSpending = -1; // 初始化最大花费金额为 -1，表示没有找到合适的组合
        int n = prices.size(); // 获取商品价格的数量

        // 三重循环遍历所有可能的3个商品组合
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    int total = prices.get(i) + prices.get(j) + prices.get(k); // 计算当前组合的总价
                    if (total <= budget) { // 如果总价小于或等于预算
                        maxSpending = Math.max(maxSpending, total); // 更新最大花费金额
                    }
                }
            }
        }
        return maxSpending; // 返回最大花费金额
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // 创建 Scanner 对象用于读取用户输入
        ArrayList<Integer> prices = new ArrayList<>(); // 用于存储商品价格的 ArrayList

        // 输入商品价格，用逗号分隔，例如 "23,26,36,27"
        String[] input = scanner.nextLine().split(","); // 获取整行输入并按逗号分隔
        for (String price : input) {
            prices.add(Integer.parseInt(price)); // 将每个价格转换为整数并添加到 ArrayList
        }

        // 输入预算金额
        int budget = scanner.nextInt();

        // 输出满足条件的最大花费金额
        System.out.println(maximizeSpending(prices, budget));
        scanner.close(); // 关闭 Scanner 对象
    }
}