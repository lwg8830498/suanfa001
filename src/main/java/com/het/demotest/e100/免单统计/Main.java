package com.het.demotest.e100.免单统计;

import java.util.*;

public class Main {

    // 解析时间字符串，返回秒的部分和毫秒的部分
    public static Map.Entry<String, Integer> parseTime(String time) {
        String datePart = time.substring(0, 19); // yyyy-MM-dd HH:mm:ss，获取前19个字符作为秒的部分
        int millisecond = Integer.parseInt(time.substring(20, 23)); // fff，获取从第20个字符开始的3个字符作为毫秒部分
        return new AbstractMap.SimpleEntry<>(datePart, millisecond);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // 读取顾客数量
        scanner.nextLine(); // 读取完整数后，跳过换行符

        List<String> orders = new ArrayList<>();
        // 读取每个顾客的下单时间
        for (int i = 0; i < n; i++) {
            orders.add(scanner.nextLine());
        }

        // 使用HashMap来存储每秒钟内的最早下单时间（毫秒）
        Map<String, Integer> earliestOrder = new HashMap<>();
        // 使用HashMap来统计每个最早下单时间的顾客数量
        Map<String, Integer> freeOrders = new HashMap<>();

        // 遍历每个订单
        for (String order : orders) {
            // 解析时间，得到秒的部分（datePart）和毫秒（millisecond）
            Map.Entry<String, Integer> parsedTime = parseTime(order);
            String datePart = parsedTime.getKey();
            int millisecond = parsedTime.getValue();

            // 如果该秒内还没有记录最早的下单时间，或者当前订单更早，则更新
            if (!earliestOrder.containsKey(datePart) || millisecond < earliestOrder.get(datePart)) {
                earliestOrder.put(datePart, millisecond); // 更新最早的毫秒时间
                freeOrders.put(datePart, 1); // 当前秒内第一次出现最早下单时间，计数为1
            }
            // 如果当前订单的下单时间和最早时间相同，则增加计数
            else if (millisecond == earliestOrder.get(datePart)) {
                freeOrders.put(datePart, freeOrders.get(datePart) + 1); // 同时下单的顾客也可以免单
            }
        }

        // 统计可以免单的顾客总数
        int freeCustomerCount = 0;
        for (int count : freeOrders.values()) {
            freeCustomerCount += count; // 每秒内最早下单的顾客数量累加
        }

        // 输出可以免单的顾客数量
        System.out.println(freeCustomerCount);
    }
}