package com.het.demotest.e100.寻找最富裕的小家庭;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            int N = Integer.parseInt(in.nextLine());
            String moneys = in.nextLine();
            List<Integer> moneyList = new ArrayList<>();
            for(String money : moneys.split(" ")){
                moneyList.add(Integer.parseInt(money));
            }

            Map<Integer, List<Integer>> familyMap = new HashMap<>();
            for(int i = 0;i < N-1; i++){
                String line = in.nextLine();
                int parent = Integer.parseInt(line.split(" ")[0]);
                int son = Integer.parseInt(line.split(" ")[1]);
                List<Integer> sons = null;
                if(familyMap.containsKey(parent)){
                    sons = familyMap.get(parent);
                } else {
                    sons = new ArrayList<>();
                }
                sons.add(son);
                familyMap.put(parent,sons);
            }
            int maxMoney = 0;
            for(Entry<Integer, List<Integer>> entry : familyMap.entrySet()){
                List<Integer> sonList = entry.getValue();
                int parent = entry.getKey();
                int moneyCn = moneyList.get(parent-1);
                for(int son : sonList){
                    moneyCn += moneyList.get(son-1);
                }
                if(moneyCn > maxMoney){
                    maxMoney = moneyCn;
                }
            }
            System.out.println(maxMoney);
        }
    }
}