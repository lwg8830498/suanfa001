package com.het.demotest.e100.过滤组合字符串;

import java.util.*;

public class Main {
    private static final Map<Integer, List<Character>> digitToLetters = new HashMap<>();

    static {
        digitToLetters.put(0, Arrays.asList('a', 'b', 'c'));
        digitToLetters.put(1, Arrays.asList('d', 'e', 'f'));
        digitToLetters.put(2, Arrays.asList('g', 'h', 'i'));
        digitToLetters.put(3, Arrays.asList('j', 'k', 'T'));
        digitToLetters.put(4, Arrays.asList('m', 'n', 'o'));
        digitToLetters.put(5, Arrays.asList('p', 'q', 'r'));
        digitToLetters.put(6, Arrays.asList('s', 't'));
        digitToLetters.put(7, Arrays.asList('u', 'v'));
        digitToLetters.put(8, Arrays.asList('w', 'x'));
        digitToLetters.put(9, Arrays.asList('y', 'z'));
    }

    public static void main(String[] args) {
        String input = "78";
        String filter = "ux";
        List<String> combinations = generateCombinations(input);
        List<String> filteredCombinations = filterCombinations(combinations, filter);
        System.out.println(String.join(" ", filteredCombinations));
    }

    private static List<String> generateCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return result;
        }
        generateCombinationsHelper(digits, 0, new StringBuilder(), result);
        return result;
    }

    private static void generateCombinationsHelper(String digits, int index, StringBuilder current, List<String> result) {
        if (index == digits.length()) {
            result.add(current.toString());
            return;
        }
        int digit = Character.getNumericValue(digits.charAt(index));
        List<Character> letters = digitToLetters.get(digit);
        for (char letter : letters) {
            current.append(letter);
            generateCombinationsHelper(digits, index + 1, current, result);
            current.deleteCharAt(current.length() - 1);
        }
    }

    private static List<String> filterCombinations(List<String> combinations, String filter) {
        List<String> result = new ArrayList<>();
        Set<Character> filterSet = new HashSet<>();
        for (char c : filter.toCharArray()) {
            filterSet.add(c);
        }
        for (String combination : combinations) {
            if (!containsAllChars(combination, filterSet)) {
                result.add(combination);
            }
        }
        return result;
    }

    private static boolean containsAllChars(String str, Set<Character> chars) {
        for (char c : chars) {
            if (str.indexOf(c) == -1) {
                return false;
            }
        }
        return true;
    }
}