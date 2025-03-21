package com.het.demotest.e200.找单词;

import java.util.*;

public class Main {

    // 定义方向向量：上下左右
    private static final int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    // 深度优先搜索 (DFS) 函数
    public static boolean dfs(char[][] board, String word, int index, int x, int y, boolean[][] visited, List<int[]> path) {
        // 如果匹配到目标字符串的最后一个字符，返回 true
        if (index == word.length()) {
            return true;
        }

        int n = board.length;

        // 检查边界条件和访问标记
        if (x < 0 || y < 0 || x >= n || y >= n || visited[x][y] || board[x][y] != word.charAt(index)) {
            return false;
        }

        // 标记当前单元格为已访问
        visited[x][y] = true;
        path.add(new int[]{x, y});

        // 遍历上下左右四个方向
        for (int[] direction : directions) {
            int newX = x + direction[0];
            int newY = y + direction[1];

            if (dfs(board, word, index + 1, newX, newY, visited, path)) {
                return true;
            }
        }

        // 回溯，取消当前单元格的访问标记
        visited[x][y] = false;
        path.remove(path.size() - 1);

        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取二维数组的大小 N
        int N = scanner.nextInt();
        scanner.nextLine(); // 读取剩余的换行符

        // 读取二维字符数组
        char[][] board = new char[N][N];
        for (int i = 0; i < N; i++) {
            String[] row = scanner.nextLine().split(",");
            for (int j = 0; j < N; j++) {
                board[i][j] = row[j].charAt(0);
            }
        }

        // 读取待查找的字符串
        String word = scanner.nextLine();

        boolean[][] visited = new boolean[N][N]; // 访问标记数组
        List<int[]> path = new ArrayList<>(); // 记录路径

        // 遍历整个二维数组，寻找字符串的起始点
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (dfs(board, word, 0, i, j, visited, path)) {
                    // 输出结果
                    for (int k = 0; k < path.size(); k++) {
                        if (k > 0) {
                            System.out.print(",");
                        }
                        System.out.print(path.get(k)[0] + "," + path.get(k)[1]);
                    }
                    return;
                }
            }
        }

        // 如果未找到，输出 N
        System.out.println("N");
    }
}