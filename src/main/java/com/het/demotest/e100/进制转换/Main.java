package com.het.demotest.e100.进制转换;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    static Map<Character,Integer> map = new HashMap<>();
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String strIn = in.nextLine();
        String strRes = strIn.substring(2,strIn.length());
        int res = 0;
        setNum();
        for (int i = strRes.length()-1; i >= 0; i--) {
            res += getNum(strRes.charAt(i))*getN(strRes.length() - i);
        }
        System.out.println(res);
    }

    public static int getN(int n){
        int res = 1;
        while(n>1){
            res *= 16;
            n--;
        }
        return res;
    }

    public static void setNum(){
        map.put('0',0);
        map.put('1',1);
        map.put('2',2);
        map.put('3',3);
        map.put('4',4);
        map.put('5',5);
        map.put('6',6);
        map.put('7',7);
        map.put('8',8);
        map.put('9',9);
        map.put('A',10);
        map.put('B',11);
        map.put('C',12);
        map.put('D',13);
        map.put('E',14);
        map.put('F',15);
    }

    public static int getNum(char c){
        return map.get(c);
    }
}