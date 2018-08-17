package com.example.siamese.api;

import com.example.siamese.IHelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: zjx
 * @Description:
 * @Date: Created on 2018/08/15
 */
public class ConsumserConfig {
    @Autowired
    private IHelloWorldService helloWorldService;
}
