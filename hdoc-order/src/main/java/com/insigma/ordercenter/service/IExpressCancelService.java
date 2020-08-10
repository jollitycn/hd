package com.insigma.ordercenter.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.insigma.ordercenter.base.Result;

public interface IExpressCancelService {

    /**
     *
     * @param shippingOrderId 发货单ID
     * @param logisticsType 1顺丰速运 2百世汇通 3宅急送 4京东
     * @return
     */
    Result cancelLogistics(Long shippingOrderId, int logisticsType) throws Exception;

}
