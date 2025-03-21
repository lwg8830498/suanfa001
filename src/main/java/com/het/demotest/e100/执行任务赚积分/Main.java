package com.het.demotest.e100.执行任务赚积分;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int t = sc.nextInt();

        List[] list = new List[t + 1];
        for (int i = 0; i < (t + 1); i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            int endTime = sc.nextInt();
            int value = sc.nextInt();
            list[Math.min(endTime,t)].add(value);
        }
        int ans = 0;

        List<Integer> waitList = new ArrayList<>();
        for (int i = t; i >0 ; i--) {
            waitList.addAll(list[i]);
            if (!waitList.isEmpty()) {
                waitList.sort(Comparator.comparingInt(o-> o));
                ans+=waitList.remove(waitList.size()-1);
            }
        }
        System.out.println(ans);
    }
}