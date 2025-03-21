package com.het.demotest.e200.最优资源分配;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();
        String s = in.next();
        char[] chars = s.toCharArray();
        int[] index = new int[n];
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            //找到第一个可以放下的地方
            int len = 'A' == c ? 1 : 'B' == c ? 2 : 8;
            for (int j = 0; j < n; j++) {
                if (index[j] + len <= m) {
                    index[j] += len;
                    break;
                }
            }
        }
        char one = '1';
        char zero = '0';
        for (int i = 0; i < n; i++) {
            int oneLen = index[i];
            StringBuilder sb = new StringBuilder();
            //拼1
            for (int j = 0; j < oneLen; j++) {
                sb.append(one);
            }
            //拼0
            for (int j = 0; j < m - oneLen; j++) {
                sb.append(zero);
            }
            System.out.println(sb);
        }

    }
}