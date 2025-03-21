package com.het.demotest.e100.攀登者1;

import java.util.Scanner;
import java.util.*;
import java.util.stream.Stream;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        String input_str = in.nextLine();
        String[] tmp2 = input_str.split(",");
        int[] nums = new int[tmp2.length];
        for (int i = 0; i < tmp2.length; i++) {
            nums[i] = Integer.parseInt(tmp2[i]);
        }
        System.out.println(validMountainCount(nums));
    }

    public static int validMountainCount(int[] arr) {
        int result = 0;
        int i=0;
        int arr_len = arr.length;
        while(true){
            if(i>=arr_len){
                return result;
            } else {

                if(i==0){
                    if (arr[i+1] < arr[i]){
                        result += 1;
                    }
                } else if (i==arr_len-1){
                    if (arr[i-1] < arr[i]){
                        result += 1;
                    }
                } else {
                    if(arr[i-1] < arr[i] && arr[i+1] < arr[i]){
                        result += 1;
                    }
                }
            }
            i+=1;
        }
    }
}