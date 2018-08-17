package com.example.dubbo.test;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: zjx
 * @Description:
 * @Date: Created on 2018/08/13
 */
@SpringBootApplication
@DubboComponentScan(basePackages = "alibaba.dubbo.api")
public class DubboConsumserMain {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(DubboConsumserMain.class);
        app.run(args);
    }
}
