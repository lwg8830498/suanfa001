package com.het.demotest.e100.VLAN资源池;

import java.util.*;

public class Main {

    // 解析VLAN资源池，转换为VLAN的整数集合
    public static List<Integer> parseVLANPool(String vlanPool) {
        List<Integer> vlans = new ArrayList<>();
        String[] parts = vlanPool.split(",");

        for (String part : parts) {
            if (part.contains("-")) {
                // 处理范围，如"1-5"
                String[] range = part.split("-");
                int start = Integer.parseInt(range[0]);
                int end = Integer.parseInt(range[1]);
                for (int i = start; i <= end; i++) {
                    vlans.add(i);
                }
            } else {
                // 处理单个VLAN
                vlans.add(Integer.parseInt(part));
            }
        }

        return vlans;
    }

    // 将VLAN集合转换为符合要求的输出字符串
    public static String formatVLANPool(List<Integer> vlans) {
        if (vlans.isEmpty()) return "";

        StringBuilder result = new StringBuilder();
        int start = vlans.get(0), end = vlans.get(0);

        for (int i = 1; i <= vlans.size(); i++) {
            if (i == vlans.size() || vlans.get(i) != end + 1) {
                // 不连续了，添加到结果
                if (start == end) {
                    result.append(start);
                } else {
                    result.append(start).append("-").append(end);
                }
                if (i != vlans.size()) result.append(",");

                if (i != vlans.size()) {
                    start = vlans.get(i);
                    end = vlans.get(i);
                }
            } else {
                // VLAN连续，更新结束值
                end = vlans.get(i);
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 输入VLAN资源池字符串
        String vlanPool = scanner.nextLine();

        // 输入申请的VLAN
        int requestedVLAN = scanner.nextInt();

        // 解析VLAN资源池
        List<Integer> vlans = parseVLANPool(vlanPool);

        // 查找并移除申请的VLAN
        vlans.removeIf(vlan -> vlan == requestedVLAN);

        // 对剩下的VLAN进行排序
        Collections.sort(vlans);

        // 将结果格式化并输出
        String result = formatVLANPool(vlans);
        System.out.println(result);

        scanner.close();
    }
}