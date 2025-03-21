package com.het.demotest.e100.找终点;

import java.util.Scanner; // 导入Scanner类用于读取输入
import java.util.Arrays; // 导入Arrays类用于操作数组

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // 创建Scanner对象用于读取输入
        // 读取一行输入，分割成字符串数组，然后转换为整数数组
        Integer[] a = Arrays.stream(sc.nextLine().split(" "))
                .map(Integer::parseInt) // 将每个字符串转换为整数
                .toArray(Integer[]::new); // 将流转换为数组
        int n = a.length; // 获取数组的长度
        int ans = 1000000000; // 初始化答案变量ans为一个很大的数，用于记录最短路径长度

        // 遍历数组的前半部分的每个元素（从索引1开始）
        for (int i = 1; i < n / 2; i++) {
            int ret = 1; // 初始化ret为1，用于记录从当前位置出发的路径长度
            int x = i; // 从数组的第i个元素开始
            // 循环直到当前位置x等于数组的最后一个元素的索引
            while (x != n - 1) {
                x = x + a[x]; // 根据数组a中的值更新位置x
                if (x > n - 1) { // 如果新的位置x超出了数组的界限
                    ret = 1000000000; // 将ret设置为一个很大的数，表示无效路径
                    break; // 跳出循环
                }
                ret += 1; // 否则，增加ret的值，表示路径长度增加
            }
            // 更新答案ans为当前的最短路径
            ans = Math.min(ans, ret);
        }

        // 如果ans仍然是初始值，表示没有找到有效路径，将ans设置为-1
        if (ans == 1000000000) {
            ans = -1;
        }
        // 输出最终的最短路径长度
        System.out.println(ans);
        sc.close(); // 关闭Scanner对象
    }
}