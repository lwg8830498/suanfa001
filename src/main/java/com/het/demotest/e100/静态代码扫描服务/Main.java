package com.het.demotest.e100.静态代码扫描服务;

import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        List<Integer> l = new ArrayList<>();
        while (in.hasNextInt()) {// 注意，如果输入是多个测试用例，请通过while循环处理多个测试用例
            l.add(in.nextInt());
        }
        int n = l.size();
        Map<Integer,Integer> valueMap = new HashMap<>();
        Map<Integer,Integer> timeMap = new HashMap<>();
        for(int i = 0;i<n/2;i++){
            valueMap.put(l.get(i),l.get(i+n/2));
            if(timeMap.containsKey(l.get(i))){
                timeMap.put(l.get(i),timeMap.get(l.get(i))+1);
            }else{
                timeMap.put(l.get(i),1);
            }
        }

        int max = 0;
        for(Integer t:timeMap.keySet()){
            int a1 = valueMap.get(t)*timeMap.get(t);
            int a2 = valueMap.get(t) + a;
            max += Math.min(a1,a2);
        }
        System.out.print(max);

    }
}