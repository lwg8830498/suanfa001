package com.het.demotest.e200.模拟目录管理;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    static class DirectoryManager {
        static class Directory {
            Map<String, Directory> children = new HashMap<>();  // 存储子目录的哈希表
        }

        private Directory root;    // 根目录对象
        private Directory current; // 当前目录对象
        private String currentPath; // 当前目录路径字符串

        public DirectoryManager() {
            root = new Directory();
            current = root;
            currentPath = "/";  // 初始化当前路径为根目录
        }

        // 创建目录的方法
        public void mkdir(String name) {
            if (!current.children.containsKey(name)) {  // 检查当前目录下是否已存在同名目录
                current.children.put(name, new Directory());  // 不存在则添加
            }
        }

        // 改变当前目录的方法
        public void cd(String path) {
            if (path.equals("..")) {
                // 如果命令是返回上一级目录
                if (!currentPath.equals("/")) {
                    // 回退到上一级目录的路径
                    currentPath = currentPath.substring(0, currentPath.lastIndexOf('/', currentPath.length() - 2) + 1);
                    current = root; // 从根目录重新遍历以定位到正确的目录对象
                    String[] dirs = currentPath.split("/");
                    for (String dir : dirs) {
                        if (!dir.isEmpty() && current.children.containsKey(dir)) {
                            current = current.children.get(dir);  // 按路径更新current指向
                        }
                    }
                }
            } else {
                // 如果命令是进入一个子目录
                if (current.children.containsKey(path)) {
                    current = current.children.get(path);
                    if (!currentPath.endsWith("/")) {
                        currentPath += "/";
                    }
                    currentPath += path;
                }
            }
        }

        // 获取当前目录路径的方法
        public String pwd() {
            return currentPath.endsWith("/") ? currentPath : currentPath + "/";  // 确保路径以斜线结尾
        }
    }

    public static void main(String[] args) {
        DirectoryManager dm = new DirectoryManager();
        Scanner scanner = new Scanner(System.in);
        String lastOutput = "";  // 用于存储最后的pwd输出
        while (scanner.hasNextLine()) {
            lastOutput = "";
            String input = scanner.nextLine().trim();
            if (input.startsWith("mkdir ")) {
                String dirName = input.substring(6);
                dm.mkdir(dirName);  // 执行创建目录命令
            } else if (input.startsWith("cd ")) {
                String dirName = input.substring(3);
                dm.cd(dirName);  // 执行改变目录命令
            } else if (input.equals("pwd")) {
                lastOutput = dm.pwd();  // 执行显示当前路径命令，并记录输出
            }
        }

        System.out.println(lastOutput);  // 输出最后一条pwd命令的结果
        scanner.close();
    }
}