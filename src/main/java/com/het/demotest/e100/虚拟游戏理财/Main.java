package com.het.demotest.e100.虚拟游戏理财;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取产品数、总投资额、可接受的总风险值
        int m = scanner.nextInt();
        int N = scanner.nextInt();
        int X = scanner.nextInt();

        // 读取投资回报率序列
        int[] returns = new int[m];
        for (int i = 0; i < m; i++) {
            returns[i] = scanner.nextInt();
        }

        // 读取风险值序列
        int[] risks = new int[m];
        for (int i = 0; i < m; i++) {
            risks[i] = scanner.nextInt();
        }

        // 读取最大投资额度序列
        int[] maxInvestments = new int[m];
        for (int i = 0; i < m; i++) {
            maxInvestments[i] = scanner.nextInt();
        }

        // 初始化最大回报和最优投资方案
        int maxReturn = 0;
        int[] bestInvestment = new int[m];

        // 遍历所有可能的2个产品组合
        for (int i = 0; i < m; i++) {
            for (int j = i; j < m; j++) {
                // 计算当前组合的风险值和回报
                int totalRisk = risks[i] + risks[j];
                if (totalRisk > X) {
                    continue; // 超过总风险值，跳过
                }

                // 枚举投资额分配方式
                for (int investI = 0; investI <= Math.min(maxInvestments[i], N); investI++) {
                    for (int investJ = 0; investJ <= Math.min(maxInvestments[j], N - investI); investJ++) {
                        if (investI + investJ > N) {
                            continue; // 总投资额超过N，跳过
                        }

                        // 计算总回报
                        int totalReturn = investI * returns[i] + investJ * returns[j];

                        // 更新最优方案
                        if (totalReturn > maxReturn) {
                            maxReturn = totalReturn;
                            Arrays.fill(bestInvestment, 0); // 重置投资方案
                            bestInvestment[i] = investI;
                            bestInvestment[j] = investJ;
                        }
                    }
                }
            }
        }

        // 输出最优投资方案
        for (int investment : bestInvestment) {
            System.out.print(investment + " ");
        }
    }
}