package com.bogdan;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * Created by Bogdan on 15-May-17.
 */
@SpringBootApplication
public class Main {

    public static void main(String[] args) throws Exception {
        Properties props = new Properties();
        String log4jConfigFile = System.getProperty("user.dir")
                + "/src/main/resources/log4j.properties";
        props.load(new FileInputStream(log4jConfigFile));
        PropertyConfigurator.configure(props);
        SpringApplication.run(Main.class, args);
    }
}