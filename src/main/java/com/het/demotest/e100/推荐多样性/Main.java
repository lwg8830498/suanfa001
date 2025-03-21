package com.het.demotest.e100.推荐多样性;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 读取窗口数量n和每个窗口的元素数量k
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        scanner.nextLine();  // 读取并丢弃换行符

        List<List<Integer>> lists = new ArrayList<>();  // 存储所有输入列表的集合
        List<List<Integer>> windows = new ArrayList<>();  // 存储每个窗口的元素集合
        for (int i = 0; i < n; i++) {
            windows.add(new ArrayList<>());  // 初始化每个窗口的列表
        }

        // 读取输入的多个列表

        while (scanner.hasNextLine()) {

            String line = scanner.nextLine();
            if (line.isEmpty()) break;  // 如果读取到空行，结束输入
            Scanner lineScanner = new Scanner(line);
            List<Integer> currentList = new ArrayList<>();
            while (lineScanner.hasNextInt()) {
                currentList.add(lineScanner.nextInt());  // 添加元素到当前列表
            }
            lists.add(currentList);  // 将当前列表添加到总列表中
        }

        int[] idx = new int[lists.size()];  // 存储每个列表的当前索引
        int nowList = 0;  // 当前处理的列表索引

        // 元素分配逻辑
        for (int i = 0; i < k; i++) {
            int st = nowList;
            for (int j = 0; j < n; j++) {
                while (idx[nowList] >= lists.get(nowList).size()) {
                    nowList = (nowList + 1) % lists.size();  // 如果当前列表已空，转到下一个列表
                }
                windows.get(j).add(lists.get(nowList).get(idx[nowList]));  // 向当前窗口添加元素
                idx[nowList]++;  // 更新当前列表的索引
            }
            if(i == k-1) {
                break;
            }
            if(st == nowList) {
                int nex = (nowList + 1) % lists.size();
                while(idx[nex] >= lists.get(nex).size()) {
                    nex = (nex + 1) % lists.size();
                }
                nowList = nex;
            }
        }

        // 输出结果
        for (List<Integer> window : windows) {
            for (Integer x : window) {
                System.out.print(x + " ");
            }
        }
    }
}