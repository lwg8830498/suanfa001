package com.het.demotest.e100.数组组成的最小数;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        scanner.close();

        // 将一个字符串分成若干个','隔开的字符串
        List<String> v = new ArrayList<>();
        int j = 0;// 用于标记子串开始的位置
        for (int i = 0; i < s.length(); i++) {//遍历字符串 当发现逗号的时候，子串就是[j,i-1]，那么可以通过调用s.substr()函数直接获取子串，同时更新下标j是下一个子串开始的位置，即i+1
            if (s.charAt(i) == ',') {
                v.add(s.substring(j, i));
                j = i + 1;
            }
        }// 最后别忘了末尾的字符串
        v.add(s.substring(j));

        // 对这个字符串数组进行自定义排序
        Collections.sort(v, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {//自定义比较函数，用于字符串数组排序，比较规则就是先看长度，长度不一样的话短的在前，否则再看字典序，字典序小的在前面
                if (a.length() != b.length()) return Integer.compare(a.length(), b.length());// 长度一样，短的在前
                for (int i = 0; i < a.length(); i++) {// 否则看字典序，从前往后找第一个不一样的看看字符大小，小的在前
                    if (a.charAt(i) != b.charAt(i)) return Character.compare(a.charAt(i), b.charAt(i));
                }
                return 0;
            }
        });
        // 然后选出前三个，如果不够三个就取所有，所以可以写成数组长度和3的较小值
        int len = Math.min(3, v.size());
        // 取出来之后要将这几个数重新组合成最小的数字，那么就可以通过冒泡的思想将字典序小的放前面
        for (int i = len-1; i >= 0;i --) {
            for (j = 0; j < i; j++) {// v[j] + v[j + 1]是原来的组合，v[j+1] + v[j]是交换的组合，取字典序小的即可
                if ((v.get(j + 1) + v.get(j)).compareTo(v.get(j) + v.get(j + 1)) < 0) {
                    Collections.swap(v, j, j + 1);
                }
            }
        }
        // 最后将所选的字符串全部拼接输出
        String ans = "";
        for (int i = 0; i < len; i++) {
            ans+=v.get(i);
        }
        System.out.println(ans);
    }
}