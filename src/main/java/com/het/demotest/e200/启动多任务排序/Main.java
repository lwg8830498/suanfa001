package com.het.demotest.e200.启动多任务排序;

import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 使用TreeMap保存有向图，键为任务名称，值为该任务所依赖的任务列表
        TreeMap<String, ArrayList<String>> mp = new TreeMap<String, ArrayList<String>>();
        // 使用TreeMap保存每个任务的入度，即依赖该任务的其他任务数量
        TreeMap<String, Integer> ru = new TreeMap<String, Integer>();

        // 循环读取输入直到没有更多输入
        while (in.hasNext()) {
            String s = in.next();
            int p = -1;
            // 找到 "->" 的位置，用来区分任务与依赖
            for (int i = 1; i < s.length(); i++) {
                if (s.charAt(i-1) == '-' && s.charAt(i) == '>') {
                    p = i;
                    break;
                }
            }
            // 获取依赖关系中的两个任务，v 是被依赖的任务，u 是依赖v的任务
            String v = s.substring(0, p - 1), u = s.substring(p + 1);
            // 如果u还未出现过，初始化其入度为0
            ru.put(u, ru.getOrDefault(u, 0));
            // v的入度增加1，因为u依赖v
            ru.put(v, ru.getOrDefault(v, 0) + 1);
            // 确保u和v都在mp中有对应的列表
            if (!mp.containsKey(u)) {
                mp.put(u, new ArrayList<String>());
            }
            if (!mp.containsKey(v)) {
                mp.put(v, new ArrayList<String>());
            }
            // 在u的列表中添加v，表示u依赖v
            mp.get(u).add(v);
        }

        // 使用TreeSet存储当前没有依赖的任务（即入度为0的任务）
        TreeSet<String> last = new TreeSet<String>();
        // 列表ans用来保存任务的执行顺序
        List<String> ans = new ArrayList<String>();
        // 找出所有入度为0的任务，添加到last中
        for (Map.Entry<String, Integer> entry : ru.entrySet()) {
            String u = entry.getKey();
            int cnt = entry.getValue();
            if (cnt == 0) {
                last.add(u);
            }
        }
        // 当还有可以执行的任务时进行循环
        while (last.size() > 0) {
            // 将可执行的任务添加到ans中
            for (String u : last) {
                ans.add(u);
            }
            // 准备下一轮可以执行的任务
            TreeSet<String> nxt = new TreeSet<String>();
            for (String u : last) {
                // 遍历u依赖的任务v，减少v的入度
                for (String v : mp.get(u)) {
                    ru.put(v, ru.get(v) - 1);
                    // 如果v的入度为0，加入到下一轮可以执行的任务中
                    if (ru.get(v) == 0) {
                        nxt.add(v);
                    }
                }
            }
            // 更新last为下一轮可以执行的任务
            last = new TreeSet<String>(nxt);
        }
        // 输出所有任务的执行顺序
        for (String u : ans) {
            System.out.print(u + " ");
        }
    }
}