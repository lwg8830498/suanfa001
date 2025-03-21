package com.het.demotest.e200.字符统计及重排;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        scanner.close();

        Map<Character, Integer> charCountMap = new HashMap<>();
        for (char c : input.toCharArray()) {
            charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
        }

        List<Map.Entry<Character, Integer>> sortedEntries = new ArrayList<>(charCountMap.entrySet());
        sortedEntries.sort((entry1, entry2) -> {
            int countCompare = entry2.getValue().compareTo(entry1.getValue());
            if (countCompare != 0) {
                return countCompare;
            } else {
                char c1 = entry1.getKey();
                char c2 = entry2.getKey();
                if (Character.isLowerCase(c1) && Character.isUpperCase(c2)) {
                    return -1;
                } else if (Character.isUpperCase(c1) && Character.isLowerCase(c2)) {
                    return 1;
                } else {
                    return Character.compare(c1, c2);
                }
            }
        });

        StringBuilder result = new StringBuilder();
        for (Map.Entry<Character, Integer> entry : sortedEntries) {
            result.append(entry.getKey()).append(":").append(entry.getValue()).append(";");
        }

        System.out.println(result.toString());
    }
}