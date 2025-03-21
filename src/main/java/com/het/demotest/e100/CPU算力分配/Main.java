package com.het.demotest.e100.CPU算力分配;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] L = scanner.nextLine().split(" ");
        int L1 = Integer.parseInt(L[0]);
        int L2 = Integer.parseInt(L[1]);

        String[] A = scanner.nextLine().split(" ");
        String[] B = scanner.nextLine().split(" ");

        int suma = 0;
        int sumb = 0;

        for (String num : A) {
            suma += Integer.parseInt(num);
        }

        for (String num : B) {
            sumb += Integer.parseInt(num);
        }

        int ava = (suma + sumb) / 2;
        int value = Math.abs(ava - suma);

        Arrays.sort(A);
        Arrays.sort(B);

        if (suma > sumb) {
            for (String item : A) {
                if (Arrays.binarySearch(B, String.valueOf(Integer.parseInt(item) - value)) >= 0) {
                    System.out.println(item + " " + (Integer.parseInt(item) - value));
                    break;
                }
            }
        } else {
            for (String item : A) {
                if (Arrays.binarySearch(B, String.valueOf(value + Integer.parseInt(item))) >= 0) {
                    System.out.println(item + " " + (value + Integer.parseInt(item)));
                    break;
                }
            }
        }
    }
}