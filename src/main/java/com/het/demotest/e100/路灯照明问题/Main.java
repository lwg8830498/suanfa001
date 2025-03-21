package com.het.demotest.e100.路灯照明问题;

import java.util.*;

class Node implements Comparable<Node> { //定义可以重载小于运算符的结构体，表示一条线段，x，y分别表示线段的左右端点
    int x, y;

    Node(int x, int y) { // 初始化这个结构体
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Node a) { // 重载小于运算符 先看x是否相等，如果不相等返回前者的x小的，不然返回前者的y小的
        if (this.x != a.x) {
            return Integer.compare(this.x, a.x);
        }
        return Integer.compare(this.y, a.y);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Node> v = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int x = scanner.nextInt();
            v.add(new Node(i * 100 - x, i * 100 + x)); // i*100-x,i*100+x表示该路灯的照明范围，i*100表示路灯的位置，x表示照明路径
        }

        Collections.sort(v); // 对这些线段进行排序
        int ans = 0, mx = v.get(0).x; // ans记录答案，mx记录线段右端点最大值

        for (int i = 0; i < n; i++) {
            if (mx < v.get(i).x) // mx跟当前线段左端点作比较，如果mx小，说明中间的v[i].x-mx这段是未照明的，需要ans累加
                ans += v.get(i).x - mx;
            mx = Math.max(mx, v.get(i).y); //mx更新线段右端点最大值
        }

        System.out.println(ans);
        scanner.close();
    }
}