package com.example.siamese.annotation;

import java.lang.annotation.*;

/**
 * @Author: zjx
 * @Description:
 * @Date: Created on 2018/08/14
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Inherited
public @interface Reference {
}
