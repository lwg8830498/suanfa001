package com.het.demotest.e200.目录删除;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Map<Integer, List<Integer>> v = new HashMap<>();// 哈希用于存边
        for (int i = 1; i <= n; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            v.computeIfAbsent(y, k -> new ArrayList<>()).add(x); // 建边，如果哈希表没有y就新建一个新的List，否则就加入一个x，表示父节点y有一个子节点x
        }

        int m = sc.nextInt();
        Queue<Integer> q = new LinkedList<>(); // 队列用于广度优先搜索
        List<Integer> ans = new ArrayList<>(); // 用于记录存在的ID
        q.add(0); // 加入根目录

        while (!q.isEmpty()) {
            int now = q.poll(); // 队首
            if (now == m) {
                continue; // 如果是删除的ID，就不用继续往下走了
            }
            if (now != 0) {
                ans.add(now); // 根目录不用输出，其他的需要加入ans
            }
            if (v.containsKey(now)) { //遍历now的子目录并加入队列
                for (int y : v.get(now)) {
                    q.add(y);
                }
            }
        }

        Collections.sort(ans); // 将所有ID从小到大排序后输出
        for (int i : ans) {
            System.out.print(i + " ");
        }
    }
}