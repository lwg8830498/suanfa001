package com.het.demotest.e200.矩阵匹配;

import java.util.Scanner;
import java.util.Arrays;

public class Main {

    private static int[][] matrix;
    private static int n, m, k;
    private static boolean[] visited;
    private static int[] match;

    // 查找增广路径
    private static boolean dfs(int x, int v) {
        for (int i = 1; i <= m; i++) {
            if (matrix[x][i] <= v) {  // 如果当前的匹配值小于我们查询的v
                if (visited[i]) {
                    continue;  // 如果已经访问过经过 i 的增广路径
                }
                visited[i] = true;
                if (match[i] == 0 || dfs(match[i], v)) { // 如果当前节点 i 没有匹配过，或者可以从 i 点找到增广路径
                    match[i] = x;  // 记录该匹配
                    return true;
                }
            }
        }
        return false;
    }

    // 二分查找的判断函数
    private static boolean check(int v) {
        int count = 0;
        Arrays.fill(match, 0); // 初始化所有的匹配都为空
        for (int i = 1; i <= n; i++) { // 以行遍历，查询最大匹配
            Arrays.fill(visited, false); // 初始化增广路径的查找点为空
            if (dfs(i, v)) {
                count++; // 找到一条增光路径
            }
            if (count >= n - k + 1) {
                return true; // 当前的最大匹配已经大于 n-k+1
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();
        k = scanner.nextInt();

        matrix = new int[n + 1][m + 1];
        visited = new boolean[m + 1];
        match = new int[m + 1];

        int l = Integer.MAX_VALUE, r = Integer.MIN_VALUE;

        // 读取矩阵并初始化二分查找的边界
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                matrix[i][j] = scanner.nextInt();
                if (l > matrix[i][j]) {
                    l = matrix[i][j];
                }
                if (r < matrix[i][j]) {
                    r = matrix[i][j];
                }
            }
        }

        // 利用二分查找
        while (l < r) {
            int mid = (l + r) / 2;
            if (check(mid)) {
                r = mid; // 如果说目前的 <= mid 条件下 可以找到 n-k+1 个最大匹配那么最大匹配的右端点则在这个范围内
            } else {
                l = mid + 1; // 否则说明 mid 太小，查询左端点要加1
            }
        }

        System.out.println(l); // 输出我们的 l
    }
}