package com.haibao.sftp.pool.aspect;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * @Author ml.c
 * @Description //自定义 控制层 注解
 * @Date 11:56 AM 1/14/21
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SftpAnnotation {

}
