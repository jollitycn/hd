package com.insigma.ordercenter.scheduler;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.insigma.ordercenter.service.BaseServices;
import lombok.extern.log4j.Log4j2;


/**
 * 定时任务-根据系统发货单创建物流快递单
 */
@Log4j2
public class CreateLogisticsJob extends BaseServices implements SimpleJob {


    @Override
    public void execute(ShardingContext shardingContext) {

        orderFeignService.createLogisticsJob();
    }
}
