package com.example.siamese.api;

import com.example.siamese.IHelloWorldService;
import com.example.siamese.annotation.Service;

/**
 * @Author: zjx
 * @Description:
 * @Date: Created on 2018/08/13
 */
@Service(interfaceClass = IHelloWorldService.class)
public class HelloWorldServiceImpl implements IHelloWorldService {
    @Override
    public String sayHello(String name) {
        return "hello " + name + "!!!";
    }
}
