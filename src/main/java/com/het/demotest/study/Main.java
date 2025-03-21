package com.het.demotest.study;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        String[] arr = s.split(" ");
        int[] nums = new int[arr.length*2];
        for (int i = 0; i < arr.length; i++) {
            nums[i] = Integer.parseInt(arr[i]);
            nums[i+arr.length] = nums[i];
        }
        for (int i = 0; i < arr.length; i++) {
            int num = nums[i];
            for (int j = 1; j < arr.length; j++) {
                if(nums[i+j] < num){
                    num = num+nums[i+j];
                    break;
                }
            }
            System.out.print(num+" ");
        }
        System.out.println();
    }
}