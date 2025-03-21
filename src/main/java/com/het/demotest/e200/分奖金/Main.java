package com.het.demotest.e200.分奖金;

import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args){
        Scanner in=new Scanner(System.in);
        int N=in.nextInt();
        int[] nums=new int[N];
        for (int i=0;i<N;i++){
            nums[i]=in.nextInt();

        }
        int[] res=findMax(nums);
        for(int i=0;i<N;i++){
            if (res[i]==-1){
                System.out.print(nums[i]);
            }else{
                System.out.print(res[i]);
            }
            if(i!=N-1){
                System.out.print(" ");
            }
        }
    }
    public static  int[] findMax(int[] array){
        int len = array.length;
        Stack<Integer> st = new Stack<>();
        int res[]=new int[len];
        int i=0;
        while(i<len){
            if(st.isEmpty()||array[i]<=array[st.peek()]){
                st.push(i);
                i++;
            }else{
                res[st.peek()]=(array[i]-array[st.peek()])*(i-st.peek());
                st.pop();
            }
        }
        while(!st.isEmpty()){
            res[st.pop()]=-1;
        }
        return  res;
    }
}