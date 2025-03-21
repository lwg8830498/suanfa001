package com.het.demotest.e100.密码解密;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);  // 创建扫描器对象，读取输入
        String s = in.next();  // 读取密文字符串
        s = s + "10*";  // 在字符串末尾添加"10*"，确保最后有一个'*'，方便处理
        List<Integer> p = new ArrayList<>();  // 创建一个列表，用于记录所有'*'的位置
        p.add(-1);  // 添加-1作为起始位置前的虚拟位置

        // 遍历字符串s，记录所有'*'的位置
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '*') {
                p.add(i);
            }
        }

        StringBuilder ans = new StringBuilder();  // 创建StringBuilder对象，用于构建结果字符串
        // 遍历所有'*'的位置，进行解密处理
        for (int i = 1; i < p.size(); i++) {
            int l = p.get(i - 1) + 1, r = p.get(i) - 1;  // 计算当前'*'的左边界l和右边界r
            for (int j = l; j <= r - 2; j++) {  // 处理l到r-2之间的单字符映射
                char c = (char)(s.charAt(j) - '1' + 'a');  // 将单字符映射到对应的字母
                ans.append(c);  // 添加到结果字符串ans中
            }
            // 处理r-1和r位置的双字符映射
            int num = (s.charAt(r - 1) - '0') * 10 + s.charAt(r) - '0';  // 计算双字符对应的数字
            char c = (char)(num + 'a' - 1);  // 将数字映射到对应的字母
            ans.append(c);  // 添加到结果字符串ans中
        }

        ans.deleteCharAt(ans.length() - 1);  // 去除最后一个多余的字符
        System.out.println(ans);  // 输出解密后的明文字符串
    }
}