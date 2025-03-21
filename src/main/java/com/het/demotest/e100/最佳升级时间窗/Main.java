package com.het.demotest.e100.最佳升级时间窗;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] Q = new int[168];
        for (int i = 0; i < 168; i++) {
            Q[i] = scanner.nextInt();
        }

        int[] result = findBestWindow(Q, n);
        System.out.println(result[0] + " " + result[1]);
    }

    private static int[] findBestWindow(int[] Q, int n) {
        int[] extendedQ = new int[336];
        System.arraycopy(Q, 0, extendedQ, 0, 168);
        System.arraycopy(Q, 0, extendedQ, 168, 168);

        int maxLength = 0;
        int bestStart = 0;
        int bestEnd = 0;

        for (int start = 0; start < 168; start++) {
            int sum = 0;
            for (int end = start; end < start + 168; end++) {
                sum += extendedQ[end];
                if (sum > n) {
                    int length = end - start + 1;
                    if (length > maxLength) {
                        maxLength = length;
                        bestStart = start;
                        bestEnd = end % 168;
                    }
                    break;
                }
            }
        }

        return new int[]{bestStart, bestEnd};
    }
}