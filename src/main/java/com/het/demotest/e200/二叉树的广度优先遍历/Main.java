package com.het.demotest.e200.二叉树的广度优先遍历;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 定义二叉树节点类
class Node {
    char val;          // 节点存储的值
    Node left = null;  // 指向左子节点的指针
    Node right = null; // 指向右子节点的指针

    Node(char val) {
        this.val = val;
    }
}

public class Main {
    static String postOrder, inOrder;
    static HashMap<Character, Integer> inMap = new HashMap<>();

    // 构建二叉树的递归函数
    static Node buildTree(int inStart, int inEnd, int postStart, int postEnd) {
        // 递归终止条件，当中序遍历的起始索引大于终止索引时，返回null
        if (inStart > inEnd) {
            return null;
        }

        // 创建新的根节点，其值为后序遍历的最后一个元素
        Node root = new Node(postOrder.charAt(postEnd));

        // 当中序遍历只有一个元素时，返回当前节点
        if (inStart == inEnd) {
            return root;
        }

        // 在中序遍历中找到根节点的索引
        int inRootIndex = inMap.get(root.val);
        // 计算左子树的大小
        int leftTreeSize = inRootIndex - inStart;

        // 递归构建左子树
        root.left = buildTree(inStart, inRootIndex - 1, postStart, postStart + leftTreeSize - 1);
        // 递归构建右子树
        root.right = buildTree(inRootIndex + 1, inEnd, postStart + leftTreeSize, postEnd - 1);

        return root;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 从标准输入读取后序遍历和中序遍历的字符串
        postOrder = scanner.next();
        inOrder = scanner.next();
        int n = postOrder.length();

        // 将中序遍历的每个字符及其索引存入哈希表
        for (int i = 0; i < n; i++) {
            inMap.put(inOrder.charAt(i), i);
        }

        // 调用buildTree函数构建二叉树，初始索引为字符串的起始和终止位置
        Node root = buildTree(0, n - 1, 0, n - 1);

        // 使用队列进行层序遍历
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            Node current = q.poll();
            System.out.print(current.val);  // 输出当前节点的值
            if (current.left != null) {
                q.add(current.left);  // 如果存在左子节点，加入队列
            }
            if (current.right != null) {
                q.add(current.right); // 如果存在右子节点，加入队列
            }
        }
        scanner.close();
    }
}