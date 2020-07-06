package com.insigma.ordercenter.config;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局每天制卡统计单例
 *
 * @author Pan Juncai
 * @version 1.0
 * @date 2020/5/13 13:32
 */
public class CountCreateCardMap {

    private static CountCreateCardMap countCreateCardMap = new CountCreateCardMap();

    private Map<String, Long> map;

    private CountCreateCardMap(){
        this.map = new HashMap<>();
    }

    /**
     * 获取单例
     *
     * @return com.insi.hd.wsmanager.config.CountCreateCardMap
     * @author  Pan Juncai
     * @date 2020/5/13 13:43
     */
    public synchronized static CountCreateCardMap getInstance() {
        if (countCreateCardMap == null) {
            countCreateCardMap = new CountCreateCardMap();
        }
        return countCreateCardMap;
    }

    /**
     *  获取今天产卡计数
     *
     * @param localDateStr  当前日期
     * @return java.long.Long
     * @author  Pan Juncai
     * @date 2020/5/13 13:54
     */
    public Long getTodayCount(String localDateStr){
        Long count = map.get(localDateStr);
        if (null == count) {
            return 0L;
        }
        return count;
    }

    /**
     * 设置今天产卡数量
     *
     * @param localDateStr 当前日期
     * @param count 最新统计数量
     * @author  Pan Juncai
     * @date 2020/5/13 13:54
     */
    public void setTodayCount(String localDateStr, Long count){
        map.put(localDateStr, count);
    }

}
