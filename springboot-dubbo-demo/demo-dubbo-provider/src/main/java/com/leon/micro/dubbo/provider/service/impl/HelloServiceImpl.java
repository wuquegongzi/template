package com.leon.micro.dubbo.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.leon.micro.dubbo.provider.service.IHelloService;

@org.springframework.stereotype.Service
@Service(interfaceClass = IHelloService.class,version="1.0.0")
public class HelloServiceImpl implements IHelloService {

    @Override
    public String sayHi(String name) {
        return "hello "+name;
    }
}
