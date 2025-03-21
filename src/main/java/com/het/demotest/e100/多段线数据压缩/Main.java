package com.het.demotest.e100.多段线数据压缩;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        String[] numStrs = input.split(" ");

        int[] nums = new int[numStrs.length];
        for (int i = 0; i < numStrs.length; i++) {
            nums[i] = Integer.parseInt(numStrs[i]);
        }

        int nodeNum = numStrs.length / 2;
        Node[] nodes = new Node[nodeNum];
        for (int i = 0; i < nodeNum; i++) {
            nodes[i] = new Node(nums[2 * i], nums[2 * i + 1]);
        }

        Node lastDirect = null;
        Node last = nodes[0];
        List<Node> res = new ArrayList<>();

        res.add(nodes[0]);
        for (int i = 1; i < nodes.length; i++) {

            Node newDirect = new Node(nodes[i].i - last.i, nodes[i].j - last.j);

            if (lastDirect == null) {
                lastDirect = newDirect;
            } else {

                if (!lastDirect.equals(newDirect)) {
                    res.add(last);
                    lastDirect = newDirect;
                }
            }

            last = nodes[i];
        }
        res.add(last);



        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < res.size(); i++) {
            sb.append(res.get(i).i).append(" ");
            sb.append(res.get(i).j).append(" ");
        }
        System.out.println(sb.substring(0, sb.length() - 1));
    }

    static class Node {
        int i;
        int j;

        public Node(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Node node = (Node) o;
            return i == node.i && j == node.j;
        }

        @Override
        public int hashCode() {
            return Objects.hash(i, j);
        }
    }
}