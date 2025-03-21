package com.het.demotest.e200.欢乐的周末;

import java.util.*;
public class Main {
    static final int N = 110, M = 110; // 定义最大的地图尺寸
    static int n, m; // 地图的行数和列数
    static int[][] a = new int[N][M]; // 存储地图数据
    static boolean[][] visited = new boolean[N][M]; // 记录位置是否被访问过
    static int[] dx = {0, 1, 0, -1}; // x方向的移动向量
    static int[] dy = {1, 0, -1, 0}; // y方向的移动向量

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt(); // 输入行数
        m = scanner.nextInt(); // 输入列数
        List<Pair> starts = new ArrayList<>(), meets = new ArrayList<>();

        // 读取地图信息
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                a[i][j] = scanner.nextInt(); // 读入地图的每个格子的值
                if (a[i][j] == 2) {
                    starts.add(new Pair(i, j)); // 位置为2的是起点，加入starts列表
                } else if (a[i][j] == 3) {
                    meets.add(new Pair(i, j)); // 位置为3的是聚餐点，加入meets列表
                }
            }
        }

        bfs(starts.get(0)); // 从第一个起点开始广度优先搜索
        int answer = 0;
        if (visited[starts.get(1).x][starts.get(1).y]) { // 如果第二个起点也被访问到
            for (Pair meet : meets) {
                if (visited[meet.x][meet.y]) {
                    answer++; // 如果聚餐点被访问过，计数器加一
                }
            }
        }
        System.out.println(answer); // 输出结果
        scanner.close(); // 关闭输入流
    }

    // 广度优先搜索的实现
    static void bfs(Pair start) {
        Queue<Pair> queue = new LinkedList<>();
        queue.add(start);
        visited[start.x][start.y] = true; // 标记起点为已访问

        while (!queue.isEmpty()) {
            Pair current = queue.poll();
            for (int i = 0; i < 4; i++) {
                int x = current.x + dx[i];
                int y = current.y + dy[i];
                if (x >= 0 && x < n && y >= 0 && y < m && !visited[x][y] && a[x][y] != 1) {
                    visited[x][y] = true; // 标记为已访问
                    queue.add(new Pair(x, y)); // 加入队列继续搜索
                }
            }
        }
    }

    // 辅助类，用于存储位置坐标
    static class Pair {
        int x, y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}