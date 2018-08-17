package com.example.siamese.annotation;

import com.example.siamese.spring.SiameseComponentScanRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Author: zjx
 * @Description:
 * @Date: Created on 2018/08/14
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Import(SiameseComponentScanRegistrar.class)
public @interface MyComponentScan {
    String[] basePackages() default {};
}
