package com.het.demotest.e200.工号不够用了怎么办;

import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long X = sc.nextLong(); // 输入员工数 X
        int Y = sc.nextInt();   // 输入字母部分的长度 Y

        System.out.println(solution(X, Y));
    }

    // 解决函数
    public static int solution(long X, int Y) {
        long letterCombinations = (long) Math.pow(26, Y); // 计算字母部分的排列组合
        long minDigitsLength = 1; // 最短数字长度

        // 计算满足 X 人数的最短数字长度
        while (letterCombinations * Math.pow(10, minDigitsLength) < X) {
            minDigitsLength++;
        }
        return (int) minDigitsLength;
    }
}