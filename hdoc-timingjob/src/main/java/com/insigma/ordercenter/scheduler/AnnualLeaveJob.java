package com.insigma.ordercenter.scheduler;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.insigma.ordercenter.service.BaseServices;
import lombok.extern.log4j.Log4j2;


@Log4j2
public class AnnualLeaveJob extends BaseServices implements SimpleJob {


    @Override
    public void execute(ShardingContext shardingContext) {

        System.out.println("定时任务测试！！！");
        System.out.println(shardingContext.getJobName());
        System.out.println(shardingContext.getShardingItem());
        System.out.println(shardingContext.getShardingTotalCount());
        System.out.println(shardingContext.getJobParameter());
    }
}
