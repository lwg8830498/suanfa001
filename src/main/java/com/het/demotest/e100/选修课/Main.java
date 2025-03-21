package com.het.demotest.e100.选修课;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String course1 = scanner.nextLine();
        String course2 = scanner.nextLine();

        Map<String, Integer> course1Map = parseCourse(course1);
        Map<String, Integer> course2Map = parseCourse(course2);

        Set<String> commonStudents = new HashSet<>(course1Map.keySet());
        commonStudents.retainAll(course2Map.keySet());

        if (commonStudents.isEmpty()) {
            System.out.println("NULL");
            return;
        }

        Map<String, List<Student>> classMap = new TreeMap<>();
        for (String studentId : commonStudents) {
            String classId = studentId.substring(0, 5);
            int totalScore = course1Map.get(studentId) + course2Map.get(studentId);
            classMap.computeIfAbsent(classId, k -> new ArrayList<>()).add(new Student(studentId, totalScore));
        }

        for (Map.Entry<String, List<Student>> entry : classMap.entrySet()) {
            System.out.println(entry.getKey());
            List<Student> students = entry.getValue();
            students.sort((s1, s2) -> {
                if (s2.totalScore != s1.totalScore) {
                    return s2.totalScore - s1.totalScore;
                } else {
                    return s1.studentId.compareTo(s2.studentId);
                }
            });
            StringBuilder sb = new StringBuilder();
            for (Student student : students) {
                sb.append(student.studentId).append(";");
            }
            sb.deleteCharAt(sb.length() - 1);
            System.out.println(sb);
        }
    }

    private static Map<String, Integer> parseCourse(String course) {
        Map<String, Integer> map = new HashMap<>();
        String[] students = course.split(";");
        for (String student : students) {
            String[] parts = student.split(",");
            map.put(parts[0], Integer.parseInt(parts[1]));
        }
        return map;
    }

    static class Student {
        String studentId;
        int totalScore;

        Student(String studentId, int totalScore) {
            this.studentId = studentId;
            this.totalScore = totalScore;
        }
    }
}