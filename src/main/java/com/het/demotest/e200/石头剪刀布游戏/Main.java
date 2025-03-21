package com.het.demotest.e200.石头剪刀布游戏;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String name;
        char op;

        // 创建一个包含3个列表的List，每个列表分别存储出拳形状为A、B、C的玩家ID
        List<List<String>> v = new ArrayList<>();
        for (int i=0; i<3; i++)
            v.add(new ArrayList<>());

        // 读取输入的玩家ID和出拳形状
        while (in.hasNext()) {
            name = in.next();
            op = in.next().charAt(0);
            if (op!='A' && op!='B' && op!='C')
            {
                System.out.println("NULL");
                return;
            }
            // 根据出拳形状将玩家ID放入对应的列表中
            v.get(op-'A').add(name);
        }

        // 统计有多少种不同的出拳形状
        int num=0;
        for (int i=0; i<3; i++) {
            if (v.get(i).size() > 0) {
                num++;
            }
        }

        // 如果只有两种出拳形状
        if (num==2) {
            int idx = 0;
            // 找到相对优势的出拳形状的索引
            for (int i=0; i<3; i++) {
                if (v.get(i).size()>0 && v.get((i+1)%3).size()>0) {
                    idx = i;
                }
            }
            // 将获胜的玩家ID按字典顺序排序
            Collections.sort(v.get(idx));
            // 输出获胜的玩家ID
            for (String x : v.get(idx)) {
                System.out.println(x);
            }
        } else {
            // 如果不是两种不同出拳形状，则为平局
            System.out.println("NULL");
        }
    }
}