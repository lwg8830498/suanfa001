package com.het.demotest.e100.部门人力分配;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int tim_top = in.nextInt();  // 读取时间上限 M
        in.nextLine();
        String line = in.nextLine();  // 读取需求工作量数组
        String[] numStrings = line.split(" ");
        TreeMap<Integer, Integer> mp_yuan = new TreeMap<>();  // 使用 TreeMap 存储每个需求工作量及其出现的次数
        for (String numString : numStrings) {
            int num = Integer.parseInt(numString);
            mp_yuan.put(num, mp_yuan.getOrDefault(num, 0) + 1);  // 统计每种工作量的需求次数
        }

        int l = mp_yuan.lastKey(), r = (int)2e9, ans = r;  // 二分查找的初始左界为最大需求工作量，右界设为一个足够大的数
        while (l <= r) {
            int mid = l + (r - l) / 2;;  // 计算中值，即假设的每月人力
            TreeMap<Integer, Integer> mp = new TreeMap<>(mp_yuan);  // 复制原始数据，以便多次使用
            int tim = 0;  // 用来计数已使用的月份数
            while (mp.size() > 0) {  // 当还有未分配的工作时继续
                tim++;
                int a = mp.firstKey();  // 取出最小的工作量
                mp.put(a, mp.get(a) - 1);
                if (mp.get(a) == 0) mp.remove(a);  // 如果当前工作量已经分配完，则移除
                if (mp.size() > 0) {
                    Integer b = mp.floorKey(mid - a);  // 尝试找到与 a 配对的第二个需求，使得两者之和不超过 mid
                    if (b != null) {
                        mp.put(b, mp.get(b) - 1);
                        if (mp.get(b) == 0) mp.remove(b);  // 如果 b 需求也分配完，则移除
                    }
                }
            }

            if (tim <= tim_top) {  // 如果用的月份数不超过时间上限，尝试减少人力
                ans = mid;
                r = mid - 1;
            } else {  // 否则增加人力
                l = mid + 1;
            }
        }
        System.out.println(ans);  // 输出计算得到的最小人力需求
    }
}