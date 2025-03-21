package com.het.demotest.e100.jfbj;

import java.util.Scanner;
public class Main{

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        String string = sc.nextLine();

        int res = 0;
        for(int i=0;i<string.length();i++){

            char c =string.charAt(i);

            if(c =='M'){
                if(i+1<string.length()&&string.charAt(i+1)=='I'){
                    res ++;
                    i+=2;
                }else if(i-1>=0&&string.charAt(i-1)=='I'){
                    res ++;
                }else{
                    res = -1;
                    break;
                }
            }
        }
        System.out.println(res);
    }

}