package com.het.demotest.e200.德州扑克;

import java.util.*;

public class Main {
    static Map<String, Integer> num = new HashMap<>();// 记录牌的大小部分（大小，个数）
    static Map<Character, Integer> type = new HashMap<>();// 记录牌的花色部分 （花色，个数）
    static Map<String, Integer> idx = new HashMap<>();// 将牌变成连续的一串数字方便排序（大小，编号）

    static boolean fun2() {// 四条 4+1
        int mx = 0;
        for (Map.Entry<String, Integer> p : num.entrySet()) {// 取最多牌的个数 
            mx = Math.max(mx, p.getValue());
        }
        return mx == 4;// 然后判断是不是4
    }

    static boolean fun3() {// 葫芦 3+2
        return num.size() == 2 && (num.values().iterator().next() == 2 || num.values().iterator().next() == 3);// 先判断牌类型是不是两种，然后看第一种牌个数是不是2或者3
    }

    static boolean fun4() {// 同花 11111
        return type.size() == 1;// 只有一种花色，看type大小即可
    }

    static boolean fun5() {// 顺子 
        if (num.size() != 5) {
            return false;// 判断是不是5个不同大小，不是的话有重复肯定不是顺子
        }
        // 然后将牌转化成编号后排序，查看编号是不是连续
        List<Integer> v = new ArrayList<>();
        for (String key : num.keySet()) {
            v.add(idx.get(key));
        }
        Collections.sort(v);

        // 顺子有两种情况，一种是递增的相差1，还有一种是A2345，编号的时候A在5后面
        // 所以先判断前4个是不是递增且相差1，然后判断最后一个跟前一个是不是相差1，如果是那么符合第一个顺子，否则就判断第一个是不是'2'，最后一个是不是'A'就可以了，其中'2'编号是2，'A'编号是14

        for (int i = 0; i < 3; i++) {//前四个不满足递增且相差1
            if (v.get(i) + 1 != v.get(i + 1)) {
                return false;
            }
        }
        if (v.get(3) + 1 == v.get(4)) {
            return true;// 满足第一种顺子
        }
        if (v.get(0) == 2 && v.get(4) == 14) {
            return true;// 满足第二种顺子
        }
        return false;
    }

    static boolean fun6() {// 三条 311
        int mx = 0;
        for (Map.Entry<String, Integer> p : num.entrySet()) {//取最多牌的个数
            mx = Math.max(mx, p.getValue());
        }
        return num.size() == 3 && mx == 3; //判断是不是三个类型并且最大是3
    }

    static boolean fun1() {// 同花顺=同花+顺子
        return fun4() && fun5();
    }

    static int solve() {
        // 将牌映射成连续的编号，用于排序
        for (int i = 2; i <= 10; i++) {
            idx.put(String.valueOf(i), i);
        }
        idx.put("J", 11);
        idx.put("Q", 12);
        idx.put("K", 13);
        idx.put("A", 14);
        // 从1到6先满足的输出，就是满足条件最大的了
        if (fun1()) {
            return 1;
        }
        if (fun2()) {
            return 2;
        }
        if (fun3()) {
            return 3;
        }
        if (fun4()) {
            return 4;
        }
        if (fun5()) {
            return 5;
        }
        if (fun6()) {
            return 6;
        }
        return 7;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 5; i++) {
            String a = scanner.next();
            char b = scanner.next().charAt(0);
            // 记录两者的个数
            num.put(a, num.getOrDefault(a, 0) + 1);
            type.put(b, type.getOrDefault(b, 0) + 1);
        }
        System.out.println(solve());
    }
}