package com.het.demotest.e200.最小矩阵宽度;

import java.util.*;

public class Main {
    /**
     * 2 5
     1 2 2 3 1
     2 3 2 3 2
     3
     1 2 3
     * @param args
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), m = in.nextInt();
        int[][] matrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0;j < m; j++) {
                matrix[i][j] = in.nextInt();
            }
        }

        int len = in.nextInt();
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = in.nextInt();
        }


        int[] map = new int[1001];
        for (int num : arr) map[num]++;

        int width = Integer.MAX_VALUE;
        boolean[][] dp = new boolean[m][m];
        for (boolean[] init : dp) Arrays.fill(init, true);
        for (int leftBound = 0; leftBound < m; leftBound++) {
            for (int rightBound = leftBound; rightBound < m; rightBound++) {
                if (rightBound > leftBound && !dp[leftBound][rightBound]) continue;
                if (rightBound - leftBound > width) continue;
                if(canFound(matrix, map, leftBound, rightBound)) {
                    width = Math.min(width, rightBound - leftBound + 1);
                } else {
                    for (int r = rightBound; r >= leftBound; r--) dp[r][rightBound] = false;
                }
            }
        }

        if (width == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(width);
    }

    private static boolean canFound(int[][] matrix, int[] map, int leftBound, int rightBound) {
        int[] numCnt = new int[1001];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = leftBound; j <= rightBound; j++) {
                numCnt[matrix[i][j]]++;
            }
        }

        boolean isOk = true;
        for(int i = 0; i < 1001; i++) {
            if (numCnt[i] >= map[i]) continue;
            else {
                isOk = false;
                break;
            }
        }

        return isOk;
    }
}