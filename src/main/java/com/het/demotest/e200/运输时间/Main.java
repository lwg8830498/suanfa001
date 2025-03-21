package com.het.demotest.e200.运输时间;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int M = scanner.nextInt(); // 车辆数
        int N = scanner.nextInt(); // 距离

        int[] speeds = new int[M];
        for (int i = 0; i < M; i++) {
            speeds[i] = scanner.nextInt();
        }

        scanner.close();

        double[] arrivalTimes = new double[M];
        arrivalTimes[0] = (double) N / speeds[0];

        for (int i = 1; i < M; i++) {
            double timeToReach = (double) N / speeds[i];
            arrivalTimes[i] = Math.max(timeToReach + i, arrivalTimes[i - 1]);
        }
        double arrivalTime = arrivalTimes[M - 1];
        if (arrivalTime == (int) arrivalTime) {
            System.out.println((int)arrivalTime - M + 1);
        } else{
            DecimalFormat df = new DecimalFormat("#.###");
            System.out.println(df.format( arrivalTime - M + 1));}

    }
}