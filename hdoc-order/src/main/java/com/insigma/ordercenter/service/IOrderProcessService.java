package com.insigma.ordercenter.service;

import com.insigma.ordercenter.base.Result;
import com.insigma.ordercenter.entity.OriginalOrder;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author youwk
 * @description 订单处理流程
 * @date 15:21 2020/7/30
 * @param
 * @return
**/
public interface IOrderProcessService {

    /**
     *
     * @param originalOrderList 原始订单列表
     * @return Map 手机号对应需要合成的订单编号集合
     */
    Map<String, Set<Long>> combinedOriginOrder(List<OriginalOrder> originalOrderList);

//    Result<?>()

}
