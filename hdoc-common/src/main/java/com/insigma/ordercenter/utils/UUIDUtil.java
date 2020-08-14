package com.insigma.ordercenter.utils;


import cn.hutool.core.util.RandomUtil;

import java.time.LocalDateTime;
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

    public static String getSerializeNo(String code) {
        String timestamp = DateUtils.formatLocalDateTimeToString(LocalDateTime.now(), DateUtils.TIME_PATTERN_MILLISECOND);
        String randomNum = String.valueOf(RandomUtil.randomInt(999));

        if (randomNum.length() == 1) {
            randomNum = "00" + randomNum;
        } else if (randomNum.length() == 2) {
            randomNum = "0" + randomNum;
        }
        String serialNo = code + timestamp + randomNum;
        return serialNo;
    }


}
