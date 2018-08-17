package com.example.siamese.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @Author: zjx
 * @Description:
 * @Date: Created on 2018/08/14
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Component
public @interface Service {
    Class<?> interfaceClass() default void.class;

    double version() default 1.0;
}
