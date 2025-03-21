package com.het.demotest.e200.路口最短时间问题;

import java.util.*;

public class Main {
    // 定义状态类，包含位置、时间和方向
    private static class State {
        int row, col, time, dir;

        State(int row, int col, int time, int dir) {
            this.row = row;
            this.col = col;
            this.time = time;
            this.dir = dir;
        }
    }

    // 自定义比较器，确保优先队列为最小堆，基于时间排序
    private static class StateComparator implements Comparator<State> {
        @Override
        public int compare(State a, State b) {
            return Integer.compare(a.time, b.time);
        }
    }

    // 计算从起始点到终点的最短通行时间
    public static int calcTime(int[][] lights, int timePerRoad, int rowStart, int colStart, int rowEnd, int colEnd) {
        int n = lights.length, m = lights[0].length;
        int[][][] minTime = new int[n][m][4];
        for (int[][] layer : minTime) {
            for (int[] row : layer) {
                Arrays.fill(row, Integer.MAX_VALUE);
            }
        }

        PriorityQueue<State> pq = new PriorityQueue<>(new StateComparator());
        for (int dir = 0; dir < 4; ++dir) {
            minTime[rowStart][colStart][dir] = 0;
        }
        pq.add(new State(rowStart, colStart, 0, -1));
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};  // 东、南、西、北方向的移动向量

        while (!pq.isEmpty()) {
            State cur = pq.poll();

            if (cur.row == rowEnd && cur.col == colEnd) {
                return cur.time;
            }

            for (int i = 0; i < 4; ++i) {
                int nextRow = cur.row + directions[i][0];
                int nextCol = cur.col + directions[i][1];
                if((cur.dir != -1) && (cur.dir + 2) %4 == i) {
                    continue;
                }
                if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= m) {
                    continue;
                }

                int waitTime = 0;
                if (!(cur.dir == -1 || i == (cur.dir + 1) % 4)){ // 左转或直行
                    waitTime = lights[cur.row][cur.col];
                }

                int nextTime = cur.time + timePerRoad + waitTime;
                if (nextTime < minTime[nextRow][nextCol][i]) {
                    minTime[nextRow][nextCol][i] = nextTime;
                    pq.add(new State(nextRow, nextCol, nextTime, i));
                }
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int dir = 0; dir < 4; ++dir) {
            ans = Math.min(ans, minTime[rowEnd][colEnd][dir]);
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] lights = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                lights[i][j] = scanner.nextInt();
            }
        }
        int timePerRoad = scanner.nextInt();
        int rowStart = scanner.nextInt();
        int colStart = scanner.nextInt();
        int rowEnd = scanner.nextInt();
        int colEnd = scanner.nextInt();
        int minimumTime = calcTime(lights, timePerRoad, rowStart, colStart, rowEnd, colEnd);
        System.out.println(minimumTime);
    }
}