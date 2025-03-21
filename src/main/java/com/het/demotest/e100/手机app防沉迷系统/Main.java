package com.het.demotest.e100.手机app防沉迷系统;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static class App {
        String name;
        int priority;
        int startTime;
        int endTime;

        public App(String name, int priority, int startTime, int endTime) {
            this.name = name;
            this.priority = priority;
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }

    // 将时间字符串（格式HH:MM）转换为一天中从00:00开始的分钟数
    public static int timeToMinutes(String time) {
        int hours = Integer.parseInt(time.substring(0, 2));
        int minutes = Integer.parseInt(time.substring(3, 5));
        return hours * 60 + minutes;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt(); // 读取要注册的App数量
        scanner.nextLine(); // 读取并忽略剩余的行结束符

        List<App> registeredApps = new ArrayList<>(); // 存储所有注册成功的App

        for (int i = 0; i < N; i++) {
            String line = scanner.nextLine(); // 读取一行App注册信息
            String[] parts = line.split(" ");
            String name = parts[0];
            int priority = Integer.parseInt(parts[1]);
            int startTime = timeToMinutes(parts[2]);
            int endTime = timeToMinutes(parts[3]) - 1; // 转换结束时间并-1，以符合时间包含开始但不包括结束的规则
            if(startTime > endTime) {
                continue;
            }
            boolean canRegister = true;
            List<App> updatedApps = new ArrayList<>();

            // 检查时间冲突并确定是否可以注册
            for (App app : registeredApps) {
                if (startTime <= app.endTime &&  endTime >= app.startTime) {
                    if (priority <= app.priority) {
                        canRegister = false;
                        break;
                    }
                }
            }

            if (canRegister) {
                // 剔除所有时间上冲突的App
                for (App app : registeredApps) {
                    if (!(startTime <= app.endTime &&  endTime >= app.startTime)) {
                        updatedApps.add(app);
                    }
                }
                updatedApps.add(new App(name, priority, startTime, endTime));
                registeredApps = updatedApps;
            }
        }

        String queryTime = scanner.nextLine(); // 读取查询时间
        int queryMinute = timeToMinutes(queryTime); // 转换查询时间为分钟

        // 检查并输出查询时间点的App
        for (App app : registeredApps) {
            if (app.startTime <= queryMinute && app.endTime >= queryMinute) {
                System.out.println(app.name);
                scanner.close();
                return;
            }
        }
        System.out.println("NA"); // 如果没有任何App活动于此时间点，输出"NA"
        scanner.close();
    }
}