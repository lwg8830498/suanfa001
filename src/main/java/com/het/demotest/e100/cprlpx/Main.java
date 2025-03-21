package com.het.demotest.e100.cprlpx;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine(); // 读取换行符

        // 存储磁盘容量字符串和对应的总容量（以 M 为单位）
        List<Disk> disks = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String capacityStr = scanner.nextLine();
            long totalCapacity = parseCapacity(capacityStr); // 解析总容量
            disks.add(new Disk(capacityStr, totalCapacity));
        }

        // 稳定排序：按总容量排序，保持原有顺序
        disks.sort(Comparator.comparingLong(Disk::getTotalCapacity));

        // 输出结果
        for (Disk disk : disks) {
            System.out.println(disk.getCapacityStr());
        }
    }

    // 解析磁盘容量字符串，返回总容量（以 M 为单位）
    private static long parseCapacity(String capacityStr) {
        long total = 0;
        int i = 0;
        while (i < capacityStr.length()) {
            // 找到数字的起始位置
            int numStart = i;
            while (i < capacityStr.length() && Character.isDigit(capacityStr.charAt(i))) {
                i++;
            }
            // 解析数字
            int num = Integer.parseInt(capacityStr.substring(numStart, i));
            // 解析单位
            char unit = capacityStr.charAt(i);
            i++;
            // 根据单位转换为 M
            switch (unit) {
                case 'M':
                    total += num;
                    break;
                case 'G':
                    total += num * 1024L;
                    break;
                case 'T':
                    total += num * 1024L * 1024L;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid unit: " + unit);
            }
        }
        return total;
    }

    // 磁盘类，存储容量字符串和总容量
    private static class Disk {
        private final String capacityStr;
        private final long totalCapacity;

        public Disk(String capacityStr, long totalCapacity) {
            this.capacityStr = capacityStr;
            this.totalCapacity = totalCapacity;
        }

        public String getCapacityStr() {
            return capacityStr;
        }

        public long getTotalCapacity() {
            return totalCapacity;
        }
    }
}