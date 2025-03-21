package com.het.demotest.e200.狼羊过河;

import java.util.Scanner;

public class Main  {
    // 定义一个全局变量ret，用于存储最少的往返次数
    static int ret = 1000000000;

    // 定义dfs函数，用于递归地解决羊和狼过河问题
    public static void dfs(int sheep, int wolf, int boat, int oppo_sheap, int oppo_wolf, int count) {
        // 终止条件：如果所有的羊和狼都成功过河，更新最小次数
        if (sheep == 0 && wolf == 0) {
            ret = Math.min(ret, count);
            return;
        }
        // 如果当前岸上的羊和狼的数量小于或等于船的容量，更新答案
        if (sheep + wolf <= boat) {
            ret = Math.min(ret, count + 1);
            return;
        }
        // 尝试所有可能的羊和狼的组合
        for (int i = 0; i <= Math.min(boat, sheep); i++) {
            for (int j = 0; j <= Math.min(boat, wolf); j++) {
                // 空运，即没有羊或狼过河， 会导致死循环，因此continue
                if (i + j == 0) continue;
                // 超载，即羊和狼的总数超过船的容量，后续的 j 同样不合法， break 
                if (i + j > boat) break;
                // 本岸羊 <= 本岸狼，说明狼运少了，需要多运一些狼
                if (sheep - i <= wolf - j && sheep - i != 0) continue;
                // 对岸羊 <= 对岸狼，说明狼运多了，后面的 j 会导致狼更多，直接break
                if (oppo_sheap + i <= oppo_wolf + j && oppo_sheap + i != 0) break;
                // 对岸没羊，但是对岸狼已经超过船载量，即下次即使整船都运羊，也无法保证对岸羊 > 对岸狼， break
                if (oppo_sheap + i == 0 && oppo_wolf + j >= boat) break;
                // 递归调用dfs函数，尝试过河
                dfs(sheep - i, wolf - j, boat, oppo_sheap + i, oppo_wolf + j, count + 1);
            }
        }
    }

    // 定义result函数，用于初始化ret变量并调用dfs函数
    public static int result(int sheep, int wolf, int boat) {
        ret = 1000000000; // 初始化ret为一个很大的数
        dfs(sheep, wolf, boat, 0, 0, 0); // 从0开始递归
        // 如果ret仍然是初始值，说明没有找到解决方案
        return ret == 1000000000 ? 0 : ret;
    }

    // 主函数，读取输入并输出结果
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt(); // 读取羊的数量
        int n = scanner.nextInt(); // 读取狼的数量
        int x = scanner.nextInt(); // 读取船的容量
        System.out.println(result(m, n, x));
        scanner.close();
    }
}