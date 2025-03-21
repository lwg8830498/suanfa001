package com.het.demotest.e100.汽水瓶;

import java.util.*;
import java.util.stream.Collectors;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    static Map<Character, Integer> map = new HashMap<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<Integer> res = new ArrayList<>();
        while (true) {
            int n = in.nextInt();
            if(n==0){
                break;
            }
            res.add(soutNum(n));
        }
        in.close();
        for (Integer re : res) {
            System.out.println(re);
        }
    }

    public static int soutNum(int n) {
        int sum = 0;
        while (n >= 3) {
            int h = n / 3;
            sum += h;
            n = (n % 3) + h;
            if (n == 2) {
                sum += 1;
            }
        }
        return sum;
    }
}