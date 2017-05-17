package com.bogdan;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by Bogdan on 15-May-17.
 */
@SpringBootApplication
public class Main {
    public static void main(String[] args) throws Exception {
        PropertyConfigurator.configure("src\\main\\resources\\log4j.properties");
        SpringApplication.run(Main.class, args);
    }
}