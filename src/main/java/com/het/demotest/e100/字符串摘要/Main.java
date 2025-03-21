package com.het.demotest.e100.字符串摘要;

import java.util.*;

public class Main {
    public static String summarizeString(String input) {
        StringBuilder filtered = new StringBuilder();
        // 去除非字母字符，并将所有字母转换为小写
        for (char c : input.toCharArray()) {
            if (Character.isLetter(c)) {
                filtered.append(Character.toLowerCase(c));
            }
        }

        Map<Character, Integer> countMap = new HashMap<>();  // 存储每个字符在字符串中出现的总次数
        List<Pair<Character, Integer>> continuous = new ArrayList<>();  // 存储连续出现的字符及其出现次数
        List<Pair<Character, Integer>> discontinuous = new ArrayList<>();  // 存储非连续出现的字符及其总次数
        int i = 0;

        // 遍历过滤后的字符串，统计连续字符的次数
        while (i < filtered.length()) {
            char currentChar = filtered.charAt(i);
            int count = 1;
            i++;
            while (i < filtered.length() && filtered.charAt(i) == currentChar) {
                count++;
                i++;
            }

            countMap.put(currentChar, countMap.getOrDefault(currentChar, 0) + count);  // 更新字符的总出现次数
            if (count > 1) {
                continuous.add(new Pair<>(currentChar, count));  // 连续出现，直接记录次数
            } else {
                discontinuous.add(new Pair<>(currentChar, countMap.get(currentChar)));  // 非连续出现，记录当前累计的总次数
            }
        }

        List<Pair<Character, Integer>> result = new ArrayList<>();
        // 先将连续出现的字符添加到结果中
        result.addAll(continuous);
        // 然后将非连续出现的字符添加到结果中，减去当前记录的次数以计算后续出现的次数
        for (Pair<Character, Integer> p : discontinuous) {
            result.add(new Pair<>(p.getKey(), countMap.get(p.getKey()) - p.getValue()));
        }

        // 对结果进行排序，数字大的在前，数字相同则按字母顺序排
        result.sort((a, b) -> {
            if (!a.getValue().equals(b.getValue())) {
                return b.getValue() - a.getValue();
            }
            return Character.compare(a.getKey(), b.getKey());
        });

        // 拼接输出字符串
        StringBuilder output = new StringBuilder();
        for (Pair<Character, Integer> p : result) {
            output.append(p.getKey()).append(p.getValue());
        }

        return output.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();  // 使用nextLine以支持包含空格的输入
        System.out.println(summarizeString(input));
        scanner.close();
    }

    // 自定义 Pair 类用于存储字符及其对应的次数
    static class Pair<K, V> {
        private final K key;
        private final V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }
}