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
public class MethodHandleTest {
    public static int doubleVal(int val) {
        return val * 2;
    }

    private int tripleVal(int val) {
        return val * 3;
    }

    public static List<Integer> transform(List<Integer> dataList, MethodHandle handle) throws Throwable {
        for (int i = 0; i < dataList.size(); i++) {
            dataList.set(i, (Integer) handle.invoke(dataList.get(i)));
        }
        return dataList;
    }

    public static void main(String[] args) {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        try {
            MethodHandle methodHandle = lookup.findSpecial(MethodHandleTest.class, "tripleVal", MethodType.methodType(int.class, int.class), MethodHandleTest.class);
            List<Integer> dataList = Arrays.asList(1, 2, 3, 4, 5);
            dataList = MethodHandleTest.transform(dataList, methodHandle);
            for (Integer data : dataList) {
                System.out.println(data);
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
