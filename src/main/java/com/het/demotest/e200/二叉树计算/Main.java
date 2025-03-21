package com.het.demotest.e200.二叉树计算;

import java.util.*;
import java.io.*;

// 定义二叉树的节点结构
class Node {
    Node left = null;  // 左子节点，默认为空
    Node right = null; // 右子节点，默认为空
    int val;           // 节点存储的整数值

    // 构造函数，初始化节点值
    public Node(int value) {
        this.val = value;
    }
}

public class Main {
    // 静态变量，存储前序遍历和中序遍历的结果
    static List<Integer> preorder = new ArrayList<>();
    static List<Integer> inorder = new ArrayList<>();
    // 存储中序遍历中每个值的索引列表
    static Map<Integer, List<Integer>> inMap = new HashMap<>();

    // 递归构建二叉树的函数
    public static Node build(int l, int r, int preIndex) {
        if (l > r) { // 如果左边界超过右边界，返回空指针
            return null;
        }
        if(l == r) { // 如果左右边界相等，但是不匹配
            if (!Objects.equals(preorder.get(preIndex), inorder.get(l))) {
                return null;
            }
        }
        Node root = new Node(preorder.get(preIndex)); // 创建新节点
        Boolean match = false;
        for (int idx : inMap.get(preorder.get(preIndex))) { // 遍历当前节点值在中序遍历中的所有索引
            if (idx < l || idx > r) continue; // 忽略不在当前分割范围内的索引
            root.left = build(l, idx - 1, preIndex + 1); // 递归构建左子树
            root.right = build(idx + 1, r, preIndex + idx - l + 1); // 递归构建右子树
            if (l <= idx - 1 && root.left == null) continue; // 如果左子树构建失败，继续尝试其他索引
            if (idx + 1 <= r && root.right == null) continue; // 如果右子树构建失败，继续尝试其他索引
            match =true;
            break; // 成功构建左右子树后退出循环
        }
        if(!match) {
            return null; // 如果没有找到有效的构建，返回空指针
        }
        return root; // 返回根节点
    }

    // 修改树中节点值的函数
    public static int modifyTree(Node root) {
        if (root == null) {
            return 0; // 如果节点为空，返回0
        }
        int cur_val = root.val; // 记录当前节点的值
        int l_val = modifyTree(root.left);  // 递归处理左子树，返回左子树值的总和
        int r_val = modifyTree(root.right); // 递归处理右子树，返回右子树值的总和
        root.val = l_val + r_val; // 更新当前节点的值为左右子树值之和
        return l_val + r_val + cur_val; // 返回当前子树所有节点值的总和
    }

    // 中序遍历输出二叉树的函数
    public static void printInorder(Node root) {
        if (root == null) {
            return; // 如果节点为空，直接返回
        }
        printInorder(root.left);  // 递归遍历左子树
        System.out.print(root.val + " ");  // 输出当前节点值
        printInorder(root.right); // 递归遍历右子树
    }

    // 主函数
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 创建输入流读取输入
        String line;

        line = br.readLine(); // 读取一行数据，表示中序遍历
        String[] values = line.split(" "); // 分割字符串
        for (String value : values) {
            int val = Integer.parseInt(value); // 转换为整数
            inorder.add(val); // 添加到中序遍历结果列表
            inMap.putIfAbsent(val, new ArrayList<>()); // 如果没有该值的映射，添加新的列表
            inMap.get(val).add(inorder.size() - 1); // 添加索引到对应的列表
        }

        line = br.readLine(); // 读取一行数据，表示前序遍历
        values = line.split(" ");
        for (String value : values) {
            preorder.add(Integer.parseInt(value)); // 添加到前序遍历结果列表
        }

        Node root = build(0, inorder.size() - 1, 0); // 构建二叉树
        modifyTree(root); // 修改二叉树的节点值
        printInorder(root); // 输出修改后的中序遍历结果
    }
}