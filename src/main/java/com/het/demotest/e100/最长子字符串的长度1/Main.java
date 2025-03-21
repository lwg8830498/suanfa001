package com.het.demotest.e100.最长子字符串的长度1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String inputStr = scanner.nextLine().trim();
            int charONums = countOccurrences(inputStr, 'o');
            if (charONums % 2 == 0) {
                System.out.println(inputStr.length());
            } else {
                System.out.println(inputStr.length() - 1);
            }
        }
        scanner.close();
    }

    private static int countOccurrences(String str, char ch) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ch) {
                count++;
            }
        }
        return count;
    }
}