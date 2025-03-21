package com.het.demotest.e100.开心消消乐;

import java.util.Scanner;

public class Main {
    static int[][] nums;
    static int n;//行  
    static int m;//列
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m=sc.nextInt();
        nums=new int[n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                nums[i][j] = sc.nextInt();
            }
        }
        int ans=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(nums[i][j]==0){
                    continue;
                }
                ans+=dfs(i,j);
            }
        }
        System.out.println(ans);
    }

    public static int dfs(int x,int y){
        nums[x][y]=0;
        int[][] direction=new int[][]{{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
        for(int i=0;i<direction.length;i++){
            int cx=x+direction[i][0];
            int cy=y+direction[i][1];
            if(0<=cx&&cx<n&&0<=cy&&cy<m&&nums[cx][cy]==1){
                dfs(cx,cy);
            }
        }
        return 1;
    }
}