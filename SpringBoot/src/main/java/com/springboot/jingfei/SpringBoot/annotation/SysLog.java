package com.springboot.jingfei.SpringBoot.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {
    String name() default "";
    String value() default "";
}
