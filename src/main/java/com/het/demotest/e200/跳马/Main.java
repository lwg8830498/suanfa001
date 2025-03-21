package com.het.demotest.e200.跳马;

import java.util.*;

// 跳马问题
public class Main {
    // 定义棋盘大小和棋盘内容
    static int n, m;
    static String[] mp;
    // 定义动态规划数组dp，用来存储从某一马到其他任何一个点的最短步数
    static int[][][] dp;

    // 程序入口
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();  // 读入行数
        m = in.nextInt();  // 读入列数
        mp = new String[n];  // 初始化棋盘
        dp = new int[900][30][30];  // 初始化动态规划数组

        // 初始化dp数组，使用一个很大的数，表示无限大
        for (int i = 0; i < 900; i++) {
            for (int j = 0; j < 30; j++) {
                for (int k = 0; k < 30; k++) {
                    dp[i][j][k] = (int)1e9;
                }
            }
        }

        // 读入棋盘数据
        for (int i = 0; i < n; i++) {
            mp[i] = in.next();
        }

        int cnt = 0;  // 马的计数器
        // 遍历棋盘上每一格
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                if (mp[x].charAt(y) == '.')  // 如果是空格，则继续
                {
                    continue;
                }
                int k = mp[x].charAt(y) - '0';  // 获取马的等级
                cnt++;  // 马的数量加1
                dfs(cnt, x, y, 0, k);  // 从当前位置开始进行深度优先搜索
            }
        }

        int ans = (int)1e9;  // 初始化答案为一个很大的数
        // 计算所有马汇聚到同一个点的最小步数
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                int sum = 0;  // 步数之和
                for (int i = 1; i <= cnt; i++) {
                    sum += dp[i][x][y];  // 累加每匹马到当前位置的步数
                }
                ans = Math.min(ans, sum);  // 取最小值
            }
        }

        // 如果没有可行的解，输出-1
        if (ans >= (int)1e9) {
            ans = -1;
        }
        System.out.println(ans);  // 输出结果
    }

    // 马的可能的移动方向数组，分别对应8个可能的跳跃方向
    static int[] dx = {1, 2, 2, 1, -1, -2, -2, -1};
    static int[] dy = {2, 1, -1, -2, -2, -1, 1, 2};

    // 深度优先搜索，用于计算从某一位置开始，马能到达的所有位置的最小步数
    static void dfs(int id, int x, int y, int val, int num) {
        // 剪枝条件，如果剩余步数小于0或当前已找到的步数更优，则返回
        if (num < 0 || dp[id][x][y] <= val) {
            return;
        }
        dp[id][x][y] = val;  // 更新到达当前位置的最少步数
        // 遍历所有可能的方向
        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            // 确保新位置在棋盘范围内
            if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                continue;
            }
            dfs(id, nx, ny, val + 1, num - 1);  // 继续搜索
        }
    }
}