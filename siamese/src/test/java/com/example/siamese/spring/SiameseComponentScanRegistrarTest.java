package com.example.siamese.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @Author: zjx
 * @Description:
 * @Date: Created on 2018/08/15
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={SiameseConfig.class})
public class SiameseComponentScanRegistrarTest {
    @Test
    public void registerBeanDefinitions() throws Exception {
    }

}