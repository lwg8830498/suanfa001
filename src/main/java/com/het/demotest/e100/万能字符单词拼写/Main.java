package com.het.demotest.e100.万能字符单词拼写;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = Integer.parseInt(scanner.nextLine()); // 读取words的数量
        String[] words = new String[N];
        for (int i = 0; i < N; i++) {
            words[i] = scanner.nextLine(); // 逐行读取单词
        }
        String chars = scanner.nextLine(); // 读取可用字符集
        scanner.close();

        System.out.println(countWordsYouCanForm(words, chars));
    }

    private static int countWordsYouCanForm(String[] words, String chars) {
        int count = 0;
        int[] charCount = new int[26]; // 存储每个字母的数量
        int wildcardCount = 0; // 用来计数“?”

        // 计算chars中每个字母的出现频率以及万能字符的数量
        for (char c : chars.toCharArray()) {
            if (c == '?') {
                wildcardCount++;
            } else {
                charCount[c - 'a']++;
            }
        }

        for (String word : words) {
            if (canFormWord(word, charCount.clone(), wildcardCount)) {
                count++;
            }
        }

        return count;
    }

    private static boolean canFormWord(String word, int[] charCount, int wildcardCount) {
        int[] wordCount = new int[26];
        for (char c : word.toCharArray()) {
            wordCount[c - 'a']++;
        }

        for (int i = 0; i < 26; i++) {
            if (wordCount[i] > charCount[i]) {
                // 如果某个字母的需求超过了chars中的数量，尝试使用万能字符
                int needed = wordCount[i] - charCount[i];
                if (wildcardCount >= needed) {
                    wildcardCount -= needed;
                } else {
                    return false;
                }
            }
        }

        return true;
    }
}