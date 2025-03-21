package com.het.demotest.e100.找座位;

public class Main {
    public static void main(String[] args) {
        // 示例输入
        int[] seats = {1, 0, 0, 0, 1};
        System.out.println(maxAdditionalSeats(seats));
    }

    public static int maxAdditionalSeats(int[] seats) {
        int count = 0;
        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == 0) {
                // 检查左边是否为空或边界
                boolean leftEmpty = (i == 0 || seats[i - 1] == 0);
                // 检查右边是否为空或边界
                boolean rightEmpty = (i == seats.length - 1 || seats[i + 1] == 0);
                if (leftEmpty && rightEmpty) {
                    seats[i] = 1; // 标记为已坐人
                    count++;
                }
            }
        }
        return count;
    }
}