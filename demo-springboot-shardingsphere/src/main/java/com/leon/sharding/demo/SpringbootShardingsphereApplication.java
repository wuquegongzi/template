package com.leon.sharding.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.leon.sharding.demo.dao")
public class SpringbootShardingsphereApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootShardingsphereApplication.class, args);
    }

}
