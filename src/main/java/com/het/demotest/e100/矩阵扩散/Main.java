package com.het.demotest.e100.矩阵扩散;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    // 方向数组，表示上下左右四个方向
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    // 节点类，用于存储坐标和当前扩散的时间
    static class Node {
        int x, y, time;

        public Node(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    // 判断坐标是否有效
    public static boolean isValid(int x, int y, int m, int n) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }

    public static int bfs(int m, int n, int x1, int y1, int x2, int y2) {
        // 初始化网格和访问时间记录
        int[][] dist = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = -1;
            }
        }

        Queue<Node> queue = new LinkedList<>();

        // 将两个初始扩散点加入队列，并设置初始时间
        queue.add(new Node(x1, y1, 0));
        dist[x1][y1] = 0;
        queue.add(new Node(x2, y2, 0));
        dist[x2][y2] = 0;

        int maxTime = 0;

        // 开始BFS
        while (!queue.isEmpty()) {
            Node current = queue.poll();

            // 更新最大时间
            maxTime = Math.max(maxTime, current.time);

            // 扩展四个方向
            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];

                // 如果该位置有效且未访问过
                if (isValid(nx, ny, m, n) && dist[nx][ny] == -1) {
                    dist[nx][ny] = current.time + 1;
                    queue.add(new Node(nx, ny, current.time + 1));
                }
            }
        }

        return maxTime;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 输入矩阵的大小和两个扩散点的坐标
        String[] input = sc.next().split(",");
        int m = Integer.parseInt(input[0]);
        int n = Integer.parseInt(input[1]);
        int x1 = Integer.parseInt(input[2]);
        int y1 = Integer.parseInt(input[3]);
        int x2 = Integer.parseInt(input[4]);
        int y2 = Integer.parseInt(input[5]);

        // 计算矩阵全部扩散所需的时间
        int result = bfs(m, n, x1, y1, x2, y2);

        // 输出结果
        System.out.println(result);

        sc.close();
    }
}