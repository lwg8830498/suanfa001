package com.het.demotest.e100.最多购买宝石数目;

import java.util.Scanner;

public class Main {
    static int res = 0;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] games = new int[n];
        for (int i = 0; i < n; i++) {
            games[i] = scanner.nextInt();
        }
        int value = scanner.nextInt();


        traverse(games, value);


        System.out.println(res);
    }

    private static void traverse(int[] games, int value) {
        int begin = 0;
        int sum = 0;
        int end = 0;
        for (; end < games.length; end++) {
            sum += games[end];
            while (sum > value) {
                sum -= games[begin];
                begin++;
            }
            res = Math.max(end - begin + 1, res);
        }
    }
}
 