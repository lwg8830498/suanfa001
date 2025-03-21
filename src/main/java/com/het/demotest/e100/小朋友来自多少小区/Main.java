package com.het.demotest.e100.小朋友来自多少小区;

import java.util.Scanner;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;


public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Map<Integer,Integer> map = new HashMap<>();
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            int num = in.nextInt();
            int key = num + 1 ;
            if(map.get(key)!=null){
                map.put(key,map.get(key)+1);
            }else{
                map.put(key,1);
            }
        }
        Set<Integer> set = map.keySet();
        int sum = 0 ;
        for(int k : set){
            int v = map.get(k);
            if(k<v){
                int x = v/k;
                if(v%k==0){
                    sum += x*k;
                }else{
                    sum += (x+1)*k;
                }
            }else{
                sum += k;
            }
        }
        System.out.println(sum);
    }
}