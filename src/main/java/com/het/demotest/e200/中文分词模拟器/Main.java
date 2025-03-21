package com.het.demotest.e200.中文分词模拟器;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 读取第一行输入的字符串
        String s = in.nextLine();
        // 读取第二行输入并分割为单词数组
        String[] slis = in.nextLine().split(",");
        HashSet<String> dic = new HashSet<>();
        int top = 0;
        // 将所有词汇添加到词库集合中，并记录最长词的长度
        for (String x : slis) {
            dic.add(x);
            top = Math.max(top, x.length());
        }

        StringBuffer ans = new StringBuffer();
        // 遍历字符串中的每一个字符
        for (int l = 0; l < s.length(); l++) {
            // 忽略标点符号
            if (s.charAt(l) < 'a' || s.charAt(l) > 'z') {
                continue;
            }
            int p = l;
            StringBuffer tmp = new StringBuffer();
            tmp.append(s.charAt(l));
            // 尝试匹配最长的词
            for (int r = l + 1; r - l + 1 <= top && r < s.length(); r++) {
                tmp.append(s.charAt(r));
                if (dic.contains(tmp.toString())) {
                    p = r;
                }
            }
            tmp.setLength(p - l + 1);
            dic.remove(tmp.toString());
            ans.append(tmp).append(",");
            l = p;
        }
        // 删除最后一个逗号
        ans.deleteCharAt(ans.length() - 1);
        System.out.println(ans);
    }
}