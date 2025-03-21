package com.het.demotest.e200.组装最大可靠性设备;

import java.util.*;

public class Main {

    // 判断能否在预算内达到最低可靠性
    private static boolean canAchieveReliability(int minReliability, int budget, List<List<Component>> components) {
        int totalCost = 0;
        for (List<Component> componentList : components) {
            int minCost = Integer.MAX_VALUE;
            for (Component component : componentList) {
                if (component.reliability >= minReliability) {
                    minCost = Math.min(minCost, component.price);
                }
            }
            if (minCost == Integer.MAX_VALUE) {
                return false; // 无法达到该类型的最低可靠性
            }
            totalCost += minCost;
            if (totalCost > budget) {
                return false; // 超出预算
            }
        }
        return totalCost <= budget;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int budget = scanner.nextInt();
        int numTypes = scanner.nextInt();
        int totalComponents = scanner.nextInt();

        // 如果没有类型或没有元件，直接输出 -1
        if (numTypes == 0 || totalComponents == 0) {
            System.out.println(-1);
            return;
        }

        List<List<Component>> components = new ArrayList<>();
        for (int i = 0; i < numTypes; i++) {
            components.add(new ArrayList<>());
        }

        for (int i = 0; i < totalComponents; i++) {
            int type = scanner.nextInt();
            int reliability = scanner.nextInt();
            int price = scanner.nextInt();
            components.get(type).add(new Component(reliability, price));
        }

        // 如果有任何类型没有可用的元件，无法组装设备
        for (List<Component> componentList : components) {
            if (componentList.isEmpty()) {
                System.out.println(-1);
                return;
            }
        }

        // 二分查找最大可实现的最低可靠性
        int left = 0, right = 100000, result = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (canAchieveReliability(mid, budget, components)) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(result);
    }

    // 组件类，包含可靠性和价格
    static class Component {
        int reliability;
        int price;

        Component(int reliability, int price) {
            this.reliability = reliability;
            this.price = price;
        }
    }
}