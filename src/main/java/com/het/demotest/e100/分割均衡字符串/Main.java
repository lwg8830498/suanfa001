package com.het.demotest.e100.分割均衡字符串;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        int right = 0;
        int sum = 0;
        int xNum = 0, yNum = 0;
        while (right < line.length()) {
            if (line.charAt(right) == 'X') {
                xNum++;
            } else {
                yNum++;
            }
            if (xNum == yNum) {
                sum++;
                xNum = 0;
                yNum = 0;
            }
            right++;
        }
        System.out.println(sum);
    }
}