package com.het.demotest.e100.开源项目热榜;

import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] weights = new int[5];
        for(int i = 0; i < 5; i++){
            weights[i] = in.nextInt();
        }
        String tt = in.nextLine();
        List<Map<String, Object>> projects = new ArrayList<>();
        for(int i = 0; i < n; i++){
            int[] pro = new int[5];
            String[] strs = in.nextLine().split(" ");
            String name = strs[0];
            pro[0] = Integer.parseInt(strs[1]);
            pro[1] = Integer.parseInt(strs[2]);
            pro[2] = Integer.parseInt(strs[3]);
            pro[3] = Integer.parseInt(strs[4]);
            pro[4] = Integer.parseInt(strs[5]);
            int hotValue = calcuateHotvalue(weights, pro);
            Map<String,Object> map = new HashMap<>();
            map.put("name",name);
            map.put("hotvalue",hotValue);
            projects.add(map);
        }
        Collections.sort(projects, (p1,p2) -> {
            if((int) p1.get("hotvalue") != (int) p2.get("hotvalue")){
                return Integer.compare((int) p2.get("hotvalue"), (int) p1.get("hotvalue"));
            }else{
                return ((String) p1.get("name")).toLowerCase().compareTo(((String) p2.get("name")).toLowerCase());
            }
        });
        for(Map<String,Object> project: projects){
            System.out.println(project.get("name"));
        }
    }
    public static int calcuateHotvalue(int[] weights, int[] pro){
        int res = 0;
        for(int i = 0; i < weights.length; i++){
            res += weights[i] * pro[i];
        }
        return res;
    }

}