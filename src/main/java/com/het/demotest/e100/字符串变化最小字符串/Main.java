package com.het.demotest.e100.字符串变化最小字符串;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();

        char[] arr = s.toCharArray();

        int minIndex = 0;
        Integer minValue = Integer.valueOf(arr[minIndex]);
        for (int i = 0; i < arr.length; i++) {
            Integer curValue = Integer.valueOf(arr[i]);
            if (curValue <= minValue) {
                minValue = curValue;
                minIndex = i;
            }
        }
        if (minIndex == 0) {
            System.out.println(s);
        } else {
            char temp = arr[minIndex];
            arr[minIndex] = arr[0];
            arr[0] = temp;
            System.out.println(String.valueOf(arr));
        }
    }
}