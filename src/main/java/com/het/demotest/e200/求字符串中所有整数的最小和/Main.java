package com.het.demotest.e200.求字符串中所有整数的最小和;

import java.util.*; // 引入Java的工具包，用于使用Scanner类

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in); // 创建一个Scanner对象，用于从标准输入读取数据
        String s = in.next(); // 读取一个字符串，这里假设输入是一个连续的没有空格的字符串
        long res = 0; // 定义长整型变量res，用于存储整数的累加和

        for (int i = 0; i < s.length(); i++) { // 遍历字符串的每个字符
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') { // 如果当前字符是数字
                int r = i, sum = 0; // r用于内部循环，sum用于计算当前扫描到的整数的数值
                while (r < s.length() && s.charAt(r) >= '0' && s.charAt(r) <= '9') { // 继续向后扫描，直到非数字字符
                    sum += s.charAt(r) - '0'; // 计算当前数字字符代表的数值并累加到sum中
                    r++; // 移动到下一个字符
                }
                if (i > 0 && s.charAt(i - 1) == '-') // 如果当前数字的前一个字符是负号
                    res -= Long.parseLong(s.substring(i, r)); // 则将当前数字字符串转换为长整型并从res中减去
                else
                    res += sum; // 否则，将当前数字的累加和加到res中
                i = r - 1; // 更新外层循环的索引，跳过已经处理过的数字部分
            }
        }
        System.out.println(res); // 输出所有整数的总和
    }
}