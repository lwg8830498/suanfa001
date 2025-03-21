package com.het.demotest.e200.最大社交距离;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 读取座位总数
        int seatNum = sc.nextInt();
        sc.nextLine();
        String arrayInput = sc.nextLine();  // 读取整个剩余的数组部分

        // 去掉输入中的方括号
        arrayInput = arrayInput.substring(1, arrayInput.length() - 1);

        // 按照逗号分割并转化为整数
        String[] actions = arrayInput.split(",");
        List<Integer> seatOrLeave = new ArrayList<>();

        // 把动作序列转化为整数并存储
        for (String action : actions) {
            seatOrLeave.add(Integer.parseInt(action.trim()));
        }

        int lastSeat = -1;  // 记录最后一个进场员工的座位
        Set<Integer> seats = new HashSet<>();  // 使用 HashSet 存储已坐人的座位

        for (int action : seatOrLeave) {  // 遍历进出场动作
            if (action == 1) {  // 入场
                if (seats.size() == seatNum) {  // 如果座位已满，输出-1
                    System.out.println(-1);
                    return;
                }

                int seat = -1, maxDist = -1;
                if (seats.isEmpty()) {  // 第一个人入座，直接坐在 0 号座位
                    seat = 0;
                    lastSeat = seat;
                } else {
                    // 遍历座位，找到距离最大的位置
                    int prev = -1;
                    for (int i = 0; i < seatNum; ++i) {
                        if (seats.contains(i)) {
                            prev = i;  // 记录最近的已坐人的位置
                        } else {
                            int dist;
                            if (prev == -1) {
                                dist = i;  // 从左边界到当前空位的距离
                            } else {
                                dist = (i - prev + 1) / 2;  // 计算空位距离的中点
                            }
                            if (dist > maxDist) {  // 更新最大距离
                                maxDist = dist;
                                seat = prev + dist;  // 更新最优座位
                            }
                        }
                    }

                    // 检查右边界，看看是否有更好的座位
                    if (!seats.contains(seatNum - 1)) {
                        int dist = seatNum - 1 - Collections.max(seats);
                        if (dist > maxDist) {
                            seat = seatNum - 1;
                        }
                    }
                }
                lastSeat = seat;  // 记录当前进场员工的座位
                seats.add(seat);  // 安排员工入座

            } else {  // 离场
                int leaveSeat = -action;  // 读取离开座位的编号
                seats.remove(leaveSeat);  // 将该员工移除座位
            }
        }

        System.out.println(lastSeat);  // 输出最后一个进来的员工的位置
    }
}