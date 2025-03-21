package com.het.demotest.e100.特殊的加密算法;

import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    // 定义最大的可能的数组大小
    static final int N = 210;
    // 明文数据数组
    static int[] Data = new int[N];
    // 密码本数组
    static int[][] Book = new int[N][N];
    // 访问标记数组，用于标记密码本中的位置是否已经被访问过
    static boolean[][] vist = new boolean[N][N];
    // 明文的长度和密码本的大小
    static int n, m;
    // 存储找到的密文序列
    static ArrayList<int[]> ans = new ArrayList<>();

    // 深度优先搜索函数，用于在密码本中寻找符合条件的密文序列
    public static boolean dfs(int u, int v, int k) {
        vist[u][v] = true; // 标记当前位置已经被访问
        ans.add(new int[]{u, v}); // 将当前位置加入到答案序列中
        if (k == n - 1) { // 如果当前位置是明文的最后一位，返回成功
            return true;
        }
        // 定义四个可能的移动方向（上、左、右、下）
        int[] dx = {-1, 0, 0, 1};
        int[] dy = {0, -1, 1, 0};
        for (int i = 0; i < 4; i++) {
            int x = u + dx[i]; // 计算下一个位置的行号
            int y = v + dy[i]; // 计算下一个位置的列号
            // 检查新位置是否有效，未被访问，且密码本中的数字与明文的下一个数字匹配
            if (x >= 0 && x < m && y >= 0 && y < m && !vist[x][y] && Book[x][y] == Data[k + 1]) {
                if (dfs(x, y, k + 1)) return true; // 递归调用dfs
            }
        }
        ans.remove(ans.size() - 1); // 回溯，移除当前位置
        vist[u][v] = false; // 取消当前位置的访问标记
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt(); // 读取明文长度
        for (int i = 0; i < n; i++) {
            Data[i] = scanner.nextInt(); // 读取明文数据
        }
        m = scanner.nextInt(); // 读取密码本的大小
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                Book[i][j] = scanner.nextInt(); // 读取密码本数据
            }
        }

        boolean find = false; // 是否找到合适的密文标志
        // 遍历密码本的每个元素，尝试作为起点
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                if (Data[0] == Book[i][j]) { // 如果位置匹配明文的第一个数字
                    if (dfs(i, j, 0)) { // 从这个位置开始DFS
                        find = true;
                        break;
                    }
                }
            }
            if (find) break; // 如果找到一条路径，终止循环
        }

        if (!find || ans.isEmpty()) System.out.println("error"); // 如果没有找到有效的密文路径，输出error
        else {
            for (int[] x : ans) { // 否则输出找到的路径
                System.out.print(x[0] + " " + x[1] + " ");
            }
        }
    }
}