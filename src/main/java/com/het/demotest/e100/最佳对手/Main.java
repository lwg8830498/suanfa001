package com.het.demotest.e100.最佳对手;

import java.util.*;

//实力差距最小总和
public class Main {
    static final int INF = Integer.MAX_VALUE / 2;  // 定义一个较大的常数，表示无穷大
    static int[][] dp = new int[55][55];  // dp[i][j]表示前i个队伍中匹配j组时的最小实力差值和
    static int[] team = new int[55];  // 保存各队伍的实力值

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int d = scanner.nextInt();

        // 初始化动态规划数组，将所有值设置为无穷大，表示无法匹配
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], INF);
        }
        dp[0][0] = 0;
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;  // 初始化匹配0组时的差值和为0
        }

        // 输入队伍的实力值
        for (int i = 1; i <= n; i++) {
            team[i] = scanner.nextInt();
        }

        // 对队伍实力值进行排序，方便后续匹配
        Arrays.sort(team, 1, n + 1);

        int maxPairs = 0;  // 保存当前能匹配的最多组数
        int ans = INF;  // 保存最小的实力差值和

        // 动态规划求解
        for (int i = 2; i <= n; i++) {  // 从第二个队伍开始
            for (int j = 1; j <= n / 2; j++) {  // j表示匹配的组数，最多为n/2组
                dp[i][j] = dp[i - 1][j];  // 不匹配当前队伍，继承上一个状态

                // 如果当前队伍和前一个队伍的实力差在允许范围内，则尝试匹配
                if (team[i] - team[i - 1] <= d) {
                    // 选择匹配当前队伍和前一个队伍，更新最小差值和
                    dp[i][j] = Math.min(dp[i][j], dp[i - 2][j - 1] + team[i] - team[i - 1]);

                    // 更新最大匹配组数和最小差值和
                    if (dp[i][j] != INF && j >= maxPairs) {
                        if (j > maxPairs) {  // 如果匹配组数更多，更新最大匹配组数和最小差值和
                            maxPairs = j;
                            ans = dp[i][j];
                        } else {  // 如果匹配组数相同，选择较小的差值和
                            ans = Math.min(ans, dp[i][j]);
                        }
                    }
                }
            }
        }

        // 输出结果
        if (ans != INF) {
            System.out.println(ans);  // 输出最小的实力差值和
        } else {
            System.out.println(-1);  // 如果无法匹配任何组，输出-1
        }

        scanner.close();
    }
}