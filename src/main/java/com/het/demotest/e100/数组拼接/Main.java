package com.het.demotest.e100.数组拼接;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    // 辅助函数：将字符串按照逗号分割成整数数组，处理连续逗号的情况
    public static List<Integer> split(String str) {
        List<Integer> result = new ArrayList<>();
        String[] items = str.split(",");

        for (String item : items) {
            if (!item.isEmpty()) {  // 处理空字符串的情况
                result.add(Integer.parseInt(item));
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 输入每次读取的固定长度
        int length = scanner.nextInt();
        // 输入整数数组的数目
        int numArrays = scanner.nextInt();
        scanner.nextLine();  // 读取换行符

        // 存储所有的数组
        List<List<Integer>> arrays = new ArrayList<>();

        // 读取每个数组
        for (int i = 0; i < numArrays; i++) {
            String line = scanner.nextLine();
            arrays.add(split(line));
        }

        List<Integer> result = new ArrayList<>();
        boolean done = false;

        // 不断循环从每个数组中提取固定长度的内容
        while (!done) {
            done = true;
            for (int i = 0; i < arrays.size(); i++) {
                List<Integer> array = arrays.get(i);
                if (!array.isEmpty()) {
                    // 每次取固定长度的元素
                    int take = Math.min(length, array.size());
                    result.addAll(array.subList(0, take));
                    // 删除已取出的部分
                    array.subList(0, take).clear();
                    if (!array.isEmpty()) {
                        done = false; // 如果当前数组还有剩余元素，继续循环
                    }
                }
            }
        }

        // 输出合并后的结果
        for (int i = 0; i < result.size(); i++) {
            if (i > 0) {
                System.out.print(",");
            }
            System.out.print(result.get(i));
        }
    }
}