package com.haibao.sftp.pool.aspect;

import cn.hutool.core.util.StrUtil;
import com.haibao.sftp.pojo.ParmsQuery;
import com.haibao.sftp.pool.service.SFTPService;
import com.haibao.sftp.utils.GsonUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/*
 * @Author ml.c
 * @Description //配置图片调用切面，不改变原有逻辑
 * @Date 11:55 AM 1/14/21
 **/
@Aspect
@Component
public class SftpAspect {

    @Autowired
    SFTPService sftpService;

    /**
     * slf4j日志
     */
    private final static Logger logger = LoggerFactory.getLogger(SftpAspect.class);

    /**
     * 切点
     */
    @Pointcut("execution(public * com.haibao.sftp.controller.IndexController.*(..)) && @annotation(com.haibao.sftp.pool.aspect.SftpAnnotation)" )
    public void addAdvice(){}

    /**
     * 环绕通知
     */
    @Around(value = "addAdvice()")
    public Object arround(ProceedingJoinPoint pjp) {

        System.out.println("进入切面...");

        Object result = null;

        Object[] args = pjp.getArgs();

        if(args != null && args.length >0) {

            ParmsQuery parmsQuery = null;
            try {
                parmsQuery = (ParmsQuery)args[0];
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(null == parmsQuery || StrUtil.isEmpty(parmsQuery.getPath())){
                return ResponseEntity.status(400).body("缺少必要参数");
            }

            ResponseEntity responseEntity =  sftpService.getSftpPicStream(parmsQuery);

            if(HttpStatus.OK == responseEntity.getStatusCode()){
                 return responseEntity;
            }
            //继续放行
        }
        try {
            logger.info("1、Around：方法环绕开始.....");
            result =  pjp.proceed();
            logger.info("3、Around：方法环绕结束，结果是 :" + result);
            return result;
        } catch (Throwable e) {
            logger.error(pjp.getSignature() + " 出现异常： ", e);
            return ResponseEntity.status(500).body("出现异常：" + e.getMessage());
        }
    }

    /**
     * 方法执行前
     */
    @Before(value = "addAdvice()")
    public void before(JoinPoint joinPoint){
////        logger.info("2、Before：方法执行开始...");
//        // 接收到请求，记录请求内容
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        assert attributes != null;
//        HttpServletRequest request = attributes.getRequest();
//        // 记录下请求内容
////        logger.info("URL : " + request.getRequestURL().toString());
////        logger.info("HTTP_METHOD : " + request.getMethod());
////        logger.info("IP : " + request.getRemoteAddr());
////        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
////        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));

    }

    /**
     * 方法执行结束，不管是抛出异常或者正常退出都会执行
     */
    @After(value = "addAdvice()")
    public void after(JoinPoint joinPoint){
//        logger.info("4、After：方法最后执行.....");
    }

    /**
     * 方法执行结束，增强处理
     */
    @AfterReturning(returning = "ret", pointcut = "addAdvice()")
    public void doAfterReturning(Object ret){
        // 处理完请求，返回内容
//        logger.info("5、AfterReturning：方法的返回值 : " + ret);
    }

    /**
     * 后置异常通知
     */
    @AfterThrowing(value = "addAdvice()")
    public void throwss(JoinPoint joinPoint){
//        logger.error("AfterThrowing：方法异常时执行.....");
    }


    public static void main(String[] args) {
        ParmsQuery parmsQuery = new ParmsQuery();
        parmsQuery.setPath("/Users/leon/Pictures/p2388225598.jpg");
        parmsQuery.setSftpHost("localhost");
        parmsQuery.setSftpPort(22);
        parmsQuery.setSftpUsername("leon");
        parmsQuery.setSftpPassword("hb1031");

        System.out.println(GsonUtils.gsonString(parmsQuery));
    }

}
