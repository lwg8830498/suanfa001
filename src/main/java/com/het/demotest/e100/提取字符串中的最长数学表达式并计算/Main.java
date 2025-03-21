package com.het.demotest.e100.提取字符串中的最长数学表达式并计算;

import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    // 功能：计算简单数学表达式的值
    public static long evaluateExpression(String expression) {
        if (expression.isEmpty()) {
            return 0; // 如果表达式为空，返回 0
        }

        Stack<Long> values = new Stack<>(); // 使用栈来存储中间结果
        long currentNumber = 0; // 当前解析的数字
        char sign = '+'; // 当前数字前的操作符，默认为 '+'
        expression += "+"; // 在表达式末尾追加 '+'
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (Character.isDigit(c)) { // 如果字符是数字
                currentNumber = currentNumber * 10 + (c - '0'); // 累加形成整数
            } else if (c == '+' || c == '-' || c == '*' || i == expression.length() - 1) { // 如果字符是操作符或已达字符串末尾
                if (c != '*' && !values.isEmpty() && values.peek() == -1) {
                    values.pop(); // 如果栈顶为 -1（之前的符号为减号），则弹出
                    sign = '-';  // 将符号改为负号
                }
                switch (sign) {
                    case '+':
                        values.push(currentNumber); // 加法：将当前数字压入栈
                        break;
                    case '-':
                        values.push(-currentNumber); // 减法：将当前数字的负值压入栈
                        break;
                    case '*':
                        long top = values.pop(); // 取出栈顶元素
                        values.push(top * currentNumber); // 执行乘法并将结果压回栈
                        break;
                }
                currentNumber = 0; // 重置当前数字
                sign = c; // 更新操作符
            }
        }

        long result = 0; // 初始化结果
        while (!values.empty()) {
            result += values.pop(); // 将栈中所有数字累加
        }
        return result; // 返回计算结果
    }

    // 功能：提取最长的合法数学表达式并计算其值
    public static  long findAndEvaluate(String s) {
        Pattern expRegex = Pattern.compile("(\\d+)([\\*+-]\\d+)*"); // 定义正则表达式，匹配数字和操作符序列
        Matcher matcher = expRegex.matcher(s);

        String longestValidExpression = "";
        int maxLength = 0;

        while (matcher.find()) {
            String matchStr = matcher.group(); // 获取匹配的字符串
            if (matchStr.length() > maxLength && matchStr.matches("^(-?\\d+([+\\-*]\\d+)+)$")) {
                maxLength = matchStr.length(); // 更新最大长度
                longestValidExpression = matchStr; // 更新最长的表达式
            }
        }

        if (longestValidExpression.isEmpty()) {
            return 0; // 如果没有合法表达式，返回 0
        } else {
            return evaluateExpression(longestValidExpression); // 计算并返回表达式的值
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        String input = scanner.nextLine(); // 从标准输入读取字符串
        System.out.println(findAndEvaluate(input)); // 输出最长合法表达式的计算结果
        scanner.close();
    }
}