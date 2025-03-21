package com.het.demotest.e100.字符串分割;

import java.util.Scanner;

public class Main {
    public static String tran(String a) {
        int cnta = 0, cntA = 0; // 分别用于计算a中小写字母数量和大写字母的数量
        StringBuilder _a = new StringBuilder(); // 将a中所有大写字母变小写字母
        StringBuilder _A = new StringBuilder(); // 将a中所有小写字母变大写字母
        for (char i : a.toCharArray()) {
            if (i >= 'a' && i <= 'z') { // 当前是小写字母
                cnta++;                 // cnta计数
                _a.append(i);           // _a添加该字母
                _A.append((char) (i - 'a' + 'A')); //_A添加转大写的字母
            } else if (i >= 'A' && i <= 'Z') {     //当前是大写字母
                cntA++;                            // cntA计数
                _a.append((char) (i - 'A' + 'a')); // _a添加转小写的字母
                _A.append(i);                      // _A添加该字母
            } else {                // 不是字母
                _a.append(i);       // _A,_a添加该字母
                _A.append(i);
            }
        }
        if (cnta > cntA) return _a.toString();      // cnta大输出转小写的
        else if (cnta < cntA) return _A.toString(); // cntA大输出转大写的
        else return a;                              // 一样输出本身
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt();
        String s = scanner.next();
        int id = 0;
        while (id < s.length() && s.charAt(id) != '-') id++; // 找第一个'-'的位置
        System.out.print(s.substring(0, id));   // 输出第一个子串     
        StringBuilder _s = new StringBuilder(); // 用于记录子串
        int cc = 0;                             // 用于计数
        for (int i = id; i < s.length(); i++) {
            if (s.charAt(i) == '-') continue; //忽略'-'
            _s.append(s.charAt(i));
            if (++cc == k) { // 数到K，输出处理后的子串并初始化_s和cc
                System.out.print("-" + tran(_s.toString()));
                _s = new StringBuilder();
                cc = 0;
            }
        }
        if (cc > 0) {
            System.out.print("-" + tran(_s.toString())); // 如果cc非负，输出剩余处理后的子串
        }
        scanner.close();
    }
}