package com.insigma.ordercenter.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("hdoc-order")
public interface OrderFeignService {

    /**
     * 根据系统发货单创建物流快递单
     */
    @GetMapping("/order/shipping-order/createLogisticsJob")
    void createLogisticsJob();

}
