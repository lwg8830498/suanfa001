package com.het.demotest.e200.最佳的出牌方法;

import java.util.Scanner;

public class Main {
    // num记录牌的数量，ans记录得分的最大值
    static int[] num = new int[15];
    static int ans = 0;

    public static void dfs(int x, int sum) {// x遍历牌从0到13，sum记录得分
        if (x == 14) {// 说明所有牌都遍历完了，然后ans更新最大值
            ans = Math.max(ans, sum);
            return;
        }
        // 判断能否组成顺子，就是看从当前位置开始往后数5张牌，判断这几张牌
        if (x + 4 <= 13) { // 判断往后是否有5张牌
            boolean f = true;
            for (int i = 0; i < 5; i++) {
                if (num[x + i] == 0) {
                    f = false;// 如果没牌f等于false
                }
            }
            if (f) {// f=1说明有牌，有顺子，那么这五张牌数量都减一，定义ss记录牌得分
                int ss = 0;
                for (int i = 0; i < 5; i++) {
                    num[x + i]--;
                    ss += x + i;
                }
                dfs(x, sum + ss * 2);// 还是遍历x，有可能x有剩余，得分是所有牌加起来乘2
                for (int i = 0; i < 5; i++) num[x + i]++;// 回溯
            }
        }
        // 将当前牌清空
        if (num[x] == 4) {
            dfs(x + 1, sum + x * 4 * 3);// 四张牌得分是所有牌和乘3
        } else if (num[x] == 1) {
            dfs(x + 1, sum + x);// 一张牌得分是x
        } else {
            dfs(x + 1, sum + x * num[x] * 2);// 其他牌得分是所有牌和乘2
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String a = scanner.next();
        for (int i = 0; i < a.length(); i++) {// 记录牌个数，0表示10，JQK分别表示11 12 13
            if (a.charAt(i) == '0') {
                num[10]++;
            } else if (a.charAt(i) == 'J') {
                num[11]++;
            } else if (a.charAt(i) == 'Q') {
                num[12]++;
            } else if (a.charAt(i) == 'K') {
                num[13]++;
            } else {
                num[a.charAt(i) - '0']++;
            }
        }
        dfs(0, 0);// x从1开始，sum从0开始。
        System.out.println(ans);
    }
}