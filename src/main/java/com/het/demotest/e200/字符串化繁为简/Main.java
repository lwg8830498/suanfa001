package com.het.demotest.e200.字符串化繁为简;

import java.util.*;

public class Main {

    // 小写字母表和大写字母表
    static List<Character> lc = new ArrayList<>();
    static List<Character> uc = new ArrayList<>();

    // 判断是否可以合并
    public static boolean canCombine(Set<Character> st1, Set<Character> st2) {
        for (int i = 0; i < 26; i++) {
            if ((st1.contains(lc.get(i)) || st1.contains(uc.get(i))) &&
                    (st2.contains(lc.get(i)) || st2.contains(uc.get(i)))) {
                return true;
            }
        }
        return false;
    }

    // 合并等效字符容器
    public static boolean loop(List<Set<Character>> eqs) {
        int n = eqs.size();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (canCombine(eqs.get(i), eqs.get(j))) {
                    // 合并两个等效字符容器
                    eqs.get(i).addAll(eqs.get(j));
                    eqs.remove(j);
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        // 初始化小写字母表和大写字母表
        for (char c = 'a'; c <= 'z'; c++) {
            lc.add(c);
            uc.add((char) (c - 32));
        }

        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();  // 输入获取

        List<Character> cArr = new ArrayList<>();  // 主体字符容器
        List<Set<Character>> eqs = new ArrayList<>();  // 等效字符容器的集合

        boolean isOpen = false;  // 标志位，表示是否遇到'('

        // 从输入字符串中解析主体字符和等效字符
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                isOpen = true;
                eqs.add(new HashSet<>());  // 新建等效字符容器
            } else if (c == ')') {
                isOpen = false;
                if (eqs.get(eqs.size() - 1).isEmpty()) {
                    eqs.remove(eqs.size() - 1);  // 如果等效字符容器为空，则删除
                }
            } else {
                if (!isOpen) {
                    cArr.add(c);  // 主体字符
                } else {
                    eqs.get(eqs.size() - 1).add(c);  // 等效字符
                }
            }
        }

        // 暴力的对等效字符容器进行合并
        while (loop(eqs));

        // 替换主体字符容器中的字符
        for (Set<Character> eq : eqs) {
            List<Character> tmp = new ArrayList<>(eq);
            Collections.sort(tmp);  // 按字典序排序
            char t = tmp.get(0);  // 获取字典序最小的字符
            for (int i = 0; i < cArr.size(); i++) {
                if (eq.contains(cArr.get(i))) {
                    cArr.set(i, t);  // 替换字符
                }
            }
        }

        // 拼接结果字符串
        StringBuilder ans = new StringBuilder();
        for (char c : cArr) {
            ans.append(c);
        }

        // 如果简化后的字符串为空，输出"0"
        System.out.println(ans.length() == 0 ? "0" : ans.toString());
    }
}