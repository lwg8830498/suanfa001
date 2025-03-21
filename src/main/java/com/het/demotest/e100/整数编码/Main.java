package com.het.demotest.e100.整数编码;

import java.util.*;

public class Main {
    public static char to16(String a) {// 二进制转16进制
        int res = 0;
        for (int i = 0; i < 4; i++) {
            res = res * 2 + a.charAt(i) - '0';// 二进制转10进制
        }
        return res < 10 ? (char)(res + '0') : (char)(res - 10 + 'A');// 十进制转十六进制
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long x = scanner.nextLong();
        List<String> v = new ArrayList<>();// 用于存放7位的二进制串的数组
        if (x == 0) {// 特判x是不是等于0，如果是直接进数组，因为后面x进不了循环
            v.add("0");
        }
        StringBuilder s = new StringBuilder();// 用于接收二进制
        int cc = 0;//用于计数 7位为一组
        while (x != 0) {
            s.append(x % 2);// 模二取余
            x >>= 1;// 移位
            if (++cc == 7) {// cc+1的同时判断是不是7，如果是就要加入v，并初始化cc和s
                cc = 0;
                v.add(s.toString());
                s.setLength(0);
            }
        }
        if (cc != 0) {// 最后如果cc非零 说明还有最后一个二进制串 加入v
            v.add(s.toString());
        }
        cc = 0;
        for (String str : v) {// 遍历这个字符串数组
            while (str.length() < 7) {
                str += '0';// 长度不超过7的末尾补上‘0’
            }// 判断这个二进制串是不是数组中的最后一位 如果是 末尾补‘0’，否则补‘1’
            if (++cc == v.size()) {
                str += '0';
            } else {
                str += '1';
            }
            str = new StringBuilder(str).reverse().toString();// 字符串倒序 就是最后的二进制串
            System.out.print(to16(str.substring(0, 4)) + "" + to16(str.substring(4)));// 分成两段str[0,4], str[4,7]转成16进制
        }
        scanner.close();
    }
}