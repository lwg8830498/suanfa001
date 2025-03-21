package com.het.demotest.e100.tjsm;

import java.util.*;

public class Main {

    // 计算在给定排列下，有多少 a[i] > b[i]
    public static int countWins(List<Integer> a, List<Integer> b) {
        int wins = 0;
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i) > b.get(i)) {
                wins++;
            }
        }
        return wins;
    }

    // 产生下一个排列
    private static boolean nextPermutation(List<Integer> permutation) {
        // 寻找非递增的最后一个元素
        int i = permutation.size() - 2;
        while (i >= 0 && permutation.get(i) >= permutation.get(i + 1)) {
            i--;
        }
        if (i == -1) {
            return false;
        }

        // 寻找尾部大于permutation[i]的最小元素
        int j = permutation.size() - 1;
        while (permutation.get(j) <= permutation.get(i)) {
            j--;
        }

        // 交换这两个元素
        Collections.swap(permutation, i, j);

        // 反转从i+1到末尾的部分
        Collections.reverse(permutation.subList(i + 1, permutation.size()));
        return true;
    }

    // 主函数，计算最优排列的数量
    public static int optimalArrangements(List<Integer> a, List<Integer> b) {
        int maxWins = 0;
        int countMaxWins = 0;
        Collections.sort(a); // 对a数组进行升序排序，方便进行全排列

        do {
            // 计算当前排列的胜利次数
            int wins = countWins(a, b);
            if (wins > maxWins) {
                maxWins = wins;
                countMaxWins = 1; // 重新计数
            } else if (wins == maxWins && wins !=0) {
                countMaxWins++; // 递增计数
            }
        } while (nextPermutation(a)); // 遍历 a 的所有排列

        return countMaxWins;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();

        // 读取第一行作为数组a
        String lineA = scanner.nextLine();
        Scanner scanA = new Scanner(lineA);
        while (scanA.hasNextInt()) {
            a.add(scanA.nextInt());
        }
        scanA.close();

        // 读取第二行作为数组b
        String lineB = scanner.nextLine();
        Scanner scanB = new Scanner(lineB);
        while (scanB.hasNextInt()) {
            b.add(scanB.nextInt());
        }
        scanB.close();

        System.out.println(optimalArrangements(a, b)); // 输出最优排列数量
    }
}