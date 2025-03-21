package com.het.demotest.e100.跳房子1;

import java.util.*;

public class Main {

    // 查找满足跳跃要求的最小索引和的步数组合
    public static int[] findCombo(int target, int[] steps) {
        HashMap<Integer, Integer> stepMap = new HashMap<>(); // 使用 HashMap 存储步数及其索引
        int[] res = new int[2];
        int minIdxSum = Integer.MAX_VALUE;

        for (int i = 0; i < steps.length; i++) {
            int neededStep = target - steps[i]; // 计算需要的配对步数
            if (stepMap.containsKey(neededStep)) { // 如果需要的步数存在于 HashMap 中
                int idxSum = stepMap.get(neededStep) + i;
                if (idxSum < minIdxSum) { // 更新最小索引和
                    minIdxSum = idxSum;
                    res[0] = neededStep;
                    res[1] = steps[i];
                }
            }
            // 如果当前步数尚未存储在 HashMap 中，则将其加入
            stepMap.putIfAbsent(steps[i], i);
        }

        return res;
    }

    // 解析输入字符串，转化为整数数组
    public static int[] parseInput(String s) {
        s = s.substring(1, s.length() - 1); // 去掉方括号
        String[] numStrings = s.split(",");
        int[] arr = new int[numStrings.length];
        for (int i = 0; i < numStrings.length; i++) {
            arr[i] = Integer.parseInt(numStrings[i].trim());
        }
        return arr;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取步数数组和目标值
        String stepsInput = scanner.next();
        int target = scanner.nextInt();

        // 解析步数数组
        int[] steps = parseInput(stepsInput);

        // 查找符合条件的步数组合
        int[] res = findCombo(target, steps);

        // 输出结果
        System.out.println("[" + res[0] + ", " + res[1] + "]");

        scanner.close();
    }
}