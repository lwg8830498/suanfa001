package com.het.demotest.e100.获得完美走位;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String string = in.nextLine();
        int numA = 0;
        int numS = 0;
        int numD = 0;
        int numW = 0;
        for(int i=0; i<string.length(); i++) {
            if(string.charAt(i) == 'A') {
                numA++;
            } else if(string.charAt(i) == 'S') {
                numS++;
            } else if(string.charAt(i) == 'D') {
                numD++;
            } else {
                numW++;
            }
        }
        int res = Integer.MAX_VALUE;
        int p = string.length()/4;
        int a = 0;
        int w = 0;
        int s = 0;
        int d = 0;
        a = Math.max(numA-p, 0);
        w = Math.max(numW-p, 0);
        s = Math.max(numS-p, 0);
        d = Math.max(numD-p, 0);
        if(a==0 && w==0 && s==0 && d==0) {
            System.out.println(0);
            return;
        }
        for(int i=0; i<string.length(); i++) {
            res = Math.min(res, match(string, i, a, w, s, d)-i +1);
        }
        System.out.println(res);
    }

    public static int match(String string, int index, int a, int w, int s, int d) {
        if(string.charAt(index) == 'A' && a>0) {
            a--;
        }
        if(string.charAt(index) == 'W' && w>0) {
            w--;
        }
        if(string.charAt(index) == 'S' && s>0) {
            s--;
        }
        if(string.charAt(index) == 'D' && d>0) {
            d--;
        }
        if(a==0 && w==0 && s==0 && d==0) {
            return index;
        }
        if(index+1>=string.length()) {
            return 1000000;
        }
        return match(string, index+1, a,w,s,d);
    }

}