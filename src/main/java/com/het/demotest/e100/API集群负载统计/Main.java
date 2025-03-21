package com.het.demotest.e100.API集群负载统计;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int count = Integer.parseInt(in.nextLine());
        String[] lines = new String[count];
        for (int i = 0; i < count; i++) {
            lines[i] = in.nextLine();
        }
        String[] search = in.nextLine().split(" ");
        int layer = Integer.parseInt(search[0]);
        String key = search[1];
        doSearch(lines, layer, key);
    }

    public static void doSearch(String[] paths, int layer, String key) {
        int count = 0;
        for (String path : paths) {
            String[] pp = path.split("/");
            if (pp.length >= layer + 1 && pp[layer].equals(key)) {
                count++;
            }
        }
        System.out.println(count);
    }
}