package com.het.demotest.e100.简单的自动曝光;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    // 存储像素值的列表
    static List<Integer> pixel = new ArrayList<>();

    // 计算添加k后的平均像素值
    static double count(int add) {
        double sum = 0; // 存储总和
        int n = pixel.size(); // 像素点的数量
        for (int p : pixel) {
            int current = p + add; // 当前像素值加上k
            if (current > 255) {
                sum += 255; // 超过255的值设为255
            } else if (current < 0) {
                sum += 0; // 小于0的值设为0
            } else {
                sum += current; // 正常值加到总和
            }
        }
        return sum / n; // 返回平均值
    }

    // 检查当前k值下的平均值是否小于等于128
    static boolean check(int add) {
        return count(add) <= 128;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine(); // 读取一行输入

        // 解析输入的像素值
        for (String s : line.split(" ")) {
            pixel.add(Integer.parseInt(s));
        }

        int l = -128, r = 128; // k的搜索范围

        // 使用二分查找来找到满足条件的最大k
        while (l < r) {
            int mid = (l + r + 1) / 2; // 计算中点
            if (check(mid)) {
                l = mid; // 有效k，向右搜索
            } else {
                r = mid - 1; // 无效k，向左搜索
            }
        }

        // 检查增加1是否能使平均值更接近128
        if (Math.abs(count(l) - 128) > Math.abs(count(l + 1) - 128)) {
            l += 1; // 更新k为l+1
        }

        System.out.println(l); // 输出结果
        scanner.close(); // 关闭扫描器
    }
}