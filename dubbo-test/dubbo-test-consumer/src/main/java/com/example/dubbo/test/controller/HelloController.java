package com.example.dubbo.test.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.example.dubbo.test.IHelloWorldService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @Author: zjx
 * @Description:
 * @Date: Created on 2018/08/13
 */
@RestController
public class HelloController {
    private static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();

    private static char[] chars = {'a', 'b', 'c', 'd', 'e'};

    @Reference
    private IHelloWorldService helloWorldService;

    @GetMapping("/hello")
    public String hello() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            sb.append(chars[RANDOM.nextInt(chars.length)]);
        }

        return helloWorldService.sayHello(sb.toString());
    }

}
