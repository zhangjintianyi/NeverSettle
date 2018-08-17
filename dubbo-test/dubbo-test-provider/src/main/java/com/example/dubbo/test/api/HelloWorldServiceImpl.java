package com.example.dubbo.test.api;


import com.alibaba.dubbo.config.annotation.Service;
import com.example.dubbo.test.IHelloWorldService;


/**
 * @Author: zjx
 * @Description:
 * @Date: Created on 2018/08/13
 */
@Service
public class HelloWorldServiceImpl implements IHelloWorldService {
    @Override
    public String sayHello(String name) {
        return "hello " + name + "!!!";
    }
}
