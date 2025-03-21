package com.het.demotest.e200.伐木工;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        if (x <= 4) {
            System.out.println(x);
            return;
        }
        int res = x % 3;
        int num3 = x / 3;  // 分割为 3 的数量
        int num2 = 0;      // 分割为 2 的数量
        int num4 = 0;      // 分割为 4 的数量
        if (res == 1) {
            num4 = 1;
        } else if (res == 2) {
            num2++;
        }
        num3 -= num4;
        while (num2-- > 0) {
            System.out.print("2 ");
        }
        while (num3-- > 0) {
            System.out.print("3 ");
        }
        while (num4-- > 0) {
            System.out.print("4 ");
        }
    }
}