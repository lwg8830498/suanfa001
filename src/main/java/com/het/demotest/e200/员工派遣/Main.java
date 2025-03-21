package com.het.demotest.e200.员工派遣;

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
        int[] nums = split(input_str, " ");
        int x = nums[0];
        int y = nums[1];
        int count_x = nums[2];
        int count_y = nums[3];

        int left = 1;
        int right = Integer.MAX_VALUE;
        while (true) {
            if(left >= right){
                break;
            } else {
                int mid = (left + right) >> 1;
                int target = Math.max(0, count_x - mid / y + mid / (x * y)) + Math.max(0, count_y - mid / x + mid / (x * y));
                if (mid - mid / x - mid / y + mid / (x * y) >= target) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

        }
        System.out.println(left);
    }

    public static int[] split(String input_str,String chars){
        String[] tmp2 = input_str.split(chars);
        int[] results = new int[tmp2.length];
        for (int i = 0; i < tmp2.length; i++) {
            results[i] = Integer.parseInt(tmp2[i]);
        }
        return results;
    }
}