package com.het.demotest.e100.来自异国的客人;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            try {
                String ks = in.next();
                String ns = in.next();
                String ms = in.next();
                int k = Integer.parseInt(ks);
                int n = Integer.parseInt(ns);
                int m = Integer.parseInt(ms);
                if (n < m) {
                    String val = Integer.toString(k, m);
                    String lucky = String.valueOf(n);
                    char[] valArr = val.toCharArray();
                    char[] luckyArr = lucky.toCharArray();
                    int loop = luckyArr.length;
                    int count = 0;
                    for (int i = 0 ; i < valArr.length; i++) {
                        String valItem = "";
                        for (int j = 0; j < loop; j++) {
                            if (valArr.length <= (i + j)) {
                                break;
                            }
                            valItem += String.valueOf(valArr[i + j]);
                        }
                        if (valItem.equals(lucky)) {
                            count++;
                        }
                    }
                    System.out.print(count);
                } else {
                    System.out.print(0);
                }

            } catch (RuntimeException e) {
                System.out.print(0);
            }
        }
    }
}