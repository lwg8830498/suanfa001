package com.het.demotest.e200.孙悟空吃蟠桃;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);  // 创建扫描器对象用于接收输入
        String line = in.nextLine();  // 读取第一行输入，桃树上桃子的数量
        String[] numStrings = line.split(" ");  // 分割字符串得到每棵树上桃子的数量
        int[] nums = new int[numStrings.length];  // 创建整数数组存放转换后的数量
        for (int i = 0; i < numStrings.length; i++) {
            nums[i] = Integer.parseInt(numStrings[i]);  // 将字符串转换为整数
        }
        int tim = in.nextInt();  // 读取第二行输入，守卫离开的时间H
        if (tim < nums.length) {  // 如果守卫离开的时间少于桃树的数量，则无法吃完所有桃子
            System.out.println(0);
            return;
        }
        int l = 1, r = 10000, ans = r;  // 设定吃桃速度K的搜索范围，初始假设最大速度为10000
        while (l <= r) {
            int mid = (l + r) / 2;  // 计算中间速度
            int wast_time = 0;
            for (int x : nums) {
                wast_time += (x + mid - 1) / mid;  // 计算以速度mid需要的时间来吃完所有桃子
            }
            if (wast_time <= tim) {  // 如果可以在守卫回来前吃完所有桃子
                ans = mid;  // 更新最小速度
                r = mid - 1;  // 尝试更小的速度
            } else {
                l = mid + 1;  // 增大速度，尝试找到一个可行的速度
            }
        }
        System.out.println(ans);  // 输出最小的速度K
    }
}