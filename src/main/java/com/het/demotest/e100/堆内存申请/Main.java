package com.het.demotest.e100.堆内存申请;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    static class Block implements Comparable<Block> {
        int start;
        int size;

        Block(int start, int size) {
            this.start = start;
            this.size = size;
        }

        // 用于对内存块进行排序
        @Override
        public int compareTo(Block other) {
            return this.start - other.start;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int requestSize = scanner.nextInt(); // 读取期望申请的内存大小

        if (requestSize <= 0 || requestSize > 100) {
            System.out.println(-1); // 请求大小不合法
            scanner.close();
            return;
        }

        ArrayList<Block> usedBlocks = new ArrayList<>();
        while (scanner.hasNext()) {
            int start = scanner.nextInt();
            int size = scanner.nextInt();

            if (start < 0 || size <= 0 || start + size > 100) {
                System.out.println(-1); // 块的起始地址或大小不合法
                scanner.close();
                return;
            }
            usedBlocks.add(new Block(start, size));
        }
        scanner.close();

        // 检查内存块是否重叠
        Collections.sort(usedBlocks);
        for (int i = 1; i < usedBlocks.size(); i++) {
            if (usedBlocks.get(i).start < usedBlocks.get(i - 1).start + usedBlocks.get(i - 1).size) {
                System.out.println(-1); // 内存块重叠
                return;
            }
        }

        // 寻找合适的空闲内存块
        ArrayList<Block> freeBlocks = new ArrayList<>();
        int currentEnd = 0;
        for (Block block : usedBlocks) {
            if (block.start > currentEnd) {
                freeBlocks.add(new Block(currentEnd, block.start - currentEnd));
            }
            currentEnd = block.start + block.size;
        }

        if (currentEnd < 100) {
            freeBlocks.add(new Block(currentEnd, 100 - currentEnd));
        }

        // 选择最合适的空闲块
        int bestFitStart = -1;
        int minSizeDiff = Integer.MAX_VALUE;
        for (Block block : freeBlocks) {
            if (block.size >= requestSize && block.size - requestSize < minSizeDiff) {
                bestFitStart = block.start;
                minSizeDiff = block.size - requestSize;
            }
        }

        System.out.println(bestFitStart); // 输出最合适的起始位置或-1
    }
}