package com.het.demotest.e100.增强的strstr;

import java.util.Scanner;

public class Main {

    public static int enhancedStrStr(String haystack, String needle) {
        int haystackLen = haystack.length();
        int needleLen = needle.length();

        for (int i = 0; i < haystackLen; ++i) {
            int j = 0;
            int k = i;
            boolean match = true;

            while (j < needleLen && k < haystackLen) {
                if (needle.charAt(j) == '[') {  // 开始处理可选段
                    boolean optionMatched = false;
                    ++j;  // 移动到 '[' 后面的第一个字符
                    while (needle.charAt(j) != ']') {
                        if (haystack.charAt(k) == needle.charAt(j)) {
                            optionMatched = true;
                        }
                        ++j;  // 移动到下一个可选字符
                    }
                    if (!optionMatched) {
                        match = false;
                        break;
                    }
                    ++j;  // 跳过 ']'
                } else {  // 处理普通字符
                    if (haystack.charAt(k) != needle.charAt(j)) {
                        match = false;
                        break;
                    }
                    ++j;  // 移动到 needle 的下一个字符
                }
                ++k;  // 移动到 haystack 的下一个字符
            }

            if (match) {
                return i;  // 返回匹配的起始位置
            }
        }

        return -1;  // 没有匹配
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String haystack = scanner.nextLine();
        String needle = scanner.nextLine();

        int result = enhancedStrStr(haystack, needle);
        System.out.println(result);

        scanner.close();
    }
}