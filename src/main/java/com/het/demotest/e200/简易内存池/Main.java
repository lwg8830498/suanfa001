package com.het.demotest.e200.简易内存池;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] mem = new int[105];  // 用于记录内存池中每个字节的使用状态
        int[] len = new int[105];  // 用于记录每个分配块的长度
        int n = in.nextInt();  // 输入操作命令的数量
        while (n-- > 0) {
            String s = in.next();
            int pd = 0;
            // 找到等号的位置，分隔操作命令和参数
            while (s.charAt(pd) != '=') {
                pd++;
            }
            String op = s.substring(0, pd);  // 提取操作命令
            int val = Integer.parseInt(s.substring(pd + 1));  // 提取参数值
            // 根据操作命令类型执行相应操作
            if (op.equals("REQUEST")) {
                int p = request(mem, len, val);
                if (p == -1) {
                    System.out.println("error");  // 内存分配失败
                } else {
                    System.out.println(p);  // 内存分配成功，输出首地址
                }
            } else if (op.equals("RELEASE")) {
                if (!release(mem, len, val)) {
                    System.out.println("error");  // 内存释放失败
                }
            }
        }
    }

    // 释放内存函数，输入参数为需要释放的内存块的首地址
    static boolean release(int[] mem, int[] len, int p) {
        // 如果该首地址处没有分配内存，则返回错误
        if (len[p] == 0) {
            return false;
        }
        // 将从p开始的len[p]个字节的内存标记为未使用状态
        for (int i = p; i <= p + len[p] - 1; i++) {
            mem[i] = 0;
        }
        // 清空该首地址处的内存块长度记录
        len[p] = 0;
        return true;
    }

    // 请求内存函数，输入参数为请求的内存大小，返回值为分配到的内存首地址
    static int request(int[] mem, int[] len, int siz) {
        // 如果请求的内存大小为0，则返回错误
        if (siz == 0) {
            return -1;
        }
        // 遍历内存池寻找合适的连续空闲块
        for (int l = 0; l < 100; l++) {
            // 如果当前字节已经被分配，则跳过
            if (mem[l] != 0) {
                continue;
            }
            int r = l;
            // 找到从l开始的连续空闲块的末尾r
            while (r < 100 && mem[r] == 0) {
                r++;
            }
            // 如果找到的连续空闲块的大小满足请求
            if (r - l >= siz) {
                // 将该连续块标记为已使用
                for (int i = l; i < l + siz; i++) {
                    mem[i] = 1;
                }
                // 记录该块的长度
                len[l] = siz;
                return l;  // 返回分配到的内存首地址
            }
            // 跳过已检查的连续空闲块
            l = r;
        }
        // 如果未找到合适的空闲块，则返回错误
        return -1;
    }
}