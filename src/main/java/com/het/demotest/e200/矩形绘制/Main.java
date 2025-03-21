package com.het.demotest.e200.矩形绘制;

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        List<Integer> xCoords = new ArrayList<>();
        List<Integer> yCoords = new ArrayList<>();
        class Operation {
            char type;
            int x1, y1, x2, y2;
            Operation(char type, int x1, int y1, int x2, int y2) {
                this.type = type;
                this.x1 = x1;
                this.y1 = y1;
                this.x2 = x2;
                this.y2 = y2;
            }
        }
        List<Operation> operations = new ArrayList<>();
        for (int i = 0; i < N; ++i) {
            String opType = scanner.next();
            int x1 = scanner.nextInt();
            int y1 = scanner.nextInt();
            int x2 = scanner.nextInt();
            int y2 = scanner.nextInt();
            operations.add(new Operation(opType.charAt(0), x1, y1, x2, y2));
            xCoords.add(x1);
            xCoords.add(x2);
            yCoords.add(y1);
            yCoords.add(y2);
        }

        // 坐标离散化
        List<Integer> xList = new ArrayList<>(new HashSet<>(xCoords));
        List<Integer> yList = new ArrayList<>(new HashSet<>(yCoords));
        Collections.sort(xList);
        Collections.sort(yList);
        Map<Integer, Integer> xMap = new HashMap<>();
        Map<Integer, Integer> yMap = new HashMap<>();
        for (int i = 0; i < xList.size(); ++i) {
            xMap.put(xList.get(i), i);
        }
        for (int i = 0; i < yList.size(); ++i) {
            yMap.put(yList.get(i), i);
        }

        // 初始化位集
        BitSet[] grid = new BitSet[yList.size()];
        for (int i = 0; i < yList.size(); ++i) {
            grid[i] = new BitSet(xList.size());
        }

        // 按顺序处理操作
        for (Operation op : operations) {
            int x1 = xMap.get(Math.min(op.x1, op.x2));
            int x2 = xMap.get(Math.max(op.x1, op.x2));
            int y1 = yMap.get(Math.min(op.y1, op.y2));
            int y2 = yMap.get(Math.max(op.y1, op.y2));
            for (int y = y1; y < y2; ++y) {
                if (op.type == 'd') {
                    grid[y].set(x1, x2);
                } else if (op.type == 'e') {
                    grid[y].clear(x1, x2);
                }
            }
        }

        // 计算最终面积
        int area = 0;
        for (int y = 0; y < yList.size() - 1; ++y) {
            int dy = yList.get(y + 1) - yList.get(y);
            for (int x = 0; x < xList.size() - 1; ++x) {
                if (grid[y].get(x)) {
                    int dx = xList.get(x + 1) - xList.get(x);
                    area += dx * dy;
                }
            }
        }
        System.out.println(area);
    }
}