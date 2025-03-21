package com.het.demotest.e200.字符串拼接;

import java.util.*;

// 主类
public class Main {
    // 主函数
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();  // 读入字符列表
        int n = in.nextInt();  // 读入目标字符串长度
        if (s.length() > 30 || n > 5 || n <= 0) {  // 检查输入的合法性
            System.out.println(0);  // 输出0
            return;  // 退出程序
        }
        for (char c : s.toCharArray()) {  // 遍历字符列表
            if (c < 'a' || c > 'z') {  // 如果字符不是小写字母
                System.out.println(0);  // 输出0
                return;  // 退出程序
            }
        }
        boolean[] vis = new boolean[100];  // 访问标记数组，用于记录字符是否已被使用

        int ans = dfs(new StringBuffer(), s, vis, n);  // 调用深度优先搜索函数计算结果
        System.out.println(ans);  // 输出结果
    }

    // 深度优先搜索函数，用于生成合法字符串并计算数量
    static int dfs(StringBuffer sb, String s, boolean[] vis, int n) {
        // 当当前字符串长度等于目标长度时，返回1，表示找到一个合法的组合
        if (sb.length() == n) {
            return 1;
        }

        int res = 0;  // 用于统计符合条件的字符串数量
        Set<Character> used = new HashSet<>();  // 用于记录本次递归过程中已尝试的字符，防止同一层递归中重复使用

        // 遍历所有字符
        for (int i = 0; i < s.length(); i++) {
            if (vis[i]) continue;  // 如果该字符已经使用过，则跳过
            if (sb.length() > 0 && s.charAt(i) == sb.charAt(sb.length() - 1)) continue;  // 如果该字符与结果字符串的最后一个字符相同，则跳过
            if (used.contains(s.charAt(i))) continue;  // 如果该字符在本层递归已被尝试过，跳过

            vis[i] = true;  // 标记当前字符为已使用
            sb.append(s.charAt(i));  // 将字符添加到结果字符串
            used.add(s.charAt(i));  // 记录字符已被使用

            res += dfs(sb, s, vis, n);  // 递归调用，继续探索下一个字符，累加合法的字符串数量

            // 回溯，撤销当前字符的使用状态
            vis[i] = false;
            sb.deleteCharAt(sb.length() - 1);
        }

        return res;  // 返回找到的合法字符串数量
    }
}