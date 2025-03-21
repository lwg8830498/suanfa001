package com.het.demotest.e100.机器人仓库搬砖;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();  // 读取一整行的内容
        String[] numStrings = line.split(" ");  // 将一行内容按空格分割成字符串数组
        int[] nums = new int[numStrings.length];

        // 将字符串数组中的每个元素转换为整数并存储到nums数组中
        for (int i = 0; i < numStrings.length; i++) {
            nums[i] = Integer.parseInt(numStrings[i]);
        }

        int tim_top = 8;  // 定义最大工作时间，即8小时

        // 如果砖堆数量超过8（即仓库数量超过8），则无法在8小时内完成，输出-1
        if (nums.length > tim_top) {
            System.out.println("-1");
            return;
        }

        // 初始化二分查找边界
        int l = 1;  // 最小可能的每小时充能数
        int r = Arrays.stream(nums).max().getAsInt();  // 最大可能的每小时充能数（砖块最大数）
        int ans = r;  // 初始化答案为最大充能数

        // 二分查找过程
        while (l <= r) {
            int mid = (l + r) / 2;  // 计算中点，尝试的每小时能量格数
            int tim = 0;  // 计算总共需要的小时数

            // 计算在当前能量格数mid下，总共需要多少小时来完成所有搬砖任务
            for (int x : nums) {
                tim += (x + mid - 1) / mid;  // 向上取整计算需要的小时数
            }

            // 判断是否满足在8小时内完成
            if (tim <= tim_top) {
                ans = mid;  // 更新答案为较小的充能数
                r = mid - 1;  // 尝试更小的充能数
            } else {
                l = mid + 1;  // 需要更多充能数，因为当前充能数无法在8小时内完成任务
            }
        }

        // 输出最小的满足条件的每小时充能数
        System.out.println(ans);
    }
}