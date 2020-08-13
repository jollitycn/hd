package com.insigma.ordercenter.scheduler;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.insigma.ordercenter.service.BaseServices;

/**
 * @author youwk
 * @ClassName OrderShippingJob
 * @description TODO
 * @date 2020/8/11 18:12
 * @Version 1.0
 */
public class OrderShippingJob extends BaseServices implements SimpleJob {
    @Override
    public void execute(ShardingContext shardingContext) {
        orderFeignService.shippingOrderDeal();
    }
}
