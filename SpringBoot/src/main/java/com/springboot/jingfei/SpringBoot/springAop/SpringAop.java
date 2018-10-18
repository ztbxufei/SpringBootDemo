package com.springboot.jingfei.SpringBoot.springAop;

import com.springboot.jingfei.SpringBoot.annotation.SysLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
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
        Object result = null;
        try {
            result = ((ProceedingJoinPoint) joinPoint).proceed();
            Class clazz = joinPoint.getTarget().getClass();
            String methodName = joinPoint.getSignature().getName();
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
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return result;
    }
}
