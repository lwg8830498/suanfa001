package com.het.demotest.e100.查找充电设备;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        int pmax = sc.nextInt();
        int[] dp = new int[pmax+1];
        Arrays.fill(dp,0);
        for (int i = 0; i < n; i++) {
            for (int i1 = 0; i1 <= pmax; i1++) {
                if(i1 >= dp[i1]+nums[i]){
                    dp[i1] = Math.max(dp[i1]+nums[i],nums[i]);
                }else if(i1 >= nums[i]){
                    dp[i1] = Math.max(dp[i1],nums[i]);
                }

            }
        }
        System.out.println(dp[pmax]);
    }
}
