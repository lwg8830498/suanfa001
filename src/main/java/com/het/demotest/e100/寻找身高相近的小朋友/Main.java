package com.het.demotest.e100.寻找身高相近的小朋友;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int mingHeight = scanner.nextInt();
        int numFriends = scanner.nextInt();

        List<Integer> frinedHeights = new ArrayList<Integer>();

        for(int i = 0;i<numFriends;i++){
            int height = scanner.nextInt();
            frinedHeights.add(height);
        }

        Collections.sort(frinedHeights,new Comparator<Integer>(){
            @Override
            public int compare(Integer h1,Integer h2){
                int different1 = Math.abs(h1-mingHeight);
                int differert2 = Math.abs(h2-mingHeight);

                if(different1 == differert2){
                    return h1-h2;
                }
                return different1 - differert2;
            }
        });

        for(int height : frinedHeights){
            System.out.print(height + " ");
        }


    }
}