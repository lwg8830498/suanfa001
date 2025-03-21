package com.het.demotest.e100.考勤信息;

import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in); // 创建Scanner对象来读取输入
        int t = in.nextInt(); // 读取测试用例的数量
        in.nextLine(); // 读取并丢弃剩余的换行符

        // 处理每个测试用例
        while (t-- > 0) {
            String line = in.nextLine(); // 读取一行数据，代表一组考勤记录
            String[] v = line.split(" "); // 将考勤记录分割成数组，每个元素是一个考勤状态
            boolean f = true; // 标志变量，初始设置为true，表示可能获得出勤奖
            int cnt_absent = 0; // 缺勤次数的计数器

            // 遍历所有考勤记录
            for (int i = 0; i < v.length; i++) {
                if (v[i].equals("leaveearly")) // 将早退转换为迟到，统一处理
                    v[i] = "late";
                if (v[i].equals("absent")) // 计算缺勤次数
                    cnt_absent++;

                // 检查连续迟到的情况
                if (i >= 1 && v[i].equals("late") && v[i-1].equals("late"))
                    f = false; // 如果有连续的迟到，设置f为false

                // 检查任意连续7次考勤的规则
                if (i >= 6) {
                    int cnt_present = 0; // 统计7次考勤中的正常上班次数
                    for (int j = 0; j < 7; j++) // 计算从当前考勤向前数的7次考勤
                        cnt_present += v[i-j].equals("present") ? 1 : 0;
                    if (cnt_present < 4) // 如果正常上班次数少于4，即不良记录超过3次
                        f = false; // 设置f为false
                }
            }

            // 检查总缺勤次数是否超过1次
            if (cnt_absent > 1)
                f = false; // 如果超过1次，设置f为false

            // 输出结果，根据f的值输出"true"或"false"
            System.out.print(f ? "true " : "false ");
        }
    }
}