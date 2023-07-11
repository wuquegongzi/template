package com.leon.common.execption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author minglei.chen
 * @description //全局异常处理
 * @date 6:15 PM 3/9/21
 **/
@ControllerAdvice
public class GlobalExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = {Exception.class, NullPointerException.class})//这里可以写多个异常
    public ResponseEntity exceptionHandler(HttpServletRequest request, Exception e, HttpServletResponse response) {
        logger.error(request.getServletPath(), response.getStatus(), e.getMessage());
        int status = response.getStatus();
        return ResponseEntity.status(status).body(e.getMessage());
    }

    @ExceptionHandler(value = {CustomerException.class})
    public ResponseEntity customerExceptionHandler(HttpServletRequest request, Exception e, HttpServletResponse response) {
        logger.error(request.getServletPath(), response.getStatus(), e.getMessage());
        int status = response.getStatus();
        return ResponseEntity.status(status).body(e.getMessage());
    }

}
