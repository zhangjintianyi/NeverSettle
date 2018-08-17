package com.example.siamese.spring;

import com.example.siamese.annotation.MyComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: zjx
 * @Description:
 * @Date: Created on 2018/08/15
 */
@Configuration
@MyComponentScan(basePackages = {"com.example.siamese.spring"})
public class SiameseConfig {
}
