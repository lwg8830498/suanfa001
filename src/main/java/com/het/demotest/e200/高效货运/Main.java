package com.het.demotest.e200.高效货运;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int wa = in.nextInt();
        int wb = in.nextInt();
        int wt = in.nextInt();
        int pa = in.nextInt();
        int pb = in.nextInt();

        int maxCountA = wt / wa;
        int maxCountB = wt / wb;
        int maxMoney = 0;


        for(int i = 1; i <= maxCountA; i++)
        {
            for(int j = 1; j <= maxCountB; j++)
            {
                int sum = wa * i + wb * j;
                if(sum > wt)
                {
                    break;
                }
                else if(sum == wt)
                {
                    maxMoney = Math.max(maxMoney,i * pa + j * pb);
                }
            }
        }

        System.out.println(maxMoney);
    }
}