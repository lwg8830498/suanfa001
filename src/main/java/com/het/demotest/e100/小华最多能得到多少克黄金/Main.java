package com.het.demotest.e100.小华最多能得到多少克黄金;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int count = 0;
        if (n == 0 || m == 0) {
            count = 0;
        } else {
            for (int i = 0; i < m; i++) {
                int sum1 = 0;
                String[] splitA = String.valueOf(i).split("");
                for (int l = 0; l < splitA.length; l++) {
                    sum1 += Integer.parseInt(splitA[l]);
                }
                if (sum1 > k) {
                    break;
                }
                for (int j = 0; j < n; j++) {
                    String[] splitB = String.valueOf(j).split("");
                    int sum2 = 0;

                    for (int l = 0; l < splitB.length; l++) {
                        sum2 += Integer.parseInt(splitB[l]);
                    }
                    if (sum2 > k) {
                        break;
                    }
                    if (sum1 + sum2 <= k) {
                        count++;
                    }
                }

            }
        }
        System.out.println(count);
    }
}