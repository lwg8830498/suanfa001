package com.het.demotest.e100.剩余银饰的重量;

import java.util.*;
public class Main {
    public static void main(String[] args) {
        // 使用Scanner类从标准输入读取数据
        Scanner in = new Scanner(System.in);
        // 读取银饰的个数
        int n = in.nextInt();
        // 创建一个大根堆，来自动排序银饰的重量
        PriorityQueue<Integer> q = new PriorityQueue<>(Comparator.reverseOrder());
        // 读取每块银饰的重量，并添加到优先队列中
        for (int i=0; i<n; i++) {
            int x = in.nextInt();
            q.add(x);
        }
        // 当堆中的元素大于或等于3时，进行处理
        while (q.size() >= 3) {
            // 依次从堆中取出三个最大的银饰
            int z = q.poll();
            int y = q.poll();
            int x = q.poll();
            // 计算熔化后可能剩余的银饰重量
            int res = f(x, y, z);
            // 如果有剩余重量，将其重新加入优先队列
            if (res != 0)
                q.add(res);
        }
        // 最后，根据剩余的银饰块数来确定输出结果
        int ans = q.size() != 0 ? q.peek() : 0;
        // 输出结果
        System.out.println(ans);
    }

    // 定义一个方法计算三块银饰熔化后的剩余重量
    static int f(int x, int y, int z) {
        int res = 0;
        // 如果y-x>0 且 z-y>0，计算这两个差值的差
        if (y - x > 0 && z - y > 0)
            res = Math.abs(z - y - y + x);
            // 如果只有y-x>0，直接返回y-x
        else if (y - x > 0)
            res = y - x;
            // 如果只有z-y>0，直接返回z-y
        else if (z - y > 0)
            res = z - y;
        // 返回计算得到的剩余重量
        return res;
    }
}