package com.het.demotest.e200.可以组成网络的服务器;

import java.util.*;
public class Main {
    static int[] dx = {0, 0, 1, -1};  // x方向的偏移量数组，用于控制上下移动
    static int[] dy = {1, -1, 0, 0};  // y方向的偏移量数组，用于控制左右移动
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), m = in.nextInt();  // 从输入中读取行数n和列数m
        int[][] mp = new int[105][105];  // 声明一个存储服务器信息的二维数组
        boolean[][] vis = new boolean[105][105];  // 声明一个记录访问状态的二维布尔数组
        for (int i = 1; i <= n; i++)  // 循环读取每一行
        {
            for (int j = 1; j <= m; j++)  // 循环读取每一行中的每一列
            {
                mp[i][j] = in.nextInt();  // 将输入的服务器信息存储到数组中
            }
        }
        int ans = 0;  // 初始化最大局域网的服务器数量为0
        for (int x = 1; x <= n; x++) {  // 遍历所有的行
            for (int y = 1; y <= m; y++) {  // 遍历行中的每个元素
                if (mp[x][y] == 1 && !vis[x][y])  // 如果当前位置有服务器且未访问过
                    ans = Math.max(ans, dfs(x, y, mp, vis, n, m));  // 使用DFS搜索，并更新最大局域网大小
            }
        }
        System.out.println(ans);  // 输出最大局域网的服务器数量
    }

    static int dfs(int x, int y, int[][] mp, boolean[][] vis, int n, int m) {
        vis[x][y] = true;  // 标记当前位置为已访问
        int res = 1;  // 初始化当前服务器连通区域的大小为1
        for (int i=0; i<4; i++) {  // 遍历上下左右四个方向
            int nx = x + dx[i], ny = y + dy[i];  // 计算下一个位置的坐标
            if (nx < 1 || nx > n || ny < 1 || ny > m)  // 如果下一个位置超出了边界
            {
                continue;  // 跳过当前方向的搜索
            }
            if (mp[nx][ny] == 1 && !vis[nx][ny])  // 如果下一个位置有服务器且未访问过
            {
                res += dfs(nx, ny, mp, vis, n, m);  // 递归搜索下一个位置
            }
        }
        return res;  // 返回从当前服务器出发的最大连通区域的大小
    }
}