package com.het.demotest.e200.文件缓存系统;

import java.util.*;
public class Main {
    // 定义内部类Node来封装文件的信息
    static class Node {
        int cnt, tim, siz;  // cnt: 访问次数, tim: 最近访问时间, siz: 文件大小
        String name;        // 文件名

        public Node(int cnt, int tim, String name, int siz) {
            this.cnt = cnt;
            this.tim = tim;
            this.siz = siz;
            this.name = name;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();  // 缓存最大大小
        int q = in.nextInt();  // 操作数
        int sum = 0;           // 当前内存使用量
        // 文件名到文件信息的映射，记录访问次数、更新时间和大小
        Map<String, List<Integer>> mp = new HashMap<>();
        // 根据访问次数和更新时间进行排序的集合
        TreeSet<Node> st = new TreeSet<>((a, b) -> {
            if (a.cnt != b.cnt)
                return a.cnt - b.cnt;  // 按访问次数排序
            return a.tim - b.tim;      // 按更新时间排序
        });

        for (int t = 1; t <= q; t++)
        {
            String op = in.next(), name = in.next();
            if (op.equals("put"))
            {
                int siz = in.nextInt();
                // 如果文件过大或已存在，则忽略此操作
                if (siz > m || mp.containsKey(name))
                    continue;
                // 空间不足时，移除最少访问和最旧的文件
                while (sum + siz > m)
                {
                    Node node = st.pollFirst();
                    sum -= node.siz;
                    mp.remove(node.name);
                }
                // 添加新文件
                List<Integer> list = new ArrayList<>(Arrays.asList(0, t, siz));
                mp.put(name, list);     // 记录文件信息
                st.add(new Node(0, t, name, siz));      // 添加到集合中
                sum += siz;     // 更新内存使用量
            }
            else if (op.equals("get"))
            {
                // 如果文件不存在，忽略此操作
                if (!mp.containsKey(name))
                    continue;
                List<Integer> list = mp.get(name);
                st.remove(new Node(list.get(0), list.get(1), name, list.get(2)));
                list.set(0, list.get(0) + 1);  // 访问次数加1
                list.set(1, t);               // 更新访问时间
                st.add(new Node(list.get(0), t, name, list.get(2)));        // 更新集合中的信息
            }
        }

        // 输出缓存中的文件名，如果没有文件，输出NONE
        if (mp.size() == 0) {
            System.out.println("NONE");
        } else
        {
            StringBuilder res = new StringBuilder();
            mp.keySet().stream().sorted().forEach(name -> res.append(name).append(","));
            res.deleteCharAt(res.length() - 1);
            System.out.println(res.toString());
        }
    }
}