package com.leon.micro.dubbo.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.leon.micro.dubbo.provider.service.IHelloService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {


//    @Reference(url = "dubbo://127.0.0.1:20880")
    @Reference(version="1.0.0")
    private IHelloService iHelloService;


    @RequestMapping("/")
    public String printMessage(@RequestParam(defaultValue = "leon") String name) {
        return iHelloService.sayHi(name);
    }

}
