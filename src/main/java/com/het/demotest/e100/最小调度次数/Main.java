package com.het.demotest.e100.最小调度次数;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = Integer.parseInt(sc.nextLine());
            String[] orders = new String[2 * n];
            for (int i = 0; i < 2 * n; i++) {
                orders[i] = sc.nextLine();
            }
            int ans = handle(orders);
            System.out.println(ans);
        }
    }

    private static int handle(String[] orders) {
        int ans = 0;
        int size = 0;
        boolean hasHeadIn = false;
        for (String str : orders) {
            if (str.contains("remove")) {
                if (hasHeadIn) {
                    hasHeadIn = false;
                    ans++;
                }
                size--;
            } else if (str.contains("head")) {
                if (size != 0) {
                    hasHeadIn = true;
                }
                size++;
            } else {
                size++;
            }
        }
        return ans;
    }
}