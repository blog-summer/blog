package com.best.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 记录日志内容：
 * 请求url
 * 访问者ip
 * 调用方法classMethod
 * 参数args
 * 返回内容
 */
@Aspect
@Component
public class LogAspect {
    //日志记录器
    private final Logger logger= LoggerFactory.getLogger(this.getClass());
    /**
     * 注解，规定切面拦截哪个类
     * 拦截web下所有
     */
    @Pointcut("execution(* com.best.web.*.*(..))")
    public void log(){}

    @Before("log()")
    //请求方法之前执行
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=attributes.getRequest();

        String url=request.getRequestURL().toString();
        String ip=request.getRemoteAddr();
        String classMethod=joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName();
        Object[] args=joinPoint.getArgs();
        RequestLog requestLog=new RequestLog(url,ip,classMethod,args);
        logger.info("Request : {}",requestLog);
        //logger.info("------------doBefore---------");
    }

    @After("log()")
    public void doAfter(){
        //logger.info("------------doAfter---------");
    }

    //方法执行完，返回的时候，拦截
    @AfterReturning(returning = "result",pointcut = "log()")
    public void  doAfterReturn(Object result){
        logger.info("Result : {}" + result);
    }

    private class RequestLog{
        //aop request获取这两个
        private String url;
        private String ip;
        //doBefore可获取下两个
        private String classMethod;
        private Object[] args;

        public RequestLog(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "RequestLog{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }

}
