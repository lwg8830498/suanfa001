package com.het.demotest.e100.关联字串;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    // 函数：获取字符串中每个字符的出现频率
    // 参数：s - 需要计算字符频率的字符串
    // 返回：存储字符频率的HashMap，键是字符，值是该字符的出现次数
    private static Map<Character, Integer> getCharFrequency(String s) {
        Map<Character, Integer> map = new HashMap<>();  // 创建一个空的 HashMap 用于存储字符频率
        for (char c : s.toCharArray()) {  // 遍历字符串中的每个字符
            // 如果字符已存在于 map 中，则将其计数加1，否则初始化为1
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        return map;  // 返回字符频率表
    }

    // 函数：比较两个字符频率表是否相同
    // 参数：map1 和 map2 - 两个字符频率表（HashMap）
    // 返回：布尔值，true 表示两个频率表相等，false 表示不相等
    private static boolean isSameFrequency(Map<Character, Integer> map1, Map<Character, Integer> map2) {
        return map1.equals(map2);  // 直接比较两个 HashMap 是否完全相等
    }

    // 函数：查找第一个字符串的排列是否为第二个字符串的子串
    // 参数：sub - 用于匹配的字符串
    //       main - 需要查找子串的字符串
    // 返回：如果找到匹配的子串，返回其起始索引，否则返回-1
    public static int findPermutation(String sub, String main) {
        int subLen = sub.length();  // 获取 sub 字符串的长度
        int mainLen = main.length();  // 获取 main 字符串的长度

        // 如果第一个字符串的长度大于第二个字符串，直接返回 -1
        if (subLen > mainLen) {
            return -1;
        }

        // 获取第一个字符串的字符频率表
        Map<Character, Integer> subFreq = getCharFrequency(sub);

        // 获取第二个字符串的前 subLen 个字符频率表，作为初始滑动窗口
        Map<Character, Integer> windowFreq = getCharFrequency(main.substring(0, subLen));

        // 遍历 main 字符串，滑动窗口从索引 0 移动到 mainLen - subLen
        for (int i = 0; i <= mainLen - subLen; i++) {
            if (i > 0) {
                // 更新滑动窗口：移除左边字符，添加右边字符
                char left = main.charAt(i - 1);  // 左边滑出窗口的字符
                char right = main.charAt(i + subLen - 1);  // 右边进入窗口的字符

                // 左边字符从窗口中移除，减少其频率
                windowFreq.put(left, windowFreq.get(left) - 1);
                // 如果字符频率为 0，删除该字符
                if (windowFreq.get(left) == 0) windowFreq.remove(left);

                // 将右边新字符加入窗口，增加其频率
                windowFreq.put(right, windowFreq.getOrDefault(right, 0) + 1);
            }

            // 如果滑动窗口的字符频率与 sub 字符串的字符频率相同，表示找到匹配
            if (isSameFrequency(subFreq, windowFreq)) {
                return i;  // 返回匹配的子串的起始索引
            }
        }

        // 遍历结束没有找到匹配的子串，返回 -1
        return -1;
    }

    // 主程序入口，获取输入并输出结果
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);  // 创建 Scanner 对象读取用户输入

        // 输入格式：一行输入两个字符串，用空格分隔
        String sub = scanner.next();  // 读取第一个字符串
        String main = scanner.next();  // 读取第二个字符串

        // 输出结果：第一个字符串的排列组合是否为第二个字符串的子串
        System.out.println(findPermutation(sub, main));  // 输出匹配子串的起始索引或 -1
    }
}
 