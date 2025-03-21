package com.het.demotest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Arrays;

@ApplicationPath("/api")
public class HelloApplication extends Application {


    public static void main(String[] args) {
        System.out.println(0%2);
    }

}