package com.het.demotest.e100.服务失效判断;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String dependencyLine = scanner.nextLine();
        String failureLine = scanner.nextLine();

        // 解析依赖关系
        List<Map.Entry<String, String>> dependencies = new ArrayList<>();
        String[] dependencyPairs = dependencyLine.split(",");
        for (String pair : dependencyPairs) {
            String[] services = pair.split("-");
            dependencies.add(new AbstractMap.SimpleEntry<>(services[0], services[1]));
        }

        // 解析故障服务
        Set<String> failedServices = new HashSet<>();
        String[] failedServiceArray = failureLine.split(",");
        for (String service : failedServiceArray) {
            failedServices.add(service);
        }

        // 构建反向依赖图
        Map<String, List<String>> dependedBy = new HashMap<>();
        Set<String> allServices = new HashSet<>();

        for (Map.Entry<String, String> dep : dependencies) {
            String service1 = dep.getKey();
            String service2 = dep.getValue();

            if (!dependedBy.containsKey(service2)) {
                dependedBy.put(service2, new ArrayList<>());
            }
            dependedBy.get(service2).add(service1);

            allServices.add(service1);
            allServices.add(service2);
        }

        // 故障传播
        Queue<String> failureQueue = new LinkedList<>();
        for (String failed : failedServices) {
            failureQueue.add(failed);
        }

        while (!failureQueue.isEmpty()) {
            String current = failureQueue.poll();

            if (dependedBy.containsKey(current)) {
                for (String dependent : dependedBy.get(current)) {
                    if (!failedServices.contains(dependent)) {
                        failedServices.add(dependent);
                        failureQueue.add(dependent);
                    }
                }
            }
        }

        // 按照依赖关系列表中的顺序查找正常服务
        List<String> normalServices = new ArrayList<>();
        Set<String> addedServices = new HashSet<>();

        for (Map.Entry<String, String> dep : dependencies) {
            String service1 = dep.getKey();
            String service2 = dep.getValue();

            if (!failedServices.contains(service1) && !addedServices.contains(service1)) {
                normalServices.add(service1);
                addedServices.add(service1);
            }

            if (!failedServices.contains(service2) && !addedServices.contains(service2)) {
                normalServices.add(service2);
                addedServices.add(service2);
            }
        }

        // 输出正常服务
        if (normalServices.isEmpty()) {
            System.out.println(",");
        } else {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < normalServices.size(); i++) {
                result.append(normalServices.get(i));
                if (i < normalServices.size() - 1) {
                    result.append(",");
                }
            }
            System.out.println(result.toString());
        }

        scanner.close();
    }
}