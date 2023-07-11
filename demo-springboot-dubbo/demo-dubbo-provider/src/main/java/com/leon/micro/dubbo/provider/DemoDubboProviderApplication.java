package com.leon.micro.dubbo.provider;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubboConfiguration  //表示要开启dubbo功能
public class DemoDubboProviderApplication {

    public static void main(String[] args) {

        SpringApplication.run(DemoDubboProviderApplication.class, args);
    }

}
