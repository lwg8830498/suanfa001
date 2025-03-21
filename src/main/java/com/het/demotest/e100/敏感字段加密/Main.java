package com.het.demotest.e100.敏感字段加密;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    // 将字符串按照需求解析为命令字
    public static ArrayList<String> parseCommandString(String s) {
        ArrayList<String> result = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean inQuotes = false;  // 标记是否在双引号内

        for (char c : s.toCharArray()) {
            if (c == '"') {
                inQuotes = !inQuotes;  // 遇到双引号，切换状态
                current.append(c);
            } else if (c == '_' && !inQuotes) {
                if (current.length() > 0) {
                    result.add(current.toString());  // 非双引号内，遇到下划线则结束一个命令字
                    current.setLength(0);  // 清空StringBuilder
                }
            } else {
                current.append(c);  // 继续累加字符
            }
        }

        if (current.length() > 0) {
            result.add(current.toString());  // 添加最后一个命令字
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 输入索引K
        int K = scanner.nextInt();
        scanner.nextLine();  // 忽略回车
        // 输入命令字符串S
        String S = scanner.nextLine();

        // 解析命令字符串
        ArrayList<String> commands = parseCommandString(S);

        // 检查索引是否有效
        if (K < 0 || K >= commands.size()) {
            System.out.println("ERROR");
        } else {
            // 替换敏感字段
            commands.set(K, "******");

            // 输出结果，使用下划线连接命令字
            boolean first = true;
            for (String cmd : commands) {
                if (!first) {
                    System.out.print("_");
                }
                System.out.print(cmd);
                first = false;
            }
            System.out.println();
        }

        scanner.close();
    }
}