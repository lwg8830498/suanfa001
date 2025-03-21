package com.het.demotest.e100.贪心的商人;

import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // 商品数量
        int m = in.nextInt(); // 售货天数

        int[] num = new int[n + 1]; // 存储每种商品的最大持有数量
        int[] price = new int[m + 1]; // 存储每天的商品价格

        for (int i = 1; i <= n; i++)
            num[i] = in.nextInt(); // 输入每种商品的最大持有数量

        long ans = 0; // 初始化总利润变量为0
        for (int i = 1; i <= n; i++) { // 遍历每种商品
            long res = 0; // 初始化临时利润变量
            for (int j = 1; j <= m; j++)
                price[j] = in.nextInt(); // 输入每种商品在每一天的价格
            for (int j = 1; j < m; j++) { // 遍历每一天的价格（不包括最后一天）
                if (price[j + 1] > price[j]) { // 如果第二天的价格高于当天价格
                    res += price[j + 1] - price[j]; // 计算当天买入第二天卖出的利润并累计到res
                }
            }
            ans += res * num[i]; // 计算该商品在所有天内可能获得的最大利润，并累加到总利润ans中
        }
        System.out.println(ans); // 输出商人在给定天数内可能获得的最大总利润
    }
}