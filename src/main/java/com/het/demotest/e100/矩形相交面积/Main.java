package com.het.demotest.e100.矩形相交面积;

import java.util.Scanner;

class Rectangle {
    int x1, y1, x2, y2; // x1, y1 表示左上角，x2, y2 表示右下角

    Rectangle(int x, int y, int w, int h) {
        this.x1 = x;
        this.y1 = y;
        this.x2 = x + w;
        this.y2 = y - h;
    }
}

public class Main {

    // 计算两个矩形的相交区域面积
    public static int intersectionArea(Rectangle r1, Rectangle r2) {
        int intersect_x1 = Math.max(r1.x1, r2.x1);
        int intersect_y1 = Math.min(r1.y1, r2.y1);
        int intersect_x2 = Math.min(r1.x2, r2.x2);
        int intersect_y2 = Math.max(r1.y2, r2.y2);

        // 判断是否有交集
        if (intersect_x1 < intersect_x2 && intersect_y1 > intersect_y2) {
            return (intersect_x2 - intersect_x1) * (intersect_y1 - intersect_y2);
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 输入三个矩形的坐标和宽高
        Rectangle r1 = new Rectangle(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt());
        Rectangle r2 = new Rectangle(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt());
        Rectangle r3 = new Rectangle(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt());

        // 计算r1和r2的交集
        int r12_x1 = Math.max(r1.x1, r2.x1);
        int r12_y1 = Math.min(r1.y1, r2.y1);
        int r12_x2 = Math.min(r1.x2, r2.x2);
        int r12_y2 = Math.max(r1.y2, r2.y2);

        // 如果r1和r2没有交集，直接输出0
        if (r12_x1 >= r12_x2 || r12_y1 <= r12_y2) {
            System.out.println(0);
            return;
        }

        // 计算r12和r3的交集面积
        int intersect_x1 = Math.max(r12_x1, r3.x1);
        int intersect_y1 = Math.min(r12_y1, r3.y1);
        int intersect_x2 = Math.min(r12_x2, r3.x2);
        int intersect_y2 = Math.max(r12_y2, r3.y2);

        // 判断是否有三者的交集，并计算面积
        if (intersect_x1 < intersect_x2 && intersect_y1 > intersect_y2) {
            int area = (intersect_x2 - intersect_x1) * (intersect_y1 - intersect_y2);
            System.out.println(area);
        } else {
            System.out.println(0);
        }

        sc.close();
    }
}