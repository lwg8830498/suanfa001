package com.het.demotest.e200.求满足条件的最长子串的长度;

public class Main {
    public static void main(String[] args) {
        String input = "abc124ACb";
        System.out.println(findLongestSubstring(input));
    }

    public static int findLongestSubstring(String s) {
        int maxLen = -1;
        int n = s.length();

        // 遍历字符串中的每个字符
        for (int i = 0; i < n; i++) {
            if (Character.isLetter(s.charAt(i))) {
                // 找到一个字母，尝试扩展子串
                int left = i;
                int right = i;
                int letterCount = 1;

                // 向左扩展
                while (left > 0 && (Character.isDigit(s.charAt(left - 1)) || (Character.isLetter(s.charAt(left - 1)) && letterCount < 2))) {
                    left--;
                    if (Character.isLetter(s.charAt(left))) {
                        letterCount++;
                        if (letterCount > 1) {
                            left++;
                            break;
                        }
                    }
                }

                // 向右扩展
                while (right < n - 1 && (Character.isDigit(s.charAt(right + 1)) || (Character.isLetter(s.charAt(right + 1)) && letterCount < 2))) {
                    right++;
                    if (Character.isLetter(s.charAt(right))) {
                        letterCount++;
                        if (letterCount > 1) {
                            right--;
                            break;
                        }
                    }
                }

                // 计算子串长度
                if (letterCount == 1) {
                    maxLen = Math.max(maxLen, right - left + 1);
                }
            }
        }

        // 如果字符串全是字母或全是数字，返回-1
        if (maxLen == -1) {
            boolean allLetters = true;
            boolean allDigits = true;
            for (char c : s.toCharArray()) {
                if (!Character.isLetter(c)) {
                    allLetters = false;
                }
                if (!Character.isDigit(c)) {
                    allDigits = false;
                }
            }
            if (allLetters || allDigits) {
                return -1;
            }
        }

        return maxLen;
    }
}