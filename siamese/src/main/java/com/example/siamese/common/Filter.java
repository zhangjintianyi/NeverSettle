package com.example.siamese.common;

/**
 * @Author: zjx
 * @Description:
 * @Date: Created on 2018/08/14
 */
public interface Filter {
    Result invoke(Invoker<?> invoker, Invocation invocation);
}
