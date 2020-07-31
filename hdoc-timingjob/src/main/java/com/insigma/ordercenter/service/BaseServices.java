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


    protected static PersonnelFeignService personnelFeignService;


    static {
        personnelFeignService = SpringContextUtils.getBean(PersonnelFeignService.class);
    }

}
