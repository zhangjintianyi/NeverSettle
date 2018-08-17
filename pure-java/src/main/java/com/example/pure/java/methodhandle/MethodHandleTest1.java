package com.example.pure.java.methodhandle;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: zjx
 * @Description:
 * @Date: Created on 2018/08/13
 */
public class MethodHandleTest1 {

    public static MethodHandle createMethodHandle(Class<?> clazz, Object obj) {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        try {
//            return lookup.findVirtual(clazz, "sayHello", MethodType.methodType(String.class)).bindTo(obj);
            return lookup.findVirtual(clazz, "createName", MethodType.methodType(String.class, List.class)).bindTo(obj);
        } catch (NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        Father son = new Son1();
        MethodHandle handle = MethodHandleTest1.createMethodHandle(Father.class, son);
        if (handle != null) {
            try {
                List<Character> characters = Arrays.asList('a', 'b', 'c');
                String greeting = (String) handle.invoke(characters);
                System.out.println(greeting);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }
}
