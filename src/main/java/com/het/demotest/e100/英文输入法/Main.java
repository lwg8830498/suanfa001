package com.het.demotest.e100.英文输入法;

import java.util.Scanner;

public class Main {
    public static String remove(String str) {
        return str.replace(".", "");
    }

    public static void compute(String str, String pre) {
        java.util.Set<String> set = new java.util.TreeSet<>();
        String[] arr = str.split(", ");
        for (int i = 0; i < arr.length; i++) {
            String temp = arr[i];
            String[] arr2 = temp.split(" ");
            if (arr2.length == 1) {
                set.add(remove(arr2[0]));
            } else {
                for (int j = 0; j < arr2.length; j++) {
                    String temp2 = arr2[j];
                    String[] arr3 = temp2.split("'");
                    if (arr3.length == 1) {
                        set.add(remove(arr3[0]));
                    } else {
                        for (String s : arr3) {
                            set.add(remove(s));
                        }
                    }
                }
            }
        }

        java.util.Set<String> res = new java.util.TreeSet<>();
        for (String s : set) {
            if (s.startsWith(pre)) {
                res.add(s);
            }
        }

        if (res.isEmpty()) {
            System.out.println(pre);
        } else {
            StringBuffer sb = new StringBuffer();
            for (String s : res) {
                sb.append(" " + s);
            }

            System.out.println(sb.toString().substring(1));
        }

    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = 2;
        String str = null;
        String pre = null;
        while (in.hasNextLine()) {
            if (n == 2) {
                str = in.nextLine();
            }
            if (n == 1) {
                pre = in.nextLine();
            }

            n--;
            if (n == 0) {
                break;
            }
        }
        compute(str, pre);
    }
}
 