package com.het.demotest.e100.查找众数及中位数;

import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    // 函数：计算数组的中位数
    public static int findMedian(List<Integer> nums) {
        // 对数组进行排序
        Collections.sort(nums);
        int n = nums.size();
        // 如果数组长度为奇数，返回中间元素
        if (n % 2 == 1) {
            return nums.get(n / 2);
        } else {
            // 如果数组长度为偶数，返回中间两个元素的平均值
            return (nums.get(n / 2 - 1) + nums.get(n / 2)) / 2;
        }
    }

    public static void main(String[] args) throws IOException {
        // 读取输入
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();

        // 定义一个整型列表
        List<Integer> arr = new ArrayList<>();
        String[] inputs = line.split(" ");
        for (String input : inputs) {
            arr.add(Integer.parseInt(input));
        }

        // 步骤1：计算每个元素的频率
        Map<Integer, Integer> frequency = new HashMap<>();
        for (int num : arr) {
            frequency.put(num, frequency.getOrDefault(num, 0) + 1);
        }

        // 步骤2：找到最高的频率
        int maxFreq = Collections.max(frequency.values());

        // 步骤3：收集所有具有最高频率的元素（众数）
        List<Integer> modes = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : frequency.entrySet()) {
            if (entry.getValue() == maxFreq) {
                modes.add(entry.getKey());
            }
        }

        // 步骤4：计算众数组成的新数组的中位数
        int median = findMedian(modes);

        // 输出结果
        System.out.println(median);
    }
}