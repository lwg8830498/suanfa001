package com.het.demotest.e100.火星文计算;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (in.hasNext()) {
            String gongsi = in.next();
            String[] meiyuanArr = gongsi.split("\\$");
            int[] valueArr = new int[meiyuanArr.length];
            for (int i = 0; i < meiyuanArr.length; i ++) {
                String[] sub = meiyuanArr[i].split("\\#");
                int result = Integer.valueOf(sub[0]);
                for (int j = 1; j < sub.length; j ++) {
                    result = jinhao(result, Integer.valueOf(sub[j]));
                }
                valueArr[i] = result;
            }
            int result = valueArr[0];
            for (int i = 1; i < valueArr.length; i ++) {
                result = meiyuan(result, valueArr[i]);
            }
            System.out.println(result);
        }
    }

    public static int jinhao(int x, int y) {
        return 4*x + 3*y + 2;
    }

    public static int meiyuan(int x, int y) {
        return 2*x + y + 3;
    }
}