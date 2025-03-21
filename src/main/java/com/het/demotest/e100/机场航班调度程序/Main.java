package com.het.demotest.e100.机场航班调度程序;

import java.util.Scanner;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] airplanes = input.split(",");

        Map<Character,Integer> map = new HashMap<>();
        map.put('$',1);
        map.put('&',2);
        map.put('*',3);
        map.put('0',11);
        map.put('1',12);
        map.put('2',13);
        map.put('3',14);
        map.put('4',15);
        map.put('5',16);
        map.put('6',17);
        map.put('7',18);
        map.put('8',19);
        map.put('9',20);
        map.put('A',21);
        map.put('B',22);
        map.put('C',23);
        map.put('D',24);
        map.put('E',25);
        map.put('F',26);
        map.put('G',27);
        map.put('H',28);
        map.put('I',29);
        map.put('J',30);
        map.put('K',31);
        map.put('L',32);
        map.put('M',33);
        map.put('N',34);
        map.put('O',35);
        map.put('P',36);
        map.put('Q',37);
        map.put('R',38);
        map.put('S',39);
        map.put('T',40);
        map.put('U',41);
        map.put('V',42);
        map.put('W',43);
        map.put('X',44);
        map.put('Y',45);
        map.put('Z',46);
        Arrays.sort(airplanes,new Comparator<String>(){
            public int compare(String str1, String str2)
            {
                if(str1.charAt(0) == str2.charAt(0))
                {
                    if(str1.charAt(1) == str2.charAt(1))
                    {
                        return Integer.parseInt(str1.substring(2)) - Integer.parseInt(str2.substring(2));
                    }
                    else
                    {
                        return map.get(str1.charAt(1)) - map.get(str2.charAt(1));
                    }
                }

                return map.get(str1.charAt(0)) - map.get(str2.charAt(0));
            }
        });

        StringBuilder sb = new StringBuilder();
        for(String plane : airplanes)
        {
            sb.append(plane);
            sb.append(",");
        }
        if(airplanes.length > 0)
        {
            sb.deleteCharAt(sb.length() - 1);
        }

        System.out.println(sb);

    }
}