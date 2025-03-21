package com.het.demotest.e100.素数之积;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        scanner.close();

        if (num <= 1) {
            System.out.println("-1 -1");
            return;
        }

        int[] result = factorizeToPrimes(num);
        if (result[0] == -1) {
            System.out.println("-1 -1");
        } else {
            System.out.println(result[0] + " " + result[1]);
        }
    }

    private static int[] factorizeToPrimes(int num) {
        for (int i = 2; i * i <= num; i++) {
            if (isPrime(i) && num % i == 0) {
                int other = num / i;
                if (isPrime(other)) {
                    return new int[]{Math.min(i, other), Math.max(i, other)};
                }
            }
        }
        return new int[]{-1, -1};
    }

    private static boolean isPrime(int n) {
        if (n <= 1) return false;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}