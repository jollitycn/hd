package com.insigma.ordercenter.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局公共常量
 *
 * @author zlt
 * @date 2018/10/29
 */
public interface CommonConstant {

    /**
     * 新增表默认字段（创建时间，关联用户，更新时间）
     */
    String[] DEFAULT_ADD_COLUMN = new String[]{"create_time","user_id","update_time"};

    HashMap<Integer,String> datatypeMap = new HashMap<Integer,String>(){
        {
            put(0,"int");
            put(1,"smallint");
            put(2,"bigint");
            put(3,"varchar(255)");
            put(4,"text");
            put(5,"date");
            put(6,"datetime");
            put(7,"double");
        }
    };

}
