package com.het.demotest.e100.相对开音节;

import java.util.Scanner;

public class Main {
    // 判断字符是否为小写字母
    public static boolean isLowerLetter(char c) {
        return (c >= 'a' && c <= 'z');
    }

    // 判断是否为元音字母
    public static boolean isVowel(char c) {
        return "aeiou".indexOf(c) >= 0;
    }

    // 判断子串是否满足相对开音节的条件
    public static boolean isRelativeOpen(String sub) {
        // 四个字符必须均为小写字母
        for (int i = 0; i < 4; i++) {
            if (!isLowerLetter(sub.charAt(i))) {
                return false;
            }
        }
        char c1 = sub.charAt(0), c2 = sub.charAt(1),
                c3 = sub.charAt(2), c4 = sub.charAt(3);
        return (!isVowel(c1)) &&
                (isVowel(c2)) &&
                (!isVowel(c3) && c3 != 'r') &&
                (c4 == 'e');
    }

    // 判断单词是否全为小写字母
    public static boolean isAllLetters(String word) {
        for (int i = 0; i < word.length(); i++) {
            if (!isLowerLetter(word.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        String[] words = line.split(" ");
        int countRes = 0;

        for (String word : words) {
            // 若单词全为字母则反转，否则保持原样
            if (isAllLetters(word)) {
                word = new StringBuilder(word).reverse().toString();
            }
            // 遍历该单词中所有长度为4的子串
            for (int i = 0; i <= word.length() - 4; i++) {
                String sub = word.substring(i, i+4);
                if (isRelativeOpen(sub))
                    countRes++;
            }
        }
        System.out.println(countRes);
        sc.close();
    }
}