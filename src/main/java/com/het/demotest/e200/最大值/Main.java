package com.het.demotest.e200.最大值;

import java.util.*;

public class Main {

    // 自定义比较规则：按字符串拼接后的顺序进行比较
    // 如果 a+b > b+a，返回 -1，使 a 排在 b 前面，否则返回 1
    private static Comparator<String> customComparator = (a, b) -> (b + a).compareTo(a + b);

    // 函数：将一组非负整数重新排列，返回最大组合数
    public static String formLargestNumber(int[] nums) {
        // 将整数数组转换为字符串数组
        String[] strNums = Arrays.stream(nums)
                .mapToObj(String::valueOf)
                .toArray(String[]::new);
        // 按自定义规则排序
        Arrays.sort(strNums, customComparator);

        // 特殊情况：如果排序后第一个数字是 "0"，说明所有数字都是 0
        if (strNums[0].equals("0")) return "0";

        // 使用 StringBuilder 拼接排序后的字符串
        return String.join("", strNums);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim();  // 读取并去除前后空格

        try {
            // 将输入的字符串分割并转换为整数数组
            int[] nums = Arrays.stream(input.split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            // 输出最大组合数
            System.out.println(formLargestNumber(nums));

        } catch (NumberFormatException e) {
            System.out.println("输入必须为非负整数。");  // 处理非数字输入
        }

        scanner.close();  // 关闭扫描器
    }
}