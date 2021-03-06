package com.example.siamese;

import com.example.siamese.annotation.MyComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: zjx
 * @Description:
 * @Date: Created on 2018/08/13
 */
@SpringBootApplication
@MyComponentScan(basePackages = "com.example.siamese.api")
@ComponentScan("com.example.siamese")
public class SiameseConsumerMain {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SiameseConsumerMain.class);
        app.run(args);
    }
}
