package com.example.siamese.spring;

import com.example.siamese.annotation.MyComponentScan;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @Author: zjx
 * @Description:
 * @Date: Created on 2018/08/15
 */
public class SiameseComponentScanRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        // 获取要扫描的包
        Set<String> packagesToScan = getPackagesToScan(importingClassMetadata);
        // 注册Service端的扫描注册bean
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(ServiceAnnotationBeanPostProcessor.class);
        builder.addConstructorArgValue(packagesToScan.toArray());
        builder.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
        registry.registerBeanDefinition("serviceAnnotationBeanPostProcessor", builder.getBeanDefinition());
        // 注册Reference端的扫描注册bean

    }

    /**
     * 获取包扫描包，顺便去重
     * @param metadata
     * @return
     */
    private Set<String> getPackagesToScan(AnnotationMetadata metadata) {
        AnnotationAttributes attributes = AnnotationAttributes.fromMap(
                metadata.getAnnotationAttributes(MyComponentScan.class.getName()));
        String[] basePackages = attributes.getStringArray("basePackages");
        // Appends value array attributes

        return new LinkedHashSet<>(Arrays.asList(basePackages));
    }
}
