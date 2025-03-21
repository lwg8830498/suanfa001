package com.het.demotest.e100.计算三叉搜索树高度;

import java.util.Scanner;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode center;

    public TreeNode(int val) {
        this.val = val;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int[] nums = new int[N];

        for (int i = 0; i < N; i++) {
            nums[i] = scanner.nextInt();
        }

        TreeNode root = new TreeNode(nums[0]);
        int maxHeight = 1;

        for (int i = 1; i < N; i++) {
            int height = insertIntoTree(root, nums[i]);
            maxHeight = Math.max(maxHeight, height);
        }

        System.out.println(maxHeight+1);
    }

    private static int insertIntoTree(TreeNode root, int num) {
        int height = 1;

        while (true) {
            int diff = num - root.val;

            if (Math.abs(diff) <= 500) {

                if (root.center == null) {
                    root.center = new TreeNode(num);
                    break;
                } else {
                    root = root.center;
                    height++;
                }
            } else if (diff < -500) {

                if (root.left == null) {
                    root.left = new TreeNode(num);
                    break;
                } else {
                    root = root.left;
                    height++;
                }
            } else {

                if (root.right == null) {
                    root.right = new TreeNode(num);
                    break;
                } else {
                    root = root.right;
                    height++;
                }
            }
        }

        return height;
    }
}