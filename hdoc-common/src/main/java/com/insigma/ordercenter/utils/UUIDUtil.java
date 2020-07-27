package com.insigma.ordercenter.utils;

import java.util.UUID;

/**
 * @author ：Jason
 * @date ：Created in 2020/1/14
 */
public class UUIDUtil {

    public static String getUUID() {
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        return uuid;
    }

    public static void main(String[] args) {
        System.out.println(getUUID());
    }
}
