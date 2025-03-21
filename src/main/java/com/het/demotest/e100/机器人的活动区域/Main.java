package com.het.demotest.e100.机器人的活动区域;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            // 第一行输入一串数字，以空格分隔
            String line = sc.nextLine();
            String[] rec = line.split(" ");

            int rowCnt = Integer.parseInt( rec[0] );
            int columnCnt = Integer.parseInt( rec[1] );

            int[][] area = new int[rowCnt][columnCnt];
            for( int i = 0; i < rowCnt; i ++ )
            {
                String row = sc.nextLine();
                String[] columns = row.split(" ");
                for( int j = 0; j < columnCnt; j ++ )
                {
                    area[i][j] = Integer.parseInt( columns[j] );
                }
            }
            processRobotMoveArea( area);
        }

    }

    private static void processRobotMoveArea( int[][] area ) {
        int maxPartAreaCnt = 0;

        for( int i = 0; i < area.length; i ++ )
        {
            for( int j = 0; j < area[i].length; j ++ )
            {
                int cellValue = area[i][j];
                if( cellValue == -1 )
                {
                    continue;
                }
                int partAreaCnt = getPartAraCnt( i, j , area );
                if( partAreaCnt > maxPartAreaCnt )
                {
                    maxPartAreaCnt = partAreaCnt;
                }
            }
        }
        System.out.println( maxPartAreaCnt );

    }

    private static int getPartAraCnt( int i, int j, int[][] area ) {
        int areaCnt = 0;
        int rowCnt = area.length;
        int columnCnt = area[0].length;
        int cellValue = area[i][j];

        area[i][j] = -1;
        areaCnt ++;
        if( i > 0 && area[i - 1][j] != -1 && Math.abs(cellValue - area[i - 1][j]) <= 1 )
        {
            areaCnt += getPartAraCnt( i - 1, j, area );
        }
        if( i < rowCnt - 1 && area[i + 1][j] != -1 && Math.abs(cellValue - area[i + 1][j]) <= 1)
        {
            areaCnt += getPartAraCnt( i + 1, j, area );
        }

        if( j > 0 && area[i][j - 1 ] != -1 && Math.abs(cellValue - area[i][j-1]) <= 1) {
            areaCnt += getPartAraCnt( i, j - 1, area );
        }

        if( j < columnCnt - 1 && area[i][j + 1] != -1 && Math.abs(cellValue - area[i][j + 1]) <= 1)
        {
            areaCnt += getPartAraCnt( i, j + 1, area );
        }

        return areaCnt;
    }

}