package com.het.demotest.e100.宜居星球改造计划;

import java.util.*;

class Point {
    int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<List<String>> grid = new ArrayList<>();

        // 读取输入并将其存储到网格中
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (!line.isEmpty()) {
                List<String> row = Arrays.asList(line.split(" "));
                grid.add(new ArrayList<>(row));
            }
        }

        int row = grid.size();
        int column = grid.get(0).size();

        // 检查是否存在至少一个 "YES" 或 "NO"，否则无法进行改造
        boolean hasYesOrNo = false;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (grid.get(i).get(j).equals("YES") || grid.get(i).get(j).equals("NO")) {
                    hasYesOrNo = true;
                    break;
                }
            }
            if (hasYesOrNo) break;
        }

        if (!hasYesOrNo) {
            System.out.println(-1);
            return;
        }

        Queue<Point> queue = new LinkedList<>();
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int[][] days = new int[row][column];
        for (int[] day : days) {
            Arrays.fill(day, -1);
        }

        // 初始化队列，将所有的 "YES" 位置加入队列，设置起始天数为 0
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (grid.get(i).get(j).equals("YES")) {
                    queue.add(new Point(i, j));
                    days[i][j] = 0;
                }
            }
        }

        int maxDays = 0;
        // 使用广度优先搜索 (BFS) 扩散宜居区
        while (!queue.isEmpty()) {
            Point current = queue.poll();

            // 向四个方向扩散
            for (int[] dir : directions) {
                int newX = current.x + dir[0];
                int newY = current.y + dir[1];

                // 检查新位置是否在网格内，且为可改造区（NO）
                if (newX >= 0 && newX < row && newY >= 0 && newY < column && grid.get(newX).get(newY).equals("NO")) {
                    grid.get(newX).set(newY, "YES");  // 改造为宜居区
                    days[newX][newY] = days[current.x][current.y] + 1;
                    maxDays = days[newX][newY];  // 更新最大天数
                    queue.add(new Point(newX, newY));
                }
            }
        }

        // 检查是否存在无法改造的可改造区
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (grid.get(i).get(j).equals("NO")) {
                    System.out.println(-1);
                    return;
                }
            }
        }

        // 输出所需的天数
        System.out.println(maxDays);
    }
}