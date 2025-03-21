package com.het.demotest.e100.求最多可以派出多少支团队;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        long[] ability = new long[n];
        for (int i = 0; i < n; i++) {
            ability[i] = in.nextInt();
        }
        long limit = in.nextInt();
        Arrays.sort(ability);

        int l = 0, r = n - 1;
        int ans = 0;
        while (l < r && ability[r] >= limit) {
            ans++;
            r--;
        }

        while (l < r) {
            if (ability[l] + ability[r] >= limit) {
                ans++;
                l++;
                r--;
            } else {
                l++;
            }

        }
        if (l == r) {
            if (ability[r] > limit) {
                ans++;
            }

        }
        System.out.println(ans);
    }
}