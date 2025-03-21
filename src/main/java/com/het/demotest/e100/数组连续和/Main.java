package com.het.demotest.e100.数组连续和;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int x = sc.nextInt();
        int[] a = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            a[i] = sc.nextInt();
        }
        int l = 1, tmp = 0; // l表示首指针，tmp表示子区间的和， ans表示和小于x的子区间个数
        long ans = 0;
        for (int r = 1; r <= n; r++) { //双指针尾指针移动
            tmp += a[r]; // 子区间加入尾数
            while (tmp >= x && l <= r) { // 加了数如果导致子区间和不小于x了，需要移动首指针以满足和小于x，此时子区间和减掉首指针的数
                tmp -= a[l++];
            }
            if (tmp <= x) {
                ans += (r - l + 1); // 如果此时的子区间和小于x，即满足条件了，如果此时l=3,r=6,那么满足条件的子区间就有[3,6],[4,6],[5,6],[6,6],个数就是r-l+1
            }
        }
        ans = (long) (n + 1) * n / 2 - ans; //总区间个数-小于x的个数 就是大于等于x的个数
        System.out.println(ans);
    }
}