package com.het.demotest.e100.螺旋数字矩阵;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in); // 创建Scanner对象，用于从标准输入读取数据
        int n = in.nextInt(), m = in.nextInt(); // 读取数字个数n和矩阵的行数m
        int lie = (n + m-1) / m; // 计算矩阵的列数，以尽量使每行的元素数量最多，但总数不超过n

        String[][] mp = new String[m][lie]; // 创建矩阵mp，初始化为m行lie列
        for (int i=0; i<m; i++)
            Arrays.fill(mp[i], "*"); // 将矩阵的所有元素初始填充为"*"

        int[] dx={0, 1, 0, -1}, dy={1, 0, -1, 0}; // 方向数组，依次表示向右、向下、向左、向上移动
        int x=0, y=0, dir=0, cnt=1; // 初始化位置(x, y)、方向dir和当前数字cnt
        while (cnt <= n) // 当前填充的数字cnt不超过总数n时执行循环
        {
            mp[x][y] = Integer.toString(cnt); // 将当前位置的元素设置为cnt的字符串表示形式
            if (cnt==n)
                break;
            int nx, ny;
            while (true) // 寻找下一个合法位置
            {
                nx = x + dx[dir]; // 计算下一步的行坐标
                ny = y + dy[dir]; // 计算下一步的列坐标
                // 如果下一步越界或者下一步的位置已经被填充，则需要改变方向
                if (nx < 0 || nx >= m || ny < 0 || ny >= lie || !mp[nx][ny].equals("*"))
                    dir = (dir + 1) % 4; // 改变方向
                else
                    break; // 找到合法位置，退出循环
            }
            x = nx; // 更新当前行坐标
            y = ny; // 更新当前列坐标
            cnt++; // 填充下一个数字
        }

        for (int i=0; i<m; i++) // 遍历矩阵的每一行
        {
            for (int j=0; j<lie; j++) // 遍历行中的每个元素
                System.out.print(mp[i][j] + " "); // 输出元素和一个空格
            System.out.println(); // 每行结束后输出换行符
        }
    }
}