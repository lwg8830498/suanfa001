package com.het.demotest.e100.最大坐标值;

import java.util.Scanner;

import java.util.Arrays;


public class Main {
    public static void main(String[] args) {
        try{
            Scanner in = new Scanner(System.in);
            int n = in.nextInt();
            in.nextLine();
            int m = in.nextInt();
            in.nextLine();
            int[] ins = new int[n+1];
            if(n>100 || n < 1 || m<-100 || m>100){
                throw new Exception();
            }

            for(int i = 1 ; i < n+1 ; i++){
                int num = in.nextInt();
                if(num>100 || num <-100){
                    throw new Exception();
                }
                ins[i] = num ;
            }

            int[] point = new int[n+1];
            point[0] = 0 ;
            for(int j = 1 ; j < n+1 ; j++){
                if(m == ins[j]){
                    if(m>0){
                        point[j] = point[j-1] + ins[j] + 1 ;
                    }else if(m==0){
                        point[j] = point[j-1] + 1 ;
                    }else{
                        point[j] = point[j-1] + ins[j] - 1 ;
                    }
                }else{
                    point[j] = point[j-1] + ins[j];
                }
            }
            Arrays.sort(point);
            System.out.println(point[n]);
        }catch(Exception e){
            System.out.println(12345);
        }
    }
}