package com.het.demotest.e200.观看文艺汇演问题;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

class Show {
    int start;
    int duration;
    int end;

    public Show(int start, int duration) {
        this.start = start;
        this.duration = duration;
        this.end = start + duration;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        ArrayList<Show> shows = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            int T = scanner.nextInt();
            int L = scanner.nextInt();
            shows.add(new Show(T, L));
        }

        // 根据演出的结束时间对演出进行排序
        Collections.sort(shows, new Comparator<Show>() {
            @Override
            public int compare(Show s1, Show s2) {
                return Integer.compare(s1.end, s2.end);
            }
        });

        int maxShows = 0;
        int lastEnd = -15; // 初始化以确保可以选择第一场演出

        // 通过贪心算法选择最多的演出
        for (Show show : shows) {
            if (show.start >= lastEnd + 15) {
                lastEnd = show.end;
                maxShows++;
            }
        }

        System.out.println(maxShows);
        scanner.close();
    }
}