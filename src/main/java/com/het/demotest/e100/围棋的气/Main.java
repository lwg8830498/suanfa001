package com.het.demotest.e100.围棋的气;

import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

public class Main {

    static int maxSide = 18;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] locBlacks = in.nextLine().split(" ");
        String[] locWhites = in.nextLine().split(" ");
        String[] blacks = transform(locBlacks);
        String[] whites = transform(locWhites);
        System.out.println(counting(blacks, whites) + " " + counting(whites, blacks));
    }


    static int counting(String[] alias, String[] ememy) {
        Set<String> count = new HashSet<>();
        for (String a : alias) {
            count.add(a);
            String[] loc = a.split("_");
            int x = Integer.parseInt(loc[0]);
            int y = Integer.parseInt(loc[1]);
            if (x > 0) {
                count.add(Integer.toString(x - 1) + "_" + loc[1]);
            }
            if (x < maxSide) {
                count.add(Integer.toString(x + 1) + "_" + loc[1]);
            }
            if (y > 0) {
                count.add(loc[0] + "_" + Integer.toString(y - 1));
            }
            if (y < maxSide) {
                count.add(loc[0] + "_" + Integer.toString(y + 1));
            }
        }
        int res = count.size() - alias.length;
        for (String e : ememy) {
            if (count.contains(e)) {
                res--;
            }
        }
        return res;
    }

    static String[] transform(String[] locs) {
        String[] chess = new String[locs.length / 2];
        for (int i = 0; i < locs.length;) {
            chess[i / 2] = locs[i] + "_" + locs[i + 1];
            i += 2;
        }
        return chess;
    }
}