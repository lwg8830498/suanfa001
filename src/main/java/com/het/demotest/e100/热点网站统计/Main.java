package com.het.demotest.e100.热点网站统计;

import java.util.*;

// 定义URLInfo类，实现Comparable接口，用于存储每个URL及其访问次数，并用于排序
class URLInfo implements Comparable<URLInfo> {
    String url;  // 保存URL
    int count;   // 保存该URL的访问次数

    // 构造函数，初始化URL和访问次数
    URLInfo(String url, int count) {
        this.url = url;
        this.count = count;
    }

    // 自定义比较函数：按访问次数降序排序，如果访问次数相同则按字典序升序排序
    @Override
    public int compareTo(URLInfo other) {
        // 比较访问次数，次数高的排前面
        if (this.count != other.count) {
            return Integer.compare(other.count, this.count);  // 按访问次数降序排列
        }
        // 如果访问次数相同，则按URL字典序升序排列
        return this.url.compareTo(other.url);
    }
}

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);  // 用于读取输入
        Map<String, Integer> urlCounts = new HashMap<>();  // 使用HashMap存储URL及其访问次数
        int totalVisits = 0;  // 总的URL访问次数

        // 循环读取每一行输入
        while (sc.hasNextLine()) {
            String input = sc.nextLine().trim();  // 去除每行输入的空格和换行符
            if (input.isEmpty()) continue;  // 如果输入是空行，跳过当前循环

            // 判断输入是否为数字，如果是数字，表示要输出访问次数最多的前N个URL
            if (Character.isDigit(input.charAt(0))) {
                int N = Integer.parseInt(input);  // 将输入转换为整数N
                if (N > 0 && N <= 10 && N <= totalVisits) {  // 确保N的范围合法
                    List<URLInfo> urlList = new ArrayList<>();  // 用于存储URL及其访问次数

                    // 将HashMap中的URL和访问次数转化为URLInfo对象列表
                    for (Map.Entry<String, Integer> entry : urlCounts.entrySet()) {
                        urlList.add(new URLInfo(entry.getKey(), entry.getValue()));
                    }

                    // 按照访问次数和字典序对URL列表进行排序
                    Collections.sort(urlList);

                    // 输出前N个访问次数最多的URL
                    for (int i = 0; i < N; i++) {
                        if (i > 0) {
                            System.out.print(",");  // 在输出多个URL时用逗号分隔
                        }
                        System.out.print(urlList.get(i).url);  // 输出排序后的URL
                    }
                    System.out.println();  // 输出换行
                }
            } else {
                // 如果输入不是数字，表示是URL，更新该URL的访问次数
                // 使用HashMap的getOrDefault方法，若URL存在则计数加1，否则初始计数为1
                urlCounts.put(input, urlCounts.getOrDefault(input, 0) + 1);
                totalVisits++;  // 总访问次数加1
            }
        }
        sc.close();  // 关闭输入流
    }
}