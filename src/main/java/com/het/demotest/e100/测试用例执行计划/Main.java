package com.het.demotest.e100.测试用例执行计划;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s1 = in.nextLine();
        int txNum = Integer.parseInt(s1.split(" ")[0]);
        int ylNum = Integer.parseInt(s1.split(" ")[1]);
        int[] txList = new int[txNum];
        ArrayList<Map<Integer, Integer>> maps = new ArrayList<Map<Integer, Integer>>();
        for (int i = 0; i < txNum; i++) {
            txList[i] = Integer.parseInt(in.nextLine());
        }
        for (int j = 0; j < ylNum; j++) {
            int score = 0;
            HashMap<Integer, Integer> ylMap = new HashMap<Integer, Integer>();;
            for (String k : in.nextLine().split(" ")) {
                score += txList[Integer.parseInt(k) - 1];
            }
            ylMap.put(j + 1, score);
            maps.add(ylMap);
        }
        Collections.sort(maps, new Comparator<Map<Integer, Integer>>() {
            public int compare(Map<Integer, Integer> o1, Map<Integer, Integer> o2) {
                Iterator<Integer> iterator1 = o1.keySet().iterator();
                int key1 = iterator1.next();
                Iterator<Integer> iterator2 = o2.keySet().iterator();
                int key2 = iterator2.next();
                if (o1.get(key1) > o2.get(key2)) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });
        for (Map map : maps) {
            Iterator<Integer> iterator1 = map.keySet().iterator();
            int key = iterator1.next();
            System.out.println(key);
        }
    }
}