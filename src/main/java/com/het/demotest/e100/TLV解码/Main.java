package com.het.demotest.e100.TLV解码;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 读取目标Tag
        String targetTag = scanner.nextLine().trim();
        // 读取16进制码流字符串
        String hexStream = scanner.nextLine().trim();

        // 将16进制码流分割为单独的字节
        String[] bytes = hexStream.split(" ");

        int i = 0;
        while (i < bytes.length) {
            String tag = bytes[i];
            if (i + 2 < bytes.length) {
                // 解析长度（小端序）
                int length = Integer.parseInt(bytes[i + 1], 16) + 256 * Integer.parseInt(bytes[i + 2], 16);
                if (tag.equalsIgnoreCase(targetTag)) {
                    // 如果找到匹配的Tag，提取并输出其Value
                    StringBuilder sb = new StringBuilder();
                    for (int j = 0; j < length; ++j) {
                        if (i + 3 + j < bytes.length) {
                            sb.append(bytes[i + 3 + j]).append(" ");
                        }
                    }
                    // 去除尾部空格并输出结果
                    System.out.println(sb.toString().trim());
                    scanner.close();
                    return;
                }
                // 跳过当前的TLV块，移到下一个元素
                i += 3 + length;
            } else {
                break;
            }
        }

        System.out.println("Tag not found");
        scanner.close();
    }
}