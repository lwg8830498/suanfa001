package com.het.demotest.e100.生成哈夫曼树;

import java.util.*;  // 导入Java的工具库，用于使用集合框架

// 主类Main
public class Main {
    // 内部类Node，表示哈夫曼树的节点
    static class Node implements Comparable<Node> {
        int value, height;  // 节点的权值和高度
        Node left, right;  // 指向左右子节点的引用

        // 构造函数，初始化节点的权值
        public Node(int value, int height) {
            this.value = value;
            this.height = height;
            this.left = null;
            this.right = null;
        }

        // 实现Comparable接口的compareTo方法，用于优先队列的排序
        @Override
        public int compareTo(Node other) {
            if (this.value == other.value) {
                return this.height - other.height;      // 优先队列中，高度小的节点优先级高
            }
            return this.value - other.value;  // 根据节点的权值进行排序
        }
    }

    // 主函数
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);  // 创建扫描器对象，用于读取输入
        int n = in.nextInt();  // 读取数字数组的长度
        PriorityQueue<Node> minHeap = new PriorityQueue<>();  // 创建优先队列（最小堆），用于构建哈夫曼树
        for (int i = 0; i < n; i++) {
            minHeap.add(new Node(in.nextInt(), 0));  // 读取每个数字并创建节点，加入最小堆
        }
        while (minHeap.size() > 1) {  // 当最小堆中的节点数大于1时，继续合并
            Node a = minHeap.poll();  // 弹出最小的节点
            Node b = minHeap.poll();  // 弹出次小的节点
            Node c = new Node(a.value + b.value, a.height+1);  // 创建新节点，权值为a和b的权值之和
            c.left = a;  // 新节点的左子节点为a
            c.right = b;  // 新节点的右子节点为b
            minHeap.add(c);  // 将新创建的节点加入最小堆中
        }
        dfs(minHeap.poll());  // 从哈夫曼树的根节点开始进行中序遍历
    }

    // 深度优先搜索（DFS）函数，用于中序遍历
    static void dfs(Node node) {
        if (node.left != null && node.right != null) {
            // 确保左节点的权值不大于右节点的权值
            if (node.left.value > node.right.value) {
                Node temp = node.left;  // 交换左右子节点，以满足条件
                node.left = node.right;
                node.right = temp;
            }
        }
        if (node.left != null)  // 遍历左子树
        {
            dfs(node.left);
        }
        System.out.print(node.value + " ");  // 打印当前节点的权值
        if (node.right != null)  // 遍历右子树
        {
            dfs(node.right);
        }
    }
}