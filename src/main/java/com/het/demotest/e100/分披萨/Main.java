package com.het.demotest.e100.分披萨;

import java.util.*;

public class Main {
    private static int num;
    private static int[][] dp;
    private static int[] v;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        num = sc.nextInt();
        dp = new int[num][num];
        v = new int[num];
        for (int i = 0; i < num; i++) {
            v[i] = sc.nextInt();
        }
        int sum = 0;
        for (int i = 0; i < num; i++) {
            sum = Math.max(sum,getMaxNum((num+i-1)%num,(i+1)%num)+v[i]);
        }
        System.out.println(sum);
    }

    public static int getMaxNum(int l, int r){
        if(dp[l][r] != 0){
            return dp[l][r];
        }
        if(v[l] < v[r]){
            r = (r+1) % num;
        }else {
            l = (num+l-1)%num;
        }
        if(l == r){
            dp[l][r] = v[l];
        }else {
            dp[l][r] = Math.max(v[l]+getMaxNum((num+l-1)%num,r),v[r]+getMaxNum(l,(r+1)%num));
        }
        return dp[l][r];
    }
}

class Main1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();  // 输入披萨块数
        long top = (long)1e18;  // 用一个极大的值初始化动态规划的最小值
        long[] v = new long[2*n];  // 存储披萨大小的数组，长度为2n，处理环形结构
        long[][] dp = new long[2*n+5][2*n+5];  // 动态规划数组
        for (int i=0; i<n; i++) {
            v[i] = in.nextLong();  // 读入每块披萨的大小
            v[n+i] = v[i];  // 复制到数组的后半部分，模拟环形结构
        }
        for (int i=0; i<dp.length; i++) {
            Arrays.fill(dp[i], -top);  // 初始化dp数组的每个元素为极小值
        }

        long ans = 0;  // 存储“吃货”可以获得的最大披萨大小总和
        for (int len = 1; len <= n; len += 2) {  // 只考虑奇数长度的区间
            for (int l = 0, r = len - 1; r < 2 * n; l++, r++) {  // 遍历所有可能的区间起点和终点
                if (len == 1) {
                    dp[l][r] = v[l];  // 如果区间长度为1，直接使用当前的披萨块大小
                } else {
                    // 状态转移方程，决定是取左端还是右端的披萨
                    dp[l][r] = Math.max(dp[l][r - 1] + v[r], dp[l + 1][r] + v[l]);
                }
                if (len == n) {
                    ans = Math.max(ans, dp[l][r]);  // 如果区间长度为n，更新最大总和
                }
                // 维护环形结构中的区间扩展
                if (l == 0 || r == 2 * n - 1) {
                    continue;
                }
                if (v[l - 1] > v[r + 1]) {
                    dp[l - 1][r] = Math.max(dp[l - 1][r], dp[l][r]);  // 向左扩展区间
                } else {
                    dp[l][r + 1] = Math.max(dp[l][r + 1], dp[l][r]);  // 向右扩展区间
                }
            }
        }
        System.out.println(ans);  // 输出“吃货”可以得到的最大披萨总和
    }
}