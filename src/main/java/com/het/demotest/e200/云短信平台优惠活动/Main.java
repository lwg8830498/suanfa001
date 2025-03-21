package com.het.demotest.e200.云短信平台优惠活动;

import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()){
            String moneyLine = scanner.nextLine();
            Integer money = Integer.parseInt(moneyLine);

            String next = scanner.nextLine();
            String[] split = next.split(" ");
            int length = split.length;

            int[] messageNums = new int[length];
            for (int i = 0; i < length; i++) {
                messageNums[i] = Integer.parseInt(split[i]);
            }

            System.out.println(process(money,messageNums,0,0));
        }
    }

    private static int process(Integer money, int[] messageNums, int countMsg,int useIndex) {
        if (money<=0 || useIndex > money){
            return countMsg;
        }
        int max = Integer.MIN_VALUE;
        for (int i = useIndex; i < messageNums.length; i++) {
            int p1 = 0;
            if (money-i-1 >=0){
                p1 = process(money-i-1,messageNums,countMsg+messageNums[i],useIndex);
            }
            int p2 = process(money, messageNums, countMsg, useIndex+1);
            max = Math.max(max,Math.max(p1,p2));
        }
        return max;
    }
}