package com.het.demotest.e200.解密犯罪时间;

import java.util.*;

public class Main {
    static List<Integer> last, now;
    static TreeSet<Integer> num;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        num = new TreeSet<>();
        // 将时间字符串中的每个数字添加到集合中
        num.add(s.charAt(0) - '0');
        num.add(s.charAt(1) - '0');
        num.add(s.charAt(3) - '0');
        num.add(s.charAt(4) - '0');
        last = read(s);
        now = new ArrayList<>(last);

        // 开始时间为00:00，用于生成所有可能的时间
        List<Integer> lis = new ArrayList<>();
        lis.add(0);
        lis.add(0);
        dfs(lis, 0);
        print(now);
    }

    // 将输入的时间字符串转换为小时和分钟的整数列表
    static List<Integer> read(String s) {
        List<Integer> res = new ArrayList<>();
        int x = (s.charAt(0) - '0') * 10 + (s.charAt(1) - '0');
        int y = (s.charAt(3) - '0') * 10 + (s.charAt(4) - '0');
        res.add(x);
        res.add(y);
        return res;
    }

    // 打印时间的格式化输出
    static void print(List<Integer> l) {
        System.out.println(String.format("%02d:%02d", l.get(0), l.get(1)));
    }

    // 检查时间是否合法（小时和分钟是否在有效范围内）
    static boolean ck(List<Integer> l) {
        return l.get(0) < 24 && l.get(1) < 60;
    }

    // 计算时间差，以分钟为单位
    static int cal(List<Integer> a, List<Integer> b) {
        int h = b.get(0) - a.get(0);
        int m = b.get(1) - a.get(1);
        if (m < 0) {
            m += 60;
            h--;
        }
        if (h < 0) {
            h += 24;
        }
        int ans = h * 60 + m;
        if (ans == 0) {
            ans = 24 * 60; // 如果时间相同，视为第二天的相同时间
        }
        return ans;
    }

    // 深度优先搜索，尝试所有可能的时间组合
    static void dfs(List<Integer> lis, int p) {
        if (p == 4) { // 如果已设置完四位（小时的十位、个位，分钟的十位、个位）
            if (ck(lis) && cal(last, lis) < cal(last, now)) {
                now = new ArrayList<>(lis); // 更新找到的最近时间
            }
            return;
        }

        for (int x : num) {
            List<Integer> tmp = new ArrayList<>(lis);
            if (p == 0) {
                lis.set(0, lis.get(0) + 10 * x);
            } else if (p == 1) {
                lis.set(0, lis.get(0) + x);
            } else if (p == 2) {
                lis.set(1, lis.get(1) + 10 * x);
            } else {
                lis.set(1, lis.get(1) + x);
            }
            dfs(lis, p + 1);
            lis = new ArrayList<>(tmp); // 回溯，恢复到上一步的时间设置
        }
    }
}