package com.het.demotest.e200.数字游戏;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuffer sb = new StringBuffer();
        // 进入循环，读取每一组输入数据，直到文件结尾
        while (scanner.hasNext()) {
            int n = scanner.nextInt(); // 牌的数量
            int m = scanner.nextInt(); // 目标除数

            Set<Integer> set = new HashSet<>(); // 使用HashSet存储前缀和的余数
            set.add(0); // 初始时插入0，表示空序列的和

            int sum = 0; // 用于计算前缀和
            int ans = 0; // 存储结果，0表示不存在满足条件的序列，1表示存在

            // 遍历所有的n张牌
            for (int i = 0; i < n; i++) {
                int x = scanner.nextInt(); // 读取每张牌的数值
                sum = (sum + x) % m; // 计算当前的前缀和并对m取余，避免sum过大

                // 如果当前的前缀和余数已经在集合中存在
                // 说明找到了一个连续序列，其和能被m整除
                if (set.contains(sum)) {
                    ans = 1; // 设置结果为1
                }

                set.add(sum); // 将当前前缀和的余数插入集合
            }

            sb.append(ans).append("\n");
        }
        System.out.print(sb);
    }
}