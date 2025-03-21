package com.het.demotest.e100.寻找链表的中间节点;

import java.util.*;

class ListNode {
    int data;
    String next;

    public ListNode(int data, String next) {
        this.data = data;
        this.next = next;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取首结点地址和结点个数
        String headAddress = scanner.next();
        int N = scanner.nextInt();

        // 使用一个哈希表来存储每个结点的信息，地址为键
        Map<String, ListNode> nodeMap = new HashMap<>();

        // 读取每个结点的信息
        for (int i = 0; i < N; i++) {
            String address = scanner.next();
            int data = scanner.nextInt();
            String next = scanner.next();
            nodeMap.put(address, new ListNode(data, next));
        }

        // 使用一个列表来保存链表中的所有结点地址
        List<String> list = new ArrayList<>();

        // 从首结点开始遍历链表，直到遇到 "-1"（即 NULL）
        String currentAddress = headAddress;
        while (!currentAddress.equals("-1")) {
            list.add(currentAddress);
            currentAddress = nodeMap.get(currentAddress).next;
        }

        // 找到中间结点：如果有两个中间结点，选择第二个
        int middleIndex = list.size() / 2;

        // 输出中间结点的数据
        System.out.println(nodeMap.get(list.get(middleIndex)).data);

        scanner.close();
    }
}