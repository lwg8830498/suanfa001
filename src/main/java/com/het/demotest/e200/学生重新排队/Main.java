package com.het.demotest.e200.学生重新排队;

import java.util.*;
import java.io.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Main {

    static List<Integer> read(BufferedReader br) throws IOException {
        String line = br.readLine();  // 读取一行数据
        if (line == null || line.isEmpty()) {
            return new ArrayList<>();  // Return an empty list if the line is null or empty
        }

        StringTokenizer st = new StringTokenizer(line);  // 将读取的行放入StringTokenizer
        List<Integer> numbers = new ArrayList<>();  // 创建一个List来存储整数

        while (st.hasMoreTokens()) {  // 使用StringTokenizer读取整数
            numbers.add(Integer.parseInt(st.nextToken()));  // 将读取的整数添加到List中
        }
        return numbers;
    }

    static void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> a = read(br);
        List<Integer> b = read(br);
        int n = a.size();

        // 预处理：记录每个数字属于哪个组
        int[] rel = new int[n + 1];
        for (int i = 0; i + 2 < n; i += 3) {
            for (int j = 0; j < 3; j++) {
                rel[b.get(i + j)] = i / 3 + 1;
            }
        }

        // 把情况2和3的先尽可能处理掉
        int ans = 0;
        boolean flag = true;
        while (flag) {
            flag = false;
            for (int i = 0; i < n - 1; i++) {
                if (rel[a.get(i)] == rel[a.get(i + 1)]) { // 如果两个紧挨在一起时一组的
                    // 并且不是情况1
                    if (i > 0 && rel[a.get(i)] == rel[a.get(i - 1)]) {
                        continue;
                    }
                    if (i + 2 < n && rel[a.get(i + 1)] == rel[a.get(i + 2)]) {
                        continue;
                    }

                    // 操作一次把落单的元素找到
                    ans++;
                    int id = -1;
                    for (int j = 0; j < i; j++) {
                        if (rel[a.get(j)] == rel[a.get(i)]) {
                            id = j;
                            break;
                        }
                    }
                    if (id == -1) {
                        for (int j = i + 2; j < n; j++) {
                            if (rel[a.get(j)] == rel[a.get(i)]) {
                                id = j;
                                break;
                            }
                        }
                        for (int j = id; j > i + 1; j--) {
                            Collections.swap(a, j, j - 1); //模拟移动
                        }
                    } else {
                        for (int j = id; j < i; j++) {
                            Collections.swap(a, j, j + 1); // 模拟移动
                        }
                    }
                    flag = true;
                }
            }
        }

        //维护一个双端队列, 用List模拟了, 因为要访问队列中间的元素
        List<Integer> dq = new ArrayList<>(Collections.nCopies(1111, 0));
        int t = 0;
        for (int i = 0; i < n; i++) {
            dq.set(t++, a.get(i));
        }

        final int tt = t;
        final List<Integer> dqq = dq;
        final int[] rell = rel;

        BiConsumer<Integer, Integer> move = (id, tar) -> {
            assert tar != -1;
            if (id < tar) {
                for (int i = id; i < tar; i++) {
                    Collections.swap(dqq, i, i + 1);
                }
            } else {
                for (int i = id; i > tar; i--) {
                    Collections.swap(dqq, i, i - 1);
                }
            }
        };

        BiFunction<Integer, Integer, Integer> hasSame = (l, r) -> {
            int tar = l;
            for (int i = l; i + 1 < r; i++) {
                if (rell[dqq.get(i)] == rell[dqq.get(i + 1)]) {
                    return i;
                }
            }
            return -1;
        };

        BiFunction<Integer, Integer, Integer> find = (l, tar) -> {
            for (int i = l; i < dqq.size(); i++) {
                if (rell[dqq.get(i)] == tar) {
                    return i;
                }
            }
            return -1;
        };

        Function<int[], Integer> dfs = new Function<int[], Integer>() {
            @Override
            public Integer apply(int[] headTail) {
                int head = headTail[0], tail = headTail[1];
                if (head == tail) {
                    return 0;// 如果队列空了,解说
                }

                int f1 = rell[dqq.get(head)], f2 = rell[dqq.get(head + 1)], f3 = rell[dqq.get(head + 2)];
                int f4 = rell[dqq.get(tail - 1)], f5 = rell[dqq.get(tail - 2)], f6 = rell[dqq.get(tail - 3)];
                if (f1 == f2 && f2 == f3) {
                    return this.apply(new int[]{head + 3, tail});
                }
                if (f1 == f4 && f4 == f5) {
                    return this.apply(new int[]{head, tail - 3});
                }

                if (f1 == f2) {
                    int id = find.apply(head + 2, f1);
                    move.accept(id, head);
                    return 1 + this.apply(new int[]{head + 3, tail});
                }

                if (f4 == f5) {
                    int id = find.apply(head, f4);
                    move.accept(id, tail - 3);
                    return 1 + this.apply(new int[]{head, tail - 3});
                }

                int id = hasSame.apply(head, tail);
                if (id != -1) {
                    move.accept(head, id);
                    return 1 + this.apply(new int[]{head, tail});
                }
                id = hasSame.apply(head, tail);
                if (id != -1) {
                    move.accept(tail - 1, id);
                    return 1 + this.apply(new int[]{head, tail});
                }

                int id1 = find.apply(head + 1, f1);
                int id2 = find.apply(id1 + 1, f1);
                List<Integer> backup = new ArrayList<>(dqq);
                move.accept(id1, head);
                move.accept(id2, head);
                int ans1 = 2 + this.apply(new int[]{head, tail});

                dqq.clear();
                dqq.addAll(backup);
                id1 = find.apply(head, f4);
                id2 = find.apply(id1 + 1, f4);
                move.accept(id2, tail - 1);
                move.accept(id1, tail - 1);
                int ans2 = 2 + this.apply(new int[]{head, tail});

                return Math.min(ans1, ans2);
            }
        };

        // 两个过程加一起就是答案
        System.out.println(ans + dfs.apply(new int[]{0, tt}));
    }

    public static void main(String[] args) throws IOException {
        solve();
    }
}