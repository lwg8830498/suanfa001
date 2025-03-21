package com.het.demotest.e100.xmzwz;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // 使用Scanner读取控制台输入
        Scanner in = new Scanner(System.in);
        // 读取一行输入，学号用空格隔开
        String[] numStrings = in.nextLine().split(",");
        // 创建整型数组存放学号，数组长度为输入学号的个数
        int[] nums = new int[numStrings.length];
        // 循环转换输入的字符串为整型
        for (int i = 0; i < numStrings.length; i++)
            nums[i] = Integer.parseInt(numStrings[i]);  // 将字符串中的学号转换成整数
        // 读取小明的学号
        int x = in.nextInt();
        // 初始化ans为0，用于记录小明应该插入的位置
        int ans = 0;
        // 遍历已排序的学号数组
        for (int y : nums) {
            // 如果遇到一个学号大于或等于小明的学号，结束循环
            if (y >= x) {
                break;
            }
            // 如果当前学号小于小明的学号，ans增加1
            ans++;
        }
        // 输出小明的位置，因为索引从0开始，所以输出时加1转换为从1开始的位置
        System.out.println(ans + 1);
    }
}