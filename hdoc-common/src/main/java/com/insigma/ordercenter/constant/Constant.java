package com.insigma.ordercenter.constant;

/**
 * @author ：Jason
 * @date ：Created in 2020/1/10
 */
public interface Constant {

    int SYS_ZERO = 0;
    int SYS_ONE = 1;
    int SYS_TWO = 2;
    int SYS_THREE = 3;
    int SYS_FOUR =4;
    int SYS_FIVE =5;
    int SYS_SIX =6;
    int SYS_EIGHT =8;
    int SYS_NEGATIVE_ONE = -1;

    String PERCENT = "%";
    String SLASH = "/";
    String COLON = ":";
    String SYS_ZERO_STR = "0";
    String SERVER_IP = "http://100.168.1.158:8081";
    String TOKEN = "token";
    /**
     * 系统通用的常量
     *
     * @author Pan Juncai
     * @version 1.0
     * @date 2019/8/26 18:00
     */
    final class Sys {
        /**
         * session中图形验证码标识
         */
        public static final String RAND_IMAGE_VALIDATE_CODE = "randImageValidateCode";

        /**
         * 日期格式化
         */
        public static final String LOCALDATETIME_FORMATTER = "yyyy-MM-dd HH:mm:ss";
        
        /**
         * session中用户标识
         */
        public static final String LOGIN_USER_CODE = "loginUser";

    }

    /**
     * sfa通用的常量
     *
     * @author Pan Juncai
     * @version 1.0
     * @date 2020/3/20 17:07
     */
    final class Sfa {
        /**
         * 策略计算基数
         */
        public static final int STRATEGY_BASE = 100;

        /**
         * 策略计算基数
         */
        public static final String STRATEGY_NAME = "买满100元送1桶";

        /**
         * 每天最大产卡数
         */
        public static final int MAX_CREATE_CARD_COUNT = 9999999;
    }

    final class Product {
        /**
         * 默认兑换商品的商品编号
         */
        public static final String PRODUCT_NUMBER = "exchange_goods_001";
    }

}
