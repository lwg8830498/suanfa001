package com.het.demotest.e200.寻找最优的路测线路;

import java.util.*;
public class Main {
    static int n, m; // 表示栅格的行数和列数
    static int[] dx = {0, 0, 1, -1}; // 定义x方向的移动：不动，不动，下移，上移
    static int[] dy = {1, -1, 0, 0}; // 定义y方向的移动：右移，左移，不动，不动

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = in.nextInt(); // 读取栅格行数
        m = in.nextInt(); // 读取栅格列数
        int[][] a = new int[25][25]; // 存储每个栅格的信号质量

        // 读入栅格的信号质量数据
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                a[i][j] = in.nextInt();
            }
        }

        // 用于二分搜索最优路线的得分
        int l = 0, r = 65535, ans = l;
        while (l <= r) {
            int mid = (l + r) / 2; // 中点信号质量
            if (ck(a, mid)) { // 检查是否存在至少为mid信号质量的可行路径
                ans = mid; // 更新答案
                l = mid + 1; // 尝试更高的信号质量
            } else {
                r = mid - 1; // 降低信号质量上限
            }
        }
        System.out.println(ans); // 输出最优路线的最低信号质量
    }

    // 检查是否存在从[0, 0]到[n-1, m-1]的路径，该路径上所有单元格的信号质量都至少为val
    static boolean ck(int[][] a, int val) {
        if (a[0][0] < val) // 如果起点信号质量小于val，直接返回false
        {
            return false;
        }
        boolean[][] vis = new boolean[25][25]; // 标记数组，记录是否访问过
        vis[0][0] = true; // 标记起点已访问
        Queue<int[]> q = new ArrayDeque<>(); // 使用队列进行广度优先搜索
        q.add(new int[]{0, 0});

        // BFS过程
        while (!q.isEmpty()) {
            int[] p = q.poll();     // 取出队首元素
            int x = p[0], y = p[1];     // 取出队首元素的坐标
            for (int i = 0; i < 4; i++) { // 探索四个方向
                int nx = x + dx[i], ny = y + dy[i];
                // 如果新位置合法且未访问过且信号质量大于等于val
                if (nx >= 0 && nx < n && ny >= 0 && ny < m && !vis[nx][ny] && a[nx][ny] >= val) {
                    vis[nx][ny] = true; // 标记为已访问
                    q.add(new int[]{nx, ny}); // 将新位置加入队列
                }
            }
        }
        return vis[n-1][m-1]; // 返回是否能到达终点
    }
}