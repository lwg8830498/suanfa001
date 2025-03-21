package com.het.demotest.e100.ggsyz;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        List<int[]> primitiveTriples = new ArrayList<>();

        for (int a = N; a <= M; a++) {
            for (int b = a + 1; b <= M; b++) {
                int cSquared = a * a + b * b;
                int c = (int) Math.sqrt(cSquared);
                if (c * c == cSquared && c <= M) {
                    if (gcd(a, b) == 1 && gcd(a, c) == 1 && gcd(b, c) == 1) {
                        primitiveTriples.add(new int[]{a, b, c});
                    }
                }
            }
        }

        if (primitiveTriples.isEmpty()) {
            System.out.println("NA");
        } else {
            primitiveTriples.sort((x, y) -> {
                if (x[0] != y[0]) {
                    return x[0] - y[0];
                }
                if (x[1] != y[1]) {
                    return x[1] - y[1];
                }
                return x[2] - y[2];
            });

            for (int[] triple : primitiveTriples) {
                System.out.println(triple[0] + " " + triple[1] + " " + triple[2]);
            }
        }
    }

    private static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}