package com.het.demotest.e200.攀登者2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    // 定义一个函数，找出所有山峰的位置
    public static List<Boolean> findPeaks(List<Integer> heights) {
        int n = heights.size(); // 获取高度列表的大小
        List<Boolean> peaks = new ArrayList<>();
        // 初始化peaks列表
        for (int i = 0; i < n; i++) {
            peaks.add(false);
        }
        for (int i = 0; i < n; i++) {
            // 判断是否是山峰（比左右邻居都高或位于边界）
            if ((i == 0 || heights.get(i) > heights.get(i - 1)) && (i == n - 1 || heights.get(i) > heights.get(i + 1))) {
                peaks.set(i, true); // 标记为山峰
            }
        }
        return peaks; // 返回山峰位置列表
    }

    // 计算可以攀登的山峰数量
    public static int climbablePeaks(List<Integer> heights, int maxStamina) {
        int cnt = 0; // 可攀登的山峰计数
        int n = heights.size(); // 获取高度列表的大小
        List<Boolean> peaks = findPeaks(heights); // 获取山峰位置
        List<Integer> up = new ArrayList<>();
        List<Integer> down = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            up.add(Integer.MAX_VALUE);
            down.add(Integer.MAX_VALUE);
        }

        // 计算每个位置的上山和下山体力消耗
        int upStrength = 0; // 上山体力消耗累计
        int downStrength = 0; // 下山体力消耗累计
        int upFrom = -1; // 上山起始位置
        for (int i = 0; i < n; i++) {
            if (heights.get(i) == 0) {
                upStrength = 0;
                downStrength = 0;
                upFrom = i; // 从平地位置重新计算体力消耗
                continue;
            }
            if(upFrom == -1) continue;
            // 计算体力消耗

            if (heights.get(i) > heights.get(i - 1)) {
                upStrength += 2 * (heights.get(i) - heights.get(i - 1));
                downStrength += heights.get(i) - heights.get(i - 1);
            } else {
                upStrength += heights.get(i - 1) - heights.get(i);
                downStrength += 2 * (heights.get(i - 1) - heights.get(i));
            }

            up.set(i, upStrength);
            down.set(i, downStrength);

        }

        // 从右至左重新计算体力消耗
        upStrength = 0;
        downStrength = 0;
        upFrom = n;
        for (int i = n - 1; i >= 0; i--) {
            if (heights.get(i) == 0) {
                upFrom = i;
                upStrength = 0;// 从平地位置重新计算体力消耗
                downStrength = 0;
                continue;
            }
            if(upFrom == n) continue;

            if (heights.get(i) > heights.get(i + 1)) {
                upStrength += 2 * (heights.get(i) - heights.get(i + 1));
                downStrength += heights.get(i) - heights.get(i + 1);
            } else {
                upStrength += heights.get(i + 1) - heights.get(i);
                downStrength += 2 * (heights.get(i + 1) - heights.get(i));
            }

            up.set(i, Math.min(up.get(i), upStrength));
            down.set(i, Math.min(down.get(i), downStrength));

        }

        // 检查每个山峰是否可以在体力限制内攀登
        for (int i = 0; i < n; i++) {
            if (peaks.get(i) && (long) up.get(i) + down.get(i) < maxStamina) { // 使用 long 防止整数溢出
                cnt++;
            }
        }
        return cnt;
    }

    // 主函数，处理输入和输出
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String[] numberStrs = line.split(",");
        List<Integer> heights = new ArrayList<>();

        // 将分隔的字符串转换成整数
        for (String numStr : numberStrs) {
            heights.add(Integer.parseInt(numStr.trim()));  // trim()去除可能的空白字符
        }

        int maxStamina = scanner.nextInt();

        int result = climbablePeaks(heights, maxStamina);
        System.out.println(result); // 输出结果
        scanner.close(); // 关闭扫描器
    }
}