package com.insigma.ordercenter.service;

import com.insigma.ordercenter.utils.SpringContextUtils;

/**
 * <p></p>
 *
 * @author chenyf
 * @className BaseServices
 * @since 2020/7/16 17:33
 */
public class BaseServices {


    protected static OrderFeignService orderFeignService;


    static {
        orderFeignService = SpringContextUtils.getBean(OrderFeignService.class);
    }

}
