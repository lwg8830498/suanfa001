package com.het.demotest.e200.会议室占用时间;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {

    // 用于读取输入并解析为会议时间段的列表
    public static List<int[]> readIntervals() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // 读取会议数量
        List<int[]> intervals = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int start = scanner.nextInt();
            int end = scanner.nextInt();
            intervals.add(new int[]{start, end});
        }
        scanner.close();
        return intervals;
    }

    // 合并时间段的函数
    public static List<int[]> mergeIntervals(List<int[]> intervals) {
        if (intervals.isEmpty()) {
            return Collections.emptyList();
        }

        // 按开始时间排序
        Collections.sort(intervals, Comparator.comparingInt(a -> a[0]));

        List<int[]> merged = new ArrayList<>();
        for (int[] interval : intervals) {
            // 如果列表不为空且当前会议的开始时间小于等于前一个会议的结束时间
            if (!merged.isEmpty() && interval[0] <= merged.get(merged.size() - 1)[1]) {
                // 合并时间段
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], interval[1]);
            } else {
                // 添加新的时间段
                merged.add(interval);
            }
        }
        return merged;
    }

    // 输出合并后的时间段
    public static void printIntervals(List<int[]> intervals) {
        for (int[] interval : intervals) {
            System.out.print(interval[0] + " " + interval[1]);
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // 读取输入数据
        List<int[]> intervals = readIntervals();

        // 合并时间段
        List<int[]> mergedIntervals = mergeIntervals(intervals);

        // 输出合并后的结果
        printIntervals(mergedIntervals);
    }
}