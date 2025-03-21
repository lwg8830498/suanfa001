package com.het.demotest.e100.查找接口成功率最优时间段;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int avg = in.nextInt();  // 读取平均失败率容忍值
        in.nextLine();  // 读取并忽略掉这行后面的换行符
        String[] str = in.nextLine().split(" ");  // 读取一行字符串并按空格分割得到失败率数组
        int n = str.length;  // 数组的长度
        int minval = Integer.MAX_VALUE;  // 用来存储数组中的最小值，初始化为最大整数
        int[] a = new int[n+1];  // 创建一个新的数组a，比原数组多一个元素
        int[] pre = new int[n+1];  // 创建一个前缀和数组，同样比原数组多一个元素

        for (int i = 1; i <= n; i++) {
            a[i] = Integer.parseInt(str[i-1]);  // 将字符串数组转换为整数并存储
            pre[i] = pre[i-1] + a[i];  // 计算前缀和
            minval = Math.min(minval, a[i]);  // 更新数组中的最小值
        }

        if (minval > avg) {
            System.out.println("NULL");  // 如果数组中的最小值大于平均失效率容忍值，直接输出NULL
            return;
        }

        List<int[]> ans = new ArrayList<>();  // 用来存储满足条件的子数组起止位置
        for (int len = n; len >= 1; len--) {  // 从最大长度开始尝试每个可能的子数组长度
            for (int l = 1, r = len; r <= n; l++, r++) {
                int sum = pre[r] - pre[l-1];  // 计算子数组的和
                if (sum <= avg * len)  // 判断平均值是否小于等于容忍值
                {
                    ans.add(new int[]{l, r});  // 如果满足条件，添加到结果列表
                }
            }
            if (ans.size() > 0) {
                break;  // 如果找到了符合条件的子数组，就不需要继续尝试更短的子数组
            }
        }

        for (int[] p : ans) {
            System.out.print((p[0]-1) + "-" + (p[1]-1) + " ");  // 输出结果，调整索引为从0开始
        }
    }
}