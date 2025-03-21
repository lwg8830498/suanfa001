package com.het.demotest.e100.数大雁;

import java.util.*;

public class Main {

    public static int countDucks(String s) {
        int q = 0, u = 0, a = 0, c = 0, k = 0;
        int maxDucks = 0;
        Queue<Integer> Q = new LinkedList<>();
        int[] cnt = new int[s.length() + 1];

        for (int i = 0; i < s.length(); i++) {
            cnt[i + 1] = cnt[i];

            char ch = s.charAt(i);
            if (ch == 'q') {
                q++;
                Q.offer(i);
            } else if (ch == 'u' && q > u) {
                u++;  // 增加 'u' 的计数
            } else if (ch == 'a' && u > a) {
                a++;  // 增加 'a' 的计数
            } else if (ch == 'c' && a > c) {
                c++;  // 增加 'c' 的计数
            } else if (ch == 'k' && c > k) {
                k++;
                cnt[i + 1]++;

                int qFirst = Q.poll();

                maxDucks = Math.max(maxDucks, cnt[i + 1] - cnt[qFirst]);
            } else if (ch != 'u' && ch != 'a' && ch != 'c' && ch != 'k') {
                return -1;
            }
        }

        return maxDucks == 0 ? -1 : maxDucks;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputSound = scanner.nextLine();
        System.out.println(countDucks(inputSound));
        scanner.close();
    }
}