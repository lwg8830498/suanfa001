package com.het.demotest.e200.书籍叠放;

import java.util.*;

public class Main {
    // 比较函数：首先按长度从小到大排，再按宽度从大到小排
    public static Comparator<int[]> compare = new Comparator<int[]>() {
        @Override
        public int compare(int[] a, int[] b) {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            }
            return a[0] - b[0];
        }
    };

    public static int maxStackableBooks(List<int[]> books) {
        // 对书籍进行排序
        Collections.sort(books, compare);

        int n = books.size();
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int maxStack = 1;

        // 动态规划寻找最多叠放数量，要求长和宽都严格大于
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (books.get(i)[0] > books.get(j)[0] && books.get(i)[1] > books.get(j)[1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxStack = Math.max(maxStack, dp[i]);
        }

        return maxStack;
    }

    public static void main(String[] args) {
        // 输入
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        // 去掉外层的 '[' 和 ']'
        input = input.trim();
        if (input.startsWith("[") && input.endsWith("]")) {
            input = input.substring(1, input.length() - 1);
        }

        List<int[]> books = new ArrayList<>();
        if (!input.isEmpty()) {
            // 分割每一组 [l, w]
            String[] pairs = input.split("\\],\\s*\\[");

            // 处理第一个和最后一个可能存在的 '[' 和 ']'
            pairs[0] = pairs[0].replaceFirst("^\\[", "");
            pairs[pairs.length - 1] = pairs[pairs.length - 1].replaceFirst("]$", "");

            for (String pair : pairs) {
                String[] nums = pair.split(",\\s*");
                int l = Integer.parseInt(nums[0]);
                int w = Integer.parseInt(nums[1]);
                books.add(new int[]{l, w});
            }
        }

        // 输出
        System.out.println(maxStackableBooks(books));
    }
}