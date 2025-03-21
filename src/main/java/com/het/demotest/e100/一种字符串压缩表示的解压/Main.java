package com.het.demotest.e100.一种字符串压缩表示的解压;

import java.util.Scanner;

//一种字符串压缩表示的解压
public class Main {

    public static String decompress(String input) {
        StringBuilder result = new StringBuilder();
        int i = 0;
        int n = input.length();
        char lastChar = 0;

        while (i < n) {
            if (Character.isDigit(input.charAt(i)) && input.charAt(i) != '0') { // 当前字符是数字且不为0
                int count = 0;

                // 读取所有连续的数字字符
                while (i < n && Character.isDigit(input.charAt(i))) {
                    count = count * 10 + (input.charAt(i) - '0');
                    i++;
                }

                // 检查数字后是否有小写字母且数字大于2
                if (i < n && Character.isLowerCase(input.charAt(i)) && count > 2 && lastChar != input.charAt(i)) {
                    // 生成连续的字母
                    for (int j = 0; j < count; j++) {
                        result.append(input.charAt(i));
                    }
                    lastChar = input.charAt(i);
                    i++;
                } else {
                    // 不合法输入
                    return "!error";
                }
            } else if (Character.isLowerCase(input.charAt(i))) { // 当前字符是小写字母
                // 检查连续相同字母的数量
                int count = 1;
                char currentChar = input.charAt(i);
                i++;

                while (i < n && input.charAt(i) == currentChar) {
                    count++;
                    i++;
                }

                // 如果连续字符超过两个且未被压缩，则输入非法
                if (count > 2 || lastChar == currentChar) {
                    return "!error";
                }
                lastChar = currentChar;
                for (int j = 0; j < count; j++) {
                    result.append(currentChar);
                }
            } else {
                // 不合法输入
                return "!error";
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        String output = decompress(input);
        System.out.println(output);
    }
}