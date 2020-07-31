package com.insigma.ordercenter.config;

import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import com.insigma.ordercenter.scheduler.AnnualLeaveJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: demo
 * @description:
 * @author: XuChao
 * @create: 2020-07-08 15:33
 **/
@Configuration
public class MyJobConfig {

//    private final String cron = "0/5 * * * * ?";
//    private final int shardingTotalCount = 3;
//    private final String shardingItemParameters = "0=A,1=B,2=C";
//    private final String jobParameters = "parameter";

    @Autowired
    private ZookeeperRegistryCenter regCenter;

//    @Resource
//    private JobEventConfiguration jobEventConfiguration;

//    @Bean
//    @Qualifier(value = "aaaa")
//    public SimpleJob stockJob() {
//        return new MySimpleJob();
//    }



    /**
     * 年假发放(每个月查询年假发放规则（每月1号是否自动计算本年可休年假）)
     * @param cron
     * @param shardingTotalCount
     * @param shardingItemParameters
     * @return
     */
    @Bean(initMethod = "init")
    public JobScheduler AnnualLeaveJob(
            @Value("${AnnualLeaveJob.cron}") final String cron,
            @Value("${AnnualLeaveJob.shardingTotalCount}") final int shardingTotalCount,
            @Value("${AnnualLeaveJob.shardingItemParameters}") final String shardingItemParameters) {

        return new SpringJobScheduler(new AnnualLeaveJob(), regCenter, getLiteJobConfiguration(AnnualLeaveJob.class,
                cron, shardingTotalCount, shardingItemParameters, "TEST！！！"));
    }


    private LiteJobConfiguration getLiteJobConfiguration(final Class<? extends SimpleJob> jobClass,
                                                         final String cron,
                                                         final int shardingTotalCount,
                                                         final String shardingItemParameters,
                                                         final String jobParameters) {
        // 定义作业核心配置
        JobCoreConfiguration simpleCoreConfig = JobCoreConfiguration.newBuilder(jobClass.getName(), cron, shardingTotalCount).
                shardingItemParameters(shardingItemParameters).jobParameter(jobParameters).build();
        // 定义SIMPLE类型配置
        SimpleJobConfiguration simpleJobConfig = new SimpleJobConfiguration(simpleCoreConfig, jobClass.getCanonicalName());
        // 定义Lite作业根配置
        LiteJobConfiguration simpleJobRootConfig = LiteJobConfiguration.newBuilder(simpleJobConfig).overwrite(true).build();
        return simpleJobRootConfig;

    }
}
