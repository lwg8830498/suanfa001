package com.het.demotest.e100.字符串重新排序;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    // 按字母顺序对单词内部排序
    public static String sortWord(String word) {
        char[] chars = word.toCharArray();
        Arrays.sort(chars); // 使用 Arrays.sort() 进行字母排序
        return new String(chars);
    }

    // 主处理函数
    public static String processString(String input) {
        // 使用 HashMap 统计频次
        Map<String, Integer> wordCount = new HashMap<>();
        String[] words = input.split("\\s+"); // 按空格拆分单词

        // 遍历每个单词并按字母排序后统计频次
        for (String word : words) {
            String sortedWord = sortWord(word);
            wordCount.put(sortedWord, wordCount.getOrDefault(sortedWord, 0) + 1);
        }

        // 将结果转换为 List，准备进行排序
        List<Map.Entry<String, Integer>> sortedWords = new ArrayList<>(wordCount.entrySet());

        // 自定义排序规则
        sortedWords.sort((a, b) -> {
            if (!a.getValue().equals(b.getValue())) {
                return b.getValue() - a.getValue(); // 按频次降序
            }
            if (a.getKey().length() != b.getKey().length()) {
                return a.getKey().length() - b.getKey().length(); // 按长度升序
            }
            return a.getKey().compareTo(b.getKey()); // 按字典序排序
        });

        // 构造结果字符串
        return sortedWords.stream()
                .flatMap(e -> Collections.nCopies(e.getValue(), e.getKey()).stream())
                .collect(Collectors.joining(" "));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 从标准输入读取多行输入
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                break; // 输入为空时退出
            }
            System.out.println(processString(input)); // 输出处理结果
        }
    }
}