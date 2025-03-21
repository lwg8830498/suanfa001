package com.het.demotest.e100.计算最接近的数;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        scanner.close();

        List<Integer> a = new ArrayList<>();
        int len = s.length() - 1;
        while (len >= 0 && s.charAt(len) != ']') {
            len--;
        }

        int k = Integer.parseInt(s.substring(len + 2));
        int j = 1;
        for (int i = 1; i < len; i++) {
            if (s.charAt(i) == ',') {
                a.add(Integer.parseInt(s.substring(j, i)));
                j = i + 1;
            }
        }
        a.add(Integer.parseInt(s.substring(j,len)));
        int n = a.size();
        // 定义pre用于求前缀和pre[i]表示a[1]+a[2]+a[3]+...+a[i], 那么pre[i]就等于pre[i-1]+a[i]
        int[] pre = new int[n];
        pre[0] = a.get(0); // 初始化第一个值
        for (int i = 1; i < n; i++) {
            pre[i] = pre[i - 1] + a.get(i);
        }

        // 定义tmp用于求数组的中位数，排序后中位数就是tmp[n/2]，n表示数组大小
        List<Integer> tmp = new ArrayList<>(a);
        Collections.sort(tmp); // 排序
        int res = tmp.get(n / 2);// res就表示中位数了

        int ans = -1, Min = Integer.MAX_VALUE;// ans用于记录满足条件的下标，Min用于记录表达式与中位数的最小差值
        for (int i = n - k; i >= 0; i--) {
            int d = Math.abs(a.get(i) - (pre[i + k - 1] - pre[i]) - res); // abs用于求绝对值，左边部分就是表达式a[i]-a[i-1]-...-a[i+k-1]转换的式子，右边部分是中位数，差的绝对值越小就越接近
            if (Min > d) {// 找到差值小的就更新最小值还有记录下标
                Min = d;
                ans = i;
            }
        }
        System.out.println(ans);// 最后输出下标
    }
}