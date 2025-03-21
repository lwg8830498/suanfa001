package com.het.demotest.e100.日志采集;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> logs = new ArrayList<>(); // 用来存储每个时刻的日志条数

        // 输入每个时刻的日志条数，直到输入结束
        while (scanner.hasNextInt()) {
            logs.add(scanner.nextInt());
        }

        int currentLogs = 0;  // 当前积累的日志条数
        int maxScore = Integer.MIN_VALUE;  // 初始化最大积分为最小的整数
        int totalDelayPenalty = 0; // 总的延迟扣分

        // 遍历日志序列，寻找最佳的上报时间点
        for (int t = 0; t < logs.size(); t++) {
            totalDelayPenalty += currentLogs;  // 所有日志的总延迟时间
            currentLogs += logs.get(t);  // 累加当前时间点的日志条数

            if (currentLogs >= 100) {  // 如果日志条数达到或超过100，必须上报
                maxScore = Math.max(maxScore, 100 - totalDelayPenalty);  // 更新最大积分
                break;
            }

            int currentScore = currentLogs - totalDelayPenalty;  // 计算当前积分
            maxScore = Math.max(maxScore, currentScore);  // 更新最大积分
        }

        System.out.println(maxScore);  // 输出最大积分

        scanner.close();
    }
}