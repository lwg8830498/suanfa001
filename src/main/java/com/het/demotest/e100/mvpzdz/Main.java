package com.het.demotest.e100.mvpzdz;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int t = Integer.parseInt(sc.nextLine());
            String[] s = sc.nextLine().split(" ");
            int[] scores = new int[t];
            for (int i = 0; i < s.length; i++) {
                scores[i] = Integer.parseInt(s[i]);
            }
            int sum = 0, max = 0;
            for (int i = 0; i < t; i++) {
                sum += scores[i];
                if (max < scores[i]) {
                    max = scores[i];
                }
            }
            while (true) {
                if (sum % max == 0) {
                    System.out.println(max);
                    break;
                } else {
                    max++;
                }
            }
        }
    }
}