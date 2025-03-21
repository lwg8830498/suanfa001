package com.het.demotest.e200.取出尽量少的球;

import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int sum = in.nextInt();
        int n = in.nextInt();
        List<Integer> l = new ArrayList<>();

        for(int i = 0;i<n;i++){
            l.add(in.nextInt());
        }

        int max = 0;
        for(int i = 0;i<n;i++){
            max = Math.max(l.get(i),max);
        }

        List<Integer> result = new ArrayList<>();
        int c = 0;
        int a = 0;
        while(a<=sum&&c<=max){
            for(int i = 0 ;i<n;i++){
                a += Math.min(l.get(i),c);
            }
            if(a>sum){
                c = Math.max(0,c-1);
                break;
            }else{
                a=0;
                c++;
            }
        }

        for(int i = 0;i<n;i++){
            result.add(Math.max(0,l.get(i)-c));
        }

        int m = 0;
        for(int i = 0;i<n;i++){
            if(result.get(i)!=0){
                m=1;break;
            }
        }
        if(m==0){
            System.out.print("[]");
        }else{
            System.out.print("[");
            for(int i =0;i<n-1;i++){
                System.out.print(result.get(i)+",");
            }
            System.out.print(result.get(n-1)+"]");
        }


    }
}