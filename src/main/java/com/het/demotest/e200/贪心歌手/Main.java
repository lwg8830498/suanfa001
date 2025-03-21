package com.het.demotest.e200.贪心歌手;

import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int totalDays = scanner.nextInt();
        int cityCount = scanner.nextInt();
        int wastedDays = 0;
        for (int i = 0; i < cityCount+1; i++) {
            wastedDays += scanner.nextInt();
        }
        int restDays = totalDays - wastedDays;
        //0 income 1 decrease
        int[][] cities = new int[cityCount][2];
        for (int i = 0; i < cityCount; i++) {
            cities[i][0] = scanner.nextInt();
            cities[i][1] = scanner.nextInt();
        }

        int i = doSum(restDays, cities);
        System.out.println(i);
    }


    //greedy , never actually wrote
    private static int doSum(int restDays, int[][] cities) {
        if (restDays == 0 || cities.length == 0) {
            return 0;
        }

        int income=0;
        for (int i = 0; i < restDays; i++) {
            int todayIncome = getTodayIncome(cities);
            income += todayIncome;
        }


        return income;
    }

    private static int getTodayIncome(int[][] cities) {
        int income = cities[0][0];

        int city = 0;
        for (int i = 1; i < cities.length; i++) {
            if (cities[i][0] > income) {
                income = cities[i][0];
                city = i;
            }
        }

        if (income <= 0) {
            return 0;
        }

        cities[city][0] -= cities[city][1];
        return income;
    }
}