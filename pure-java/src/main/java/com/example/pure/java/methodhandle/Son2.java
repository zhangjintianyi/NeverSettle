package com.example.pure.java.methodhandle;

import java.util.List;
import java.util.Random;

/**
 * @Author: zjx
 * @Description:
 * @Date: Created on 2018/08/13
 */
public class Son2 implements Father{
    @Override
    public String sayHello() {
        return "say hello from son No.2";
    }

    @Override
    public String createName(List<Character> characters) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            sb.append(characters.get(random.nextInt(characters.size())));
        }

        return sb.toString();
    }
}