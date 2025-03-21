package com.het.demotest.e100.停车场车辆统计;

import java.util.*;


public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String input = in.nextLine();
        if (input.length() == 0) {
            System.out.print(0);
            return;
        }
        int len = 0;
        int res = 0;
        for (int i = 0; i < input.length(); i++){
            if (i % 2 == 0) {
                if (input.charAt(i) == '1') {
                    len++;
                }
                else {
                    int a = len / 3;
                    res += ((len % 3 == 0)? a : a + 1);
                    len = 0;
                }
            }
        }
        int a = len / 3;
        res += ((len % 3 == 0)? a : a + 1);
        System.out.println(res);
    }
}