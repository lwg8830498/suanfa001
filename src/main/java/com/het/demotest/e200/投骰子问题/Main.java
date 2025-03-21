package com.het.demotest.e200.投骰子问题;

import java.util.Scanner;

public class Main {
    // 骰子的初始状态
    static int[] dice = {1, 2, 3, 4, 5, 6};  // 左、右、前、后、上、下分别为 dice[0] 到 dice[5]

    // 向左翻转 L
    public static void rollLeft() {
        int temp = dice[4];  // 旧的上面
        dice[4] = dice[1];   // 旧的右面 -> 新的上面
        dice[1] = dice[5];   // 旧的下面 -> 新的右面
        dice[5] = dice[0];   // 旧的左面 -> 新的下面
        dice[0] = temp;      // 旧的上面 -> 新的左面
    }

    // 向右翻转 R
    public static void rollRight() {
        int temp = dice[4];  // 旧的上面
        dice[4] = dice[0];   // 旧的左面 -> 新的上面
        dice[0] = dice[5];   // 旧的下面 -> 新的左面
        dice[5] = dice[1];   // 旧的右面 -> 新的下面
        dice[1] = temp;      // 旧的上面 -> 新的右面
    }

    // 向后翻转 F
    public static void rollBackward() {
        int temp = dice[4];  // 旧的上面
        dice[4] = dice[2];   // 旧的前面 -> 新的上面
        dice[2] = dice[5];   // 旧的下面 -> 新的前面
        dice[5] = dice[3];   // 旧的后面 -> 新的下面
        dice[3] = temp;      // 旧的上面 -> 新的后面
    }

    // 向前翻转 B
    public static void rollForward() {
        int temp = dice[4];  // 旧的上面
        dice[4] = dice[3];   // 旧的后面 -> 新的上面
        dice[3] = dice[5];   // 旧的下面 -> 新的后面
        dice[5] = dice[2];   // 旧的前面 -> 新的下面
        dice[2] = temp;      // 旧的上面 -> 新的前面
    }

    // 顺时针旋转 C
    public static void rotateClockwise() {
        int temp = dice[0];  // 旧的左面
        dice[0] = dice[2];   // 旧的前面 -> 新的左面
        dice[2] = dice[1];   // 旧的右面 -> 新的前面
        dice[1] = dice[3];   // 旧的后面 -> 新的右面
        dice[3] = temp;      // 旧的左面 -> 新的后面
    }

    // 逆时针旋转 A
    public static void rotateAnticlockwise() {
        int temp = dice[0];  // 旧的左面
        dice[0] = dice[3];   // 旧的后面 -> 新的左面
        dice[3] = dice[1];   // 旧的右面 -> 新的后面
        dice[1] = dice[2];   // 旧的前面 -> 新的右面
        dice[2] = temp;      // 旧的左面 -> 新的前面
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();  // 读取指令序列

        // 遍历输入字符串，执行相应的操作
        for (char move : input.toCharArray()) {
            if (move == 'L') {
                rollLeft();
            } else if (move == 'R') {
                rollRight();
            } else if (move == 'F') {
                rollForward();
            } else if (move == 'B') {
                rollBackward();
            } else if (move == 'A') {
                rotateAnticlockwise();
            } else if (move == 'C') {
                rotateClockwise();
            }
        }

        // 输出最终状态
        for (int i = 0; i < 6; i++) {
            System.out.print(dice[i]);
        }
        System.out.println();

        scanner.close();
    }
}