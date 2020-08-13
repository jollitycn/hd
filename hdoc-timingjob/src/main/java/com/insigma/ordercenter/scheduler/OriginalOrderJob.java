package com.insigma.ordercenter.scheduler;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.insigma.ordercenter.service.BaseServices;
import com.insigma.ordercenter.service.OrderFeignService;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author youwk
 * @ClassName OriginalOrderJob
 * @description TODO
 * @date 2020/8/11 17:04
 * @Version 1.0
 */
@Slf4j
public class OriginalOrderJob extends BaseServices implements SimpleJob {

    @Override
    public void execute(ShardingContext shardingContext) {
        orderFeignService.originalOrderDeal();
    }
}
