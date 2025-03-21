package com.het.demotest.e100.整数对最小和;

import java.util.Scanner;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        String line1 = in.nextLine();
        String line2 = in.nextLine();
        int k = in.nextInt();

        String[] strArray1 = line1.split(" ");
        String[] strArray2 = line2.split(" ");
        int n = Integer.parseInt(strArray1[0]);
        int m = Integer.parseInt(strArray2[0]);;
        int[] array1 = new int[n];
        int[] array2 = new int[m];
        for (int i = 0; i < n; i++) {
            array1[i] = Integer.parseInt(strArray1[i+1]);
        }
        for (int i = 0; i < m; i++) {
            array2[i] = Integer.parseInt(strArray2[i+1]);
        }

        int[] res = new int[n*m];
        for (int i = 0; i < n*m; i++) {
            res[i] = Integer.MAX_VALUE;
        }

        int l=0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                res[l++] = array1[i]+array2[j];
            }
        }

        Arrays.sort(res);


        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum+=res[i];
        }
        System.out.println(sum);
    }
}