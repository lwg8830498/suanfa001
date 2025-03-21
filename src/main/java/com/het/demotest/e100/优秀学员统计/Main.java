package com.het.demotest.e100.优秀学员统计;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        int[][] idArr = new int[num][2];
        int[] arr = new int[30];
        for(int i = 0 ; i < 30 ; i++){
            arr[i] = in.nextInt();
        }
        for(int i = 0 ; i < 30 ; i++){
            for(int j = 0 ; j < arr[i] ; j++){
                int id = in.nextInt();
                idArr[id][0]++;
                if(idArr[id][1]==0){
                    idArr[id][1] = i + 1;
                }
            }
        }
        int[] result = new int[5];
        for(int i = 0; i < 5 ; i++){
            int maxId = idArr[0][0];
            int maxId2 = idArr[0][1];
            result[i] = 0;
            for(int j = 1 ; j < num ;j++){
                if(idArr[j][0] > maxId){
                    maxId = idArr[j][0];
                    maxId2 = idArr[j][1];
                    result[i] = j;
                }else if((idArr[j][0] == maxId)&& (idArr[j][1] < maxId2)){
                    maxId = idArr[j][0];
                    maxId2 = idArr[j][1];
                    result[i] = j;
                }
            }

            idArr[result[i]][0]= -1;
            if(i<num && i != 4 &&  i != num -1){
                System.out.print(result[i] +" ");
            }else if(i<num){
                System.out.print(result[i]);
            }

        }

    }
}