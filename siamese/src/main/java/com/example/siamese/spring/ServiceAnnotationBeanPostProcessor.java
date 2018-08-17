package com.example.siamese.spring;

import com.example.siamese.annotation.Service;
import com.example.siamese.config.ExportedServiceInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.env.Environment;
import org.springframework.util.ClassUtils;
import org.springframework.util.CollectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @Author: zjx
 * @Description:
 * @Date: Created on 2018/08/14
 */
@Slf4j
public class ServiceAnnotationBeanPostProcessor implements BeanDefinitionRegistryPostProcessor, EnvironmentAware {

    private final Set<String> packagesToScan;

    private Environment environment;

    public ServiceAnnotationBeanPostProcessor(String[] basePackages) {
        packagesToScan = new LinkedHashSet<>(basePackages.length);
        for (String basePackage : basePackages) {
            packagesToScan.add(basePackage.trim());
        }
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        if (!CollectionUtils.isEmpty(packagesToScan)) {
            registerServiceInfoBean(packagesToScan, registry);
        }
    }

    /**
     * TODO: 代码优化
     *
     * @param packagesToScan
     * @param registry
     */
    private void registerServiceInfoBean(Set<String> packagesToScan, BeanDefinitionRegistry registry) {
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(registry);
        if (!CollectionUtils.isEmpty(packagesToScan)) {
            for (String packagePath : packagesToScan) {
                int scanNum = scanner.scan(packagePath);
                Set<BeanDefinition> candidateComponents = scanner.findCandidateComponents(packagePath);
                if (!CollectionUtils.isEmpty(candidateComponents)) {
                    for (BeanDefinition candidateComponent : candidateComponents) {
                        try {
                            Class<?> clazz = ClassUtils.forName(candidateComponent.getBeanClassName(), Thread.currentThread().getContextClassLoader());
                            if (clazz.isAnnotationPresent(Service.class)) {
                                Annotation[] annotations = clazz.getAnnotationsByType(Service.class);
                                if (annotations != null && annotations.length == 1) {
                                    Service serviceAnnotation = (Service) annotations[0];
                                    Method[] methods = clazz.getMethods();
                                    Set<ExportedServiceInfo.ExportedMethodInfo> exportedMethodInfos = new HashSet<>(methods.length);
                                    for (Method method : methods) {
                                        if (method.getDeclaringClass().equals(clazz)) {
                                            ExportedServiceInfo.ExportedMethodInfo methodInfo = ExportedServiceInfo.ExportedMethodInfo.builder().methodName(method.getName())
                                                    .argumentClasses(method.getParameterTypes())
                                                    .build();
                                            exportedMethodInfos.add(methodInfo);
                                        }
                                    }
                                    AbstractBeanDefinition serviceBeanDefinition =
                                            buildServiceBeanInfoDefinition(serviceAnnotation, candidateComponent.getBeanClassName());
                                    registry.registerBeanDefinition(ExportedServiceInfo.class.getSimpleName() + "." + clazz.getName(), serviceBeanDefinition);

                                } else {
                                    log.error("you can not have @Service greater than 1");
                                }

                            }
                        } catch (ClassNotFoundException e) {
                            log.error(e.getMessage());
                        }
                    }
                }
            }
        }
    }

    /**
     * 构建ServiceInfo BeanDefinition
     *
     * @return
     */
    private AbstractBeanDefinition buildServiceBeanInfoDefinition(Service serviceAnnotation, String beanName) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(ExportedServiceInfo.class);

        return builder.addPropertyReference("interfaceClass", environment.resolvePlaceholders(serviceAnnotation.interfaceClass().getName()))
                .addPropertyReference("implClassName", environment.resolvePlaceholders(beanName))
                .addPropertyValue("version", serviceAnnotation.version()).getBeanDefinition();
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
