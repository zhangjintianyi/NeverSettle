package com.example.siamese.config;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * @Author: zjx
 * @Description:
 * @Date: Created on 2018/08/14
 */

@Builder
@Setter
@Getter
public class ExportedServiceInfo {

    private String interfaceClass;

    private String implClassName;

    private Set<ExportedMethodInfo> exportedMethodInfos;

    private double version;


    @Builder
    @Setter
    @Getter
    public static class ExportedMethodInfo {

        private String methodName;

        Class<?>[] argumentClasses;
    }
}
