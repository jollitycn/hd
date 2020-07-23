//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.insigma.ordercenter.logistics.sf.oms;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class HmacSha512CoderFactory {
    private static Map<String, HmacSha512Coder> hmacSha512CoderMap = new ConcurrentHashMap();

    private HmacSha512CoderFactory() {
    }

    public static HmacSha512Coder getAESCBCCoderObject(String keySeeds) {
        if (!hmacSha512CoderMap.containsKey(keySeeds)) {
            HmacSha512Coder hmacSha512Coder = new HmacSha512Coder();
            hmacSha512Coder.setKeySeeds(keySeeds);
            hmacSha512CoderMap.put(keySeeds, hmacSha512Coder);
        }

        return (HmacSha512Coder)hmacSha512CoderMap.get(keySeeds);
    }

    public static String getHmacSha512Coder(String keySeeds, String data) {
        if (!hmacSha512CoderMap.containsKey(keySeeds)) {
            HmacSha512Coder hmacSha512Coder = new HmacSha512Coder();
            hmacSha512Coder.setKeySeeds(keySeeds);
            hmacSha512CoderMap.put(keySeeds, hmacSha512Coder);
        }

        try {
            String hdata = ((HmacSha512Coder)hmacSha512CoderMap.get(keySeeds)).generateHMAC(data);
            return hdata;
        } catch (Exception var3) {
            throw new EncryptionRuntimeException(var3);
        }
    }

    public static void main(String[] args) {
        String hmacsha512 = getUUID();
        String rs = getHmacSha512Coder(hmacsha512, "ddddddddd");
        System.out.println(rs);
    }

    private static String getUUID() {
        return UUID.randomUUID().toString().trim().replaceAll("-", "");
    }
}
