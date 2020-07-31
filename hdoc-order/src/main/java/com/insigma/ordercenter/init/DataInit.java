package com.insigma.ordercenter.init;

import com.insigma.ordercenter.entity.Strategy;
import com.insigma.ordercenter.service.IStrategyService;
import com.insigma.ordercenter.utils.JsonUtil;
import com.insigma.ordercenter.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.spring.web.json.Json;

import java.util.List;

/**
 * @author youwk
 * @ClassName 数据初始化
 * @description TODO
 * @date 2020/7/30 11:11
 * @Version 1.0
 */
@Configuration
@Slf4j
public class DataInit implements InitializingBean {

    @Autowired
    private IStrategyService strategyService;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("策略正在初始化....");
        List<Strategy> list = strategyService.list();
        redisUtil.set("strategyList", list);
    }
}
