package com.het.demotest.e100.求幸存数之和;

import java.util.*;


public class Main {

    public long sumOfLeft (int[] nums, int jump, int left) {
        int n = nums.length;
        boolean[] used = new boolean[n];
        int size = n;
        int i = 0;
        int count = 0;
        while (size > left) {
            while (true) {
                i = (i + 1) % n;
                if(!used[i]) {
                    count++;
                }
                if(count == jump + 1) {
                    used[i % n] = true;
                    count = 0;
                    size--;
                    break;
                }
            }
        }
        int sum = 0;
        for (int j = 0; j < used.length; j++) {
            if (!used[j]) {
                sum += nums[j];
            }
        }
        return sum;
    }
}