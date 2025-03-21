package com.het.demotest.e100.货币单位换算;

import java.util.Scanner;

public class Main {

    public static double get( String s, int y, int x) {
        double sum = 0;
        double f = Double.parseDouble(s.substring(y, x));
        if (s.charAt(x) == 'C') {
            sum = sum + f;
        } else if (s.charAt(x) == 'f') {
            sum += f / 100;
        } else if (s.charAt(x) == 'E') {
            sum += f / 0.14; //100:14
        } else if (s.charAt(x) == 'e') {
            sum += f / 0.14 / 100;
        } else if (s.charAt(x) == 'G') {
            sum += f / 0.12; //100:12
        } else if (s.charAt(x) == 'p') {
            sum += f / 0.12 / 100;
        } else if (s.charAt(x) == 'J') {
            sum += f / 18.25; //100:1825
        } else if (s.charAt(x) == 's') {
            sum += f / 18.25 / 100;
        } else if (s.charAt(x) == 'H') {
            sum += f / 1.23; //100:123
        } else if (s.charAt(x) == 'c') {
            sum += f / 1.23 / 100; //
        }

        return sum;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        in.nextLine();
        double sum = 0;
        while (n-- > 0) {
            String s = in.nextLine();
            int x = 0;
            while ((s.charAt(x) >= '0' && s.charAt(x) <= '9') || s.charAt(x) == '.') {
                x++;
            }
            int y = x;
            while (y < s.length() && ((s.charAt(y) >= 'a' && s.charAt(y) <= 'z') ||
                    (s.charAt(y) >= 'A' && s.charAt(y) <= 'Z'))) {
                y++;
            }
            if (y == s.length()) {
                sum = sum + get(s, 0, x);
            } else {
                sum = sum + get(s, 0, x);
                x = y;
                while ((s.charAt(x) >= '0' && s.charAt(x) <= '9') || s.charAt(x) == '.') {
                    x++;
                }
                sum = sum + get(s, y, x);
            }
        }
        long ans = (long)(sum * 100);

        System.out.println(ans);
    }


}