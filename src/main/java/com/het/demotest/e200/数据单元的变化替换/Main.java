package com.het.demotest.e200.数据单元的变化替换;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<Integer>[] g = new List[100]; // 邻接表，存储依赖关系
        List<Integer>[] L = new List[100]; // 存储每个单元格中'<'的位置
        List<Integer>[] R = new List[100]; // 存储每个单元格中'>'的位置
        int[] ru = new int[100]; // 入度数组，记录每个节点的依赖数
        for (int i = 0; i < 100; i++) {
            g[i] = new ArrayList<>();
            L[i] = new ArrayList<>();
            R[i] = new ArrayList<>();
        }
        String[] v = in.nextLine().split(","); // 从输入读取CSV数据并分割成单元格
        boolean f = true; // 标志位，用于检测是否有非法格式
        for (int i = 0; i < v.length; i++) { // 检查每个单元格的引用合法性
            for (int p = 0; p < v[i].length(); p++) {
                if (v[i].charAt(p) == '<') {
                    L[i].add(p);
                }
                if (v[i].charAt(p) == '>') {
                    R[i].add(p);
                }
            }
            if (L[i].size() == 0 && R[i].size() == 0) {
                continue;
            }
            if (L[i].size() != 1 || R[i].size() != 1 || L[i].get(0) + 2 != R[i].get(0)) {
                f = false; // 如果格式不正确（如引用不匹配或位置不正确），设置f为false
                break;
            }
            int fu = v[i].charAt(L[i].get(0) + 1) - 'A'; // 计算引用的单元格索引
            if (fu < 0 || fu >= v.length) {
                f = false; // 如果引用的单元格不存在，设置f为false
                break;
            }
            g[fu].add(i); // 将当前单元格添加到它引用的单元格的邻接表中
            ru[i]++; // 增加当前单元格的入度
        }
        if (!f) {
            System.out.println("-1"); // 如果有错误，输出-1
            return;
        }

        for (int i = 0; i < v.length; i++) {
            if (ru[i] == 0) {
                dfs(i, g, L, R, v); // 对所有入度为0的单元格进行深度优先搜索
            }
        }
        StringBuffer sb = new StringBuffer();
        for (String s : v) {
            sb.append(s).append(","); // 将处理后的数据重新组合成CSV格式
        }
        sb.deleteCharAt(sb.length() - 1); // 删除最后一个逗号
        System.out.println(sb.toString()); // 输出最终结果
    }

    // 深度优先搜索函数，用于递归解析单元格内容
    static void dfs(int x, List<Integer>[] g, List<Integer>[] L, List<Integer>[] R, String[] v) {
        for (int y : g[x]) {
            v[y] = v[y].substring(0, L[y].get(0)) + v[x] + v[y].substring(R[y].get(0) + 1); // 替换引用为实际内容
            dfs(y, g, L, R, v); // 继续解析其他依赖此单元格的单元格
        }
    }
}