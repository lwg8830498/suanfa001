package com.het.demotest.e100.数组去重和排序;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 从控制台读入一行字符串输入
        String input = scanner.nextLine();
        String[] tokens = input.split(",");

        // 解析输入字符串为浮点数
        List<Double> nums = new ArrayList<>();
        for (String token : tokens) {
            nums.add(Double.parseDouble(token));
        }

        // 用于存储每个数字的出现频率
        Map<Double, Integer> frequency = new HashMap<>();

        // 用于存储元素的首次出现顺序
        Map<Double, Integer> firstAppearance = new HashMap<>();

        // 答案数组
        List<Double> ans = new ArrayList<>();

        // 填充频率映射并保持首次出现的顺序
        for (int i = 0; i < nums.size(); i++) {
            double num = nums.get(i);
            if (!frequency.containsKey(num)) {
                firstAppearance.put(num, i);
                ans.add(num);
            }
            frequency.put(num, frequency.getOrDefault(num, 0) + 1);
        }

        // 根据每个数字的出现频率对ans数组进行排序
        // 如果频率相同，则按照首次出现的顺序排序
        Collections.sort(ans, new Comparator<Double>() {
            @Override
            public int compare(Double a, Double b) {
                if (frequency.get(a).equals(frequency.get(b))) {
                    return firstAppearance.get(a) - firstAppearance.get(b);
                }
                return frequency.get(b) - frequency.get(a);
            }
        });

        // 按照要求的格式打印排序后的数组
        for (int i = 0; i < ans.size(); i++) {
            double num = ans.get(i);
            if (num == Math.floor(num)) {
                System.out.print((int)num); // 如果数字是整数，则以整数形式打印
            } else {
                System.out.print(num); // 否则，打印为浮点数
            }
            if (i != ans.size() - 1) {
                System.out.print(",");
            }
        }
    }
}