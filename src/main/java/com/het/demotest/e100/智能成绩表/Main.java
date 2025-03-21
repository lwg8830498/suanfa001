package com.het.demotest.e100.智能成绩表;

import java.util.*;

public class Main {
    public static String standard;
    static class Student implements Comparable<Student>{
        String name;
        Map<String,Integer> projects;
        int tot =0;

        public Student(String name){
            this.name = name;
            projects = new HashMap<>();
        }
        public void add(String project, int score ){
            projects.put(project,score);
            tot += score;
        }
        @Override
        public int compareTo(Student o){
            int p0 = tot , p1 =o.tot;
            if(projects.containsKey(standard)){
                p0=projects.get(standard);
                p1=o.projects.get(standard);
            }

            if(p0 != p1) {
                return p1-p0;
            } else {
                return name.compareTo(o.name);
            }
        }
    }




    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n =sc.nextInt();
        int m =sc.nextInt();
        sc.nextLine();
        String[] projects = sc.nextLine().split(" ");
        List<Student> students = new ArrayList<>();

        for(int i=0 ; i<n ; i++){
            String name=sc.next();
            Student student=new Student(name);
            for(int j=0 ; j<m ; j++){
                int cur =sc.nextInt();
                student.add(projects[j],cur);
            }
            students.add(student);
        }

        standard=sc.next();
        Collections.sort(students);
        for(Student student : students){
            System.out.print(student.name+" ");
        }

    }
}