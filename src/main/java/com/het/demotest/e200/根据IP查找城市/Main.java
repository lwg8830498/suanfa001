package com.het.demotest.e200.根据IP查找城市;

import java.util.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main {

    // 将IP地址转换为长整数
    private static long ipToLong(String ip) {
        long ipNum = 0;
        try {
            byte[] bytes = InetAddress.getByName(ip).getAddress();
            for (byte b : bytes) {
                ipNum = ipNum << 8 | (b & 0xFF);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return ipNum;
    }

    static class IpRange implements Comparable<IpRange> {
        long start, end;
        String city;

        public IpRange(long start, long end, String city) {
            this.start = start;
            this.end = end;
            this.city = city;
        }

        // 实现Comparable接口，用于排序
        @Override
        public int compareTo(IpRange other) {
            if (this.start != other.start) {
                return Long.compare(this.start, other.start);
            }
            return Long.compare(this.end, other.end);
        }
    }

    // 根据查询的IP找出最佳匹配的城市名
    public static List<String> findCityMatches(List<IpRange> ranges, List<String> queries) {
        Collections.sort(ranges);
        List<String> results = new ArrayList<>();
        for (String ip : queries) {
            long ipNum = ipToLong(ip);
            String bestMatch = "";
            long smallestRange = Long.MAX_VALUE;

            for (IpRange range : ranges) {
                if (ipNum >= range.start && ipNum <= range.end) {
                    long rangeSize = range.end - range.start;
                    if (rangeSize < smallestRange || (rangeSize == smallestRange && range.city.compareTo(bestMatch) > 0)) {
                        bestMatch = range.city;
                        smallestRange = rangeSize;
                    }
                }
            }

            results.add(bestMatch);
        }
        return results;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputRanges = scanner.nextLine();
        String inputIPs = scanner.nextLine();

        List<IpRange> ranges = new ArrayList<>();
        String[] segments = inputRanges.split(";");

        // 解析输入的IP范围
        for (String segment : segments) {
            int pos = segment.indexOf('=');
            String city = segment.substring(0, pos);
            String range = segment.substring(pos + 1);
            int commaPos = range.indexOf(',');
            String startIp = range.substring(0, commaPos);
            String endIp = range.substring(commaPos + 1);

            IpRange ipRange = new IpRange(ipToLong(startIp), ipToLong(endIp), city);
            ranges.add(ipRange);
        }

        List<String> queries = Arrays.asList(inputIPs.split(","));

        // 查找每个IP的最佳匹配城市
        List<String> results = findCityMatches(ranges, queries);

        // 输出结果
        System.out.println(String.join(",", results));
    }
}