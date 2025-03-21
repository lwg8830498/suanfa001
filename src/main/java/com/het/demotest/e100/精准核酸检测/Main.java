package com.het.demotest.e100.精准核酸检测;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = Integer.parseInt(scanner.nextLine().trim()); // 读取并解析总人数

        String[] infectedInput = scanner.nextLine().split(",");
        Set<Integer> infected = new HashSet<>(); // 使用 Set 存储确诊病例编号，避免重复
        for (String s : infectedInput) {
            infected.add(Integer.parseInt(s.trim())); // 转换并添加确诊病例编号
        }

        int[][] contacts = new int[N][N]; // 接触矩阵
        for (int i = 0; i < N; i++) {
            String[] line = scanner.nextLine().split(","); // 分割每行的输入
            for (int j = 0; j < N; j++) {
                contacts[i][j] = Integer.parseInt(line[j].trim()); // 将输入填入接触矩阵
            }
        }
        scanner.close();

        Set<Integer> toTest = new HashSet<>(); // 需要进行核酸检测的人员集合
        boolean[] visited = new boolean[N]; // 访问标记数组

        // 初始化访问队列，使用 LinkedList 实现 Queue 接口
        Queue<Integer> queue = new LinkedList<>();
        for (int index : infected) {
            visited[index] = true; // 标记确诊病例为已访问
        }

        // 遍历确诊病例，将与之直接接触的未访问个体加入队列
        for (int index : infected) {
            for (int j = 0; j < N; j++) {
                if (contacts[index][j] == 1 && !visited[j]) {
                    queue.add(j);
                    visited[j] = true;
                    toTest.add(j); // 记录需要测试的人员
                }
            }
        }

        // 广度优先搜索，探索所有通过接触可传播到的个体
        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int j = 0; j < N; j++) {
                if (contacts[current][j] == 1 && !visited[j]) {
                    queue.add(j);
                    visited[j] = true;
                    toTest.add(j); // 记录需要测试的人员
                }
            }
        }

        // 输出需要进行核酸检测的人数
        System.out.println(toTest.size());
    }
}