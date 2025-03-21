package com.het.demotest.e100.判断一组不等式是否满足约束并输出最大差;

import java.util.*;

public class Main {
    static double[][] a = new double[3][5];
    static double[] b = new double[3];
    static int[] x = new int[5];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        scanner.close();

        List<String> v = new ArrayList<>();
        int j = 0;
        for (int i = 0; i < s.length(); i++) { // 提取信息，由逗号或者分号分隔开
            if (s.charAt(i) == ',' || s.charAt(i) == ';') {
                v.add(s.substring(j, i));
                j = i + 1;
            }
        }
        v.add(s.substring(j));

        // 得到的信息依次填入不等式系数，变量和目标值
        int pos = 0;
        for (int i = 0; i < 3; i++) {
            for (j = 0; j < 5; j++) {
                a[i][j] = Double.parseDouble(v.get(pos++));
            }
        }
        for (int i = 0; i < 5; i++) {
            x[i] = Integer.parseInt(v.get(pos++));
        }
        for (int i = 0; i < 3; i++) {
            b[i] = Double.parseDouble(v.get(pos++));
        }

        // 然后求得不等式左边的值，更新最大差
        int f = 1, p = 1;
        double Max = 0;
        for (int i = 0; i < 3; i++) {
            double pre = 0;
            for (j = 0; j < 5; j++) {
                pre += a[i][j] * x[j]; // 求不等式左边的值
            }
            double diff = pre - b[i]; // 当前不等式差值
            if (p != 0) {
                Max = diff;
                p = 0; // 初始化不等式最大差
            }
            Max = Math.max(Max, diff); // 更新最大差
            // 根据diff的大小然后看当前不等式符号，不符合的令f=0
            if (diff > 0) {
                if (v.get(pos).equals("=") || v.get(pos).equals("<") || v.get(pos).equals("<=")) {
                    f = 0;
                }
            } else if (diff < 0) {
                if (v.get(pos).equals("=") || v.get(pos).equals(">") || v.get(pos).equals(">=")) {
                    f = 0;
                }
            } else {
                if (v.get(pos).equals(">") || v.get(pos).equals("<")) {
                    f = 0;
                }
            }
            pos++;
        }
        // 如果f为1，说明三个不等式都成立，输出true，否则输出false
        if (f != 0) {
            System.out.print("true ");
        } else {
            System.out.print("false ");
        }
        System.out.print((int) Max); // 输出最大值整数部分
    }
}