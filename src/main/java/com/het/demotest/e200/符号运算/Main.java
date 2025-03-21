package com.het.demotest.e200.符号运算;

import java.util.Stack;

public class Main {

    // 主函数
    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        String expression = scanner.nextLine(); // 从标准输入读取表达式
        scanner.close(); // 关闭扫描器

        try {
            System.out.println(evaluate(expression)); // 计算并打印表达式的结果
        } catch (RuntimeException e) {
            System.out.println(e.getMessage()); // 输出异常信息
        }
    }

    // 分数类
    public static class Fraction {
        int numerator, denominator; // 分子和分母

        public Fraction(int n, int d) {
            numerator = n;
            denominator = d;
            if (denominator == 0) { // 检查分母是否为0
                throw new RuntimeException("ERROR");
            }
            reduce(); // 化简分数
        }

        // 化简分数的方法
        private void reduce() {
            if (denominator < 0) { // 保证分母为正数
                numerator = -numerator;
                denominator = -denominator;
            }
            int gcd_val = gcd(Math.abs(numerator), Math.abs(denominator)); // 计算最大公约数
            numerator /= gcd_val; // 简化分子
            denominator /= gcd_val; // 简化分母
        }

        // 计算最大公约数的静态方法
        private static int gcd(int a, int b) {
            while (b != 0) {
                int t = b;
                b = a % b;
                a = t;
            }
            return a;
        }

        // 分数加法
        public Fraction add(Fraction other) {
            return new Fraction(numerator * other.denominator + other.numerator * denominator,
                    denominator * other.denominator);
        }

        // 分数减法
        public Fraction subtract(Fraction other) {
            return new Fraction(numerator * other.denominator - other.numerator * denominator,
                    denominator * other.denominator);
        }

        // 分数乘法
        public Fraction multiply(Fraction other) {
            return new Fraction(numerator * other.numerator, denominator * other.denominator);
        }

        // 分数除法
        public Fraction divide(Fraction other) {
            if (other.numerator == 0) { // 检查除数是否为0
                throw new RuntimeException("ERROR");
            }
            return new Fraction(numerator * other.denominator, denominator * other.numerator);
        }

        // 覆盖 toString 方法以友好方式显示分数
        @Override
        public String toString() {
            if (denominator == 1) { // 如果分母为1，仅显示分子
                return String.valueOf(numerator);
            } else {
                return numerator + "/" + denominator;
            }
        }
    }

    // 计算运算符优先级
    private static int precedence(char op) {
        if (op == '+' || op == '-') {
            return 1;
        }
        if (op == '*' || op == '/') {
            return 2;
        }
        return 0;
    }

    // 应用运算符进行计算
    private static Fraction applyOp(Fraction a, Fraction b, char op) {
        switch (op) {
            case '+': return a.add(b);
            case '-': return a.subtract(b);
            case '*': return a.multiply(b);
            case '/': return a.divide(b);
            default: throw new RuntimeException("Unexpected operator");
        }
    }

    // 表达式求值函数
    public static Fraction evaluate(String expression) {
        Stack<Fraction> values = new Stack<>(); // 存储分数的栈
        Stack<Character> ops = new Stack<>(); // 存储运算符的栈
        int i = 0;

        while (i < expression.length()) {
            if (Character.isWhitespace(expression.charAt(i))) { // 跳过空白字符
                i++;
                continue;
            }

            if (Character.isDigit(expression.charAt(i))) { // 处理数字，转化为分数
                int val = 0;
                while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                    val = val * 10 + (expression.charAt(i) - '0');
                    i++;
                }
                values.push(new Fraction(val, 1)); // 将数字作为分数压入栈
                i--; // 循环调整
            } else if (expression.charAt(i) == '(') {
                ops.push(expression.charAt(i)); // 左括号压入栈
            } else if (expression.charAt(i) == ')') {
                while (ops.peek() != '(') {
                    Fraction val2 = values.pop();
                    Fraction val1 = values.pop();
                    char op = ops.pop();
                    values.push(applyOp(val1, val2, op)); // 计算直到遇到左括号
                }
                ops.pop(); // 弹出左括号
            } else {
                while (!ops.isEmpty() && precedence(ops.peek()) >= precedence(expression.charAt(i))) {
                    Fraction val2 = values.pop();
                    Fraction val1 = values.pop();
                    char op = ops.pop();
                    values.push(applyOp(val1, val2, op)); // 根据优先级进行运算
                }
                ops.push(expression.charAt(i)); // 将当前运算符压入栈
            }
            i++;
        }

        while (!ops.isEmpty()) {
            Fraction val2 = values.pop();
            Fraction val1 = values.pop();
            char op = ops.pop();
            values.push(applyOp(val1, val2, op)); // 计算所有剩余的运算
        }

        return values.peek(); // 返回计算结果
    }
}