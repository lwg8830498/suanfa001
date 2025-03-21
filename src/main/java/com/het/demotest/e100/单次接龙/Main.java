package com.het.demotest.e100.单次接龙;

import java.util.*;

public class Main {

    // 递归函数，用于进行单词接龙
    public static String wordChain(String currentWord, List<String> words, boolean[] used) {
        String nextWord = "";
        int nextIndex = -1;

        // 找到所有可以接龙的单词
        for (int i = 0; i < words.size(); i++) {
            if (!used[i] && words.get(i).charAt(0) == currentWord.charAt(currentWord.length() - 1)) {
                if (nextIndex == -1 ||
                        words.get(i).length() > nextWord.length() ||
                        (words.get(i).length() == nextWord.length() && words.get(i).compareTo(nextWord) < 0)) {
                    nextWord = words.get(i);
                    nextIndex = i;
                }
            }
        }

        // 如果找到了合适的单词，继续接龙
        if (nextIndex != -1) {
            used[nextIndex] = true;
            return currentWord + wordChain(nextWord, words, used);
        } else {
            return currentWord;  // 无法再继续接龙时，返回当前的单词串
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 输入处理
        int K = sc.nextInt();  // 起始单词的索引
        int N = sc.nextInt();  // 单词数量
        sc.nextLine();  // 读取剩余的换行符

        List<String> words = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            words.add(sc.nextLine());
        }

        // 标记哪些单词已经被使用
        boolean[] used = new boolean[N];
        used[K] = true;

        // 从指定的起始单词开始接龙
        String result = wordChain(words.get(K), words, used);

        // 输出最终的结果
        System.out.println(result);
    }
}