package com.het.demotest.e200.字母组合;

import java.util.*;

public class Main {

    // 递归函数：生成所有可能的字母组合
    private static void generateCombinations(String digits, Map<Character, String> digitToLetters, StringBuilder current, int index, List<String> result, Set<Character> blockSet) {
        if (index == digits.length()) {
            // 检查组合是否包含屏蔽字符串中的所有字符
            boolean isValid = true;
            for (char c : blockSet) {
                if (current.indexOf(String.valueOf(c)) == -1) {
                    isValid = false;
                    break;
                }
            }
            if (!isValid) {
                result.add(current.toString());
            }
            return;
        }

        char digit = digits.charAt(index);
        String letters = digitToLetters.get(digit);
        for (char letter : letters.toCharArray()) {
            current.append(letter);
            generateCombinations(digits, digitToLetters, current, index + 1, result, blockSet);
            current.deleteCharAt(current.length() - 1);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取输入
        String digits = scanner.nextLine().replace(" ", "");
        String blockString = scanner.nextLine().replace(" ", "");

        // 定义数字到字母的映射关系
        Map<Character, String> digitToLetters = new HashMap<>();
        digitToLetters.put('0', "abc");
        digitToLetters.put('1', "def");
        digitToLetters.put('2', "ghi");
        digitToLetters.put('3', "jkl");
        digitToLetters.put('4', "mno");
        digitToLetters.put('5', "pqr");
        digitToLetters.put('6', "st");
        digitToLetters.put('7', "uv");
        digitToLetters.put('8', "wx");
        digitToLetters.put('9', "yz");

        // 将屏蔽字符串转换为字符集合
        Set<Character> blockSet = new HashSet<>();
        for (char c : blockString.toCharArray()) {
            blockSet.add(c);
        }

        List<String> result = new ArrayList<>();
        StringBuilder current = new StringBuilder();

        // 生成所有组合
        generateCombinations(digits, digitToLetters, current, 0, result, blockSet);

        // 输出结果
        for (int i = 0; i < result.size(); i++) {
            System.out.print(result.get(i));
            if (i < result.size() - 1) {
                System.out.print(",");
            }
        }
        System.out.println(",");

        scanner.close();
    }
}