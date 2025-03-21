package com.het.demotest.e100.响应报文时间;

import java.util.Scanner;

public class Main {

    // 计算最大响应时间
    public static long calculateMaxResponseTime(int M) {
        if (M < 128) {
            return M; // 直接返回 M
        } else {
            int mant = M & 0x0F; // 获取低4位
            int exp = (M >> 4) & 0x07; // 获取高3位
            return (mant | 0x10) << (exp + 3); // 计算最大响应时间
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int C = scanner.nextInt(); // 读取查询报文个数
        long minResponseTime = Long.MAX_VALUE; // 初始化最小响应时间为最大值

        for (int i = 0; i < C; i++) {
            long T = scanner.nextLong(); // 读取每个报文的时间 T
            int M = scanner.nextInt(); // 读取最大响应时间 M

            long maxResponseTime = calculateMaxResponseTime(M); // 计算最大响应时间
            long responseTime = T + maxResponseTime; // 计算响应时间

            // 更新最小响应时间
            if (responseTime < minResponseTime) {
                minResponseTime = responseTime;
            }
        }

        System.out.println(minResponseTime); // 输出最小响应时间
        scanner.close(); // 关闭输入
    }
}