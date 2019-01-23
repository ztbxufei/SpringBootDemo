package com.springboot.jingfei.SpringBoot.springAop;

import com.springboot.jingfei.SpringBoot.annotation.SysLog;
import com.springboot.jingfei.SpringBoot.utils.StringUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 环绕通知类加注解整合
 *
 * @Author jingfei
 * @Date 2018-10-18 16:52
 */

@Component
@Aspect
public class SpringAop {

    private static final Logger logger = Logger.getLogger(SpringAop.class);

    /**
     * 定义切入点
     */
    @Pointcut("execution(* com.springboot.jingfei.SpringBoot.controller.*.*(..))")
    public void SpringAopScan(){

    }

    /**
     * 环绕通知
     * 既有前置通知又可以有后置通知
     * 还包括方法的执行，参数的返回
     * @param joinPoint 切点
     * @return
     */
    @Around("SpringAopScan()")
    public Object SpringAopAround(JoinPoint joinPoint){
        String methodName = "";
        String className = "";
        Signature signature = joinPoint.getSignature();
        Class clazz = joinPoint.getTarget().getClass();
        if(signature instanceof MethodSignature){
            methodName = ((MethodSignature) signature).getMethod().getName();
            className = clazz.getSimpleName();
        }
        Object result = null;
        try {
            logger.info(className + "的" + methodName + "方法开始时间: " + StringUtils.getCurrentTime());
            result = ((ProceedingJoinPoint) joinPoint).proceed();
            Method[] methods = clazz.getMethods();
            for(Method method : methods){
                if(method.getName().equals(methodName)){
                    SysLog sysLog = method.getDeclaredAnnotation(SysLog.class);
                    if(sysLog != null){
                        String name = sysLog.name();
                        String value = sysLog.value();
                        System.out.println("name : " + name + "\tvalue: " + value);
                        break;
                    }
                }
            }
            logger.info(className + "的" + methodName + "方法结束时间: " + StringUtils.getCurrentTime());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return result;
    }
}
