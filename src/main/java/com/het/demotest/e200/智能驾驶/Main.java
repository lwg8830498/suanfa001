package com.het.demotest.e200.智能驾驶;

import java.util.*;
public class Main {
    // 定义移动方向，分别为右、下、左、上
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 读取地图的大小，n为行数，m为列数
        String[] numStrings = in.nextLine().split(",");
        int n = Integer.parseInt(numStrings[0]);
        int m = Integer.parseInt(numStrings[1]);

        // 初始化地图数组
        int[][] mp = new int[n][m];
        for (int i = 0; i < n; i++) {
            numStrings = in.nextLine().split(",");
            for (int j = 0; j < m; j++) {
                mp[i][j] = Integer.parseInt(numStrings[j]); // 读取每个格子的值
            }
        }

        // 初始化从终点到各点的最短距离数组
        int[][] dis = new int[n][m];
        boolean[][] vis = new boolean[n][m]; // 访问标记数组
        for (int i = 0; i < n; i++) {
            Arrays.fill(dis[i], Integer.MAX_VALUE); // 初始距离设为极大值
            Arrays.fill(vis[i], false); // 初始访问状态设为未访问
        }

        // 使用队列进行BFS
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{n-1, m-1}); // 从终点开始搜索
        dis[n-1][m-1] = mp[n-1][m-1]; // 终点到终点的最短距离即为其本身值
        vis[n-1][m-1] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            vis[x][y] = false; // 标记为已处理
            // 尝试向四个方向移动
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                // 检查新位置是否在地图内且不是障碍物
                if (nx < 0 || nx >= n || ny < 0 || ny >= m || mp[nx][ny] == 0) {
                    continue;
                }
                int nxtval = 0;
                if (mp[nx][ny] > 0) // 如果不是障碍物，则计算新的消耗值
                {
                    nxtval = dis[x][y] + mp[nx][ny];
                }
                if (nxtval > 100) // 消耗值不能超过100（油箱容量）
                {
                    continue;
                }
                // 更新最短距离
                if (dis[nx][ny] > nxtval) {
                    dis[nx][ny] = nxtval;
                    if (!vis[nx][ny]) {
                        vis[nx][ny] = true;
                        q.add(new int[]{nx, ny}); // 加入队列继续搜索
                    }
                }
            }
        }

        int ans = -1;
        if (dis[0][0] <= 100) // 如果起点的最短距离不超过100
        {
            ans = dis[0][0];
        }
        System.out.println(ans); // 输出结果
    }
}