package com.example.genaration;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan({"com.example.genaration.mapper", "com.example.genaration.dao"})
public class GenarationApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(GenarationApplication.class, args);
    }

}
