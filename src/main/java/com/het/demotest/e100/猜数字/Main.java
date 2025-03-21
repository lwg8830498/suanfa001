package com.het.demotest.e100.猜数字;

import java.util.Scanner;

/**
 * @author liwen
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt(); // 输入猜测次数
        String[] guesses = new String[N]; // 存储猜测的数字
        String[] hints = new String[N]; // 存储提示

        // 读取猜测和提示
        for (int i = 0; i < N; i++) {
            guesses[i] = scanner.next();
            hints[i] = scanner.next();
        }

        String answer = "NA"; // 初始化答案为NA
        int count = 0; // 记录满足条件的谜底数量

        // 枚举所有可能的四位数
        for (int num = 0; num <= 9999; num++) {
            String candidate = String.format("%04d", num); // 格式化为四位数
            boolean isValid = true;

            // 检查当前候选数是否与所有猜测和提示匹配
            for (int i = 0; i < N; i++) {
                if (!checkHint(candidate, guesses[i], hints[i])) {
                    isValid = false;
                    break;
                }
            }

            // 如果匹配，则记录答案
            if (isValid) {
                answer = candidate;
                count++;
            }

            // 如果找到多个可能的谜底，直接输出NA
            if (count > 1) {
                answer = "NA";
                break;
            }
        }

        System.out.println(answer); // 输出答案
    }

    // 检查候选数是否与猜测和提示匹配
    private static boolean checkHint(String candidate, String guess, String hint) {
        int A = 0, B = 0;

        // 计算A（数字和位置都正确）
        for (int i = 0; i < 4; i++) {
            if (candidate.charAt(i) == guess.charAt(i)) {
                A++;
            }
        }

        // 计算B（数字正确但位置不对）
        int[] countCandidate = new int[10];
        int[] countGuess = new int[10];

        for (int i = 0; i < 4; i++) {
            if (candidate.charAt(i) != guess.charAt(i)) {
                countCandidate[candidate.charAt(i) - '0']++;
                countGuess[guess.charAt(i) - '0']++;
            }
        }

        for (int i = 0; i < 10; i++) {
            B += Math.min(countCandidate[i], countGuess[i]);
        }

        // 构造提示字符串
        String expectedHint = A + "A" + B + "B";

        // 比较实际提示和期望提示
        return expectedHint.equals(hint);
    }
}