package com.het.demotest.e100.游戏分组;

import java.util.Scanner;

public class Main {
    private static int minNum = Integer.MAX_VALUE;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] strArr = sc.nextLine().split(" ");
        int[] numArr = new int[10];
        int numArrSum = 0;
        for (int i = 0; i < strArr.length; i++) {
            int i1 = Integer.parseInt(strArr[i]);
            numArr[i] = i1;
            numArrSum += i1;
        }
        getMinNum(numArr,0,0,0,numArrSum);
        System.out.println(minNum);

    }
    public static void getMinNum(int[] numArr,int i,int selectedNum,int sum,int numArrSum){
        if( 10-i < 5-selectedNum){
            return;
        }
        if(selectedNum == 5){
            int df = Math.abs(sum-(numArrSum-sum));
            if(df < minNum){
                minNum = df;
            }
            return;
        }
        //不选
        getMinNum(numArr,i+1,selectedNum,sum,numArrSum);
        //选
        getMinNum(numArr,i+1,selectedNum+1,sum+numArr[i],numArrSum);
    }
}
