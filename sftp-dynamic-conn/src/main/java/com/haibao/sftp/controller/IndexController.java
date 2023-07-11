package com.haibao.sftp.controller;

import com.haibao.sftp.pojo.ParmsQuery;
import com.haibao.sftp.pool.aspect.SftpAnnotation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 *
 * @author ml.c
 * @date 3:09 PM 4/19/21
 **/
@RestController
@RequestMapping(value = "/")
public class IndexController {

    @SftpAnnotation
    @PostMapping(value = "/test")
    public ResponseEntity test(@RequestBody ParmsQuery parmsQuery) {

        System.out.println("进入方法...");
        return ResponseEntity.ok("进入方法");
    }
}
