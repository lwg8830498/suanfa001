package com.het.demotest.e100.内存资源分配;

import java.util.*;

public class Main {

    // 将字符串按照指定的分隔符分割，返回一个字符串列表
    public static List<String> split(String str, String delimiter) {
        List<String> tokens = new ArrayList<>();
        StringTokenizer tokenStream = new StringTokenizer(str, delimiter);
        while (tokenStream.hasMoreTokens()) {
            tokens.add(tokenStream.nextToken());
        }
        return tokens;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取内存池和申请列表的输入
        String memoryPoolStr = scanner.nextLine();   // 第一行：内存池资源列表
        String requestsStr = scanner.nextLine();     // 第二行：内存申请列表

        // 解析内存池资源
        List<Map.Entry<Integer, Integer>> memoryPool = new ArrayList<>();
        List<String> memoryInfo = split(memoryPoolStr, ","); // 分割内存池资源，按逗号分隔
        for (String info : memoryInfo) {
            List<String> mem = split(info, ":"); // 按冒号分割内存大小和数量
            int size = Integer.parseInt(mem.get(0));     // 转换内存大小
            int quantity = Integer.parseInt(mem.get(1)); // 转换数量
            memoryPool.add(new AbstractMap.SimpleEntry<>(size, quantity));
        }

        // 按内存粒度大小升序排序
        memoryPool.sort(Map.Entry.comparingByKey());

        // 解析内存申请列表
        List<Integer> requests = new ArrayList<>();
        List<String> requestsInfo = split(requestsStr, ","); // 按逗号分割申请列表
        for (String req : requestsInfo) {
            if (!req.isEmpty()) { // 防止空字符串导致错误
                requests.add(Integer.parseInt(req)); // 转换为整数并添加到申请列表
            }
        }

        // 内存分配结果列表
        List<Boolean> results = new ArrayList<>();

        // 遍历每个申请
        for (int req : requests) {
            // 使用二分查找找到能满足当前申请的最小内存块
            int left = 0, right = memoryPool.size() - 1;
            int allocIndex = -1; // 存储找到的合适内存块索引

            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (memoryPool.get(mid).getKey() >= req) {
                    allocIndex = mid; // 找到一个符合的内存块，继续往更小的查找
                    right = mid - 1;
                } else {
                    left = mid + 1;  // 查找更大的内存块
                }
            }

            // 检查是否找到合适的内存块且有足够的数量
            if (allocIndex != -1 && memoryPool.get(allocIndex).getValue() > 0) {
                // 分配内存，数量减少
                Map.Entry<Integer, Integer> entry = memoryPool.get(allocIndex);
                entry.setValue(entry.getValue() - 1); // 数量减少1

                // 如果该内存块数量减少为0，删除该条目
                if (entry.getValue() == 0) {
                    memoryPool.remove(allocIndex); // 删除当前条目
                }

                results.add(true);  // 分配成功
            } else {
                results.add(false); // 分配失败
            }
        }

        // 输出分配结果
        for (int i = 0; i < results.size(); i++) {
            System.out.print(results.get(i) ? "true" : "false");
            if (i != results.size() - 1) {
                System.out.print(",");  // 用逗号分隔结果
            }
        }
        System.out.println();
    }
}