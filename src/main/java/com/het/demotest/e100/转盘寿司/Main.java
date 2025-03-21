package com.het.demotest.e100.转盘寿司;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String s = in.nextLine();
        String[] s_prices = s.split(" ");
        int len = s_prices.length;
        int[] prices = new int[len];
        for(int i = 0; i < len; i++){
            prices[i] = Integer.parseInt(s_prices[i]);
        }
        for(int i = 0; i < len; i++){
            int price = prices[i];
            for(int j = (i + 1) % len; j != i; j = (j + 1)%len){
                if(prices[i] > prices[j]){
                    price += prices[j];
                    break;
                }
            }
            System.out.print(price + " ");
        }
    }
}