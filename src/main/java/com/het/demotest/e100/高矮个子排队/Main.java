package com.het.demotest.e100.高矮个子排队;

import java.util.Arrays; // 导入Arrays工具类，用于操作数组
import java.util.Scanner; // 导入Scanner类，用于读取输入

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // 创建Scanner对象用于读取输入
        try {
            // 读取一行输入，分割成字符串数组，然后转换为整数数组
            Integer[] a = Arrays.stream(sc.nextLine().split(" "))
                    .map(Integer::parseInt) // 将每个字符串转换为整数
                    .toArray(Integer[]::new); // 将流转换为数组
            int n = a.length; // 获取数组的长度
            boolean f = true; // 初始化一个布尔变量f，用于控制排序的方向

            // 遍历数组的前n-1个元素
            for (int i = 0; i < n - 1; i++, f = !f) {
                // 如果当前元素与下一个元素相等，或者它们的相对大小与f的值相同，则跳过不交换
                if (a[i].equals(a[i + 1]) || (a[i] > a[i + 1]) == f) {
                    continue;
                }
                // 交换当前元素与下一个元素的值
                int p = a[i];
                a[i] = a[i + 1];
                a[i + 1] = p;
            }

            // 遍历数组，打印每个元素，元素之间用空格分隔
            for (int i = 0; i < n; i++) {
                System.out.print(a[i] + " ");
            }
        } catch (Exception e) {
            // 如果在转换输入或处理过程中发生异常，打印空数组表示"[]"
            System.out.println("[]");
        }
    }
}