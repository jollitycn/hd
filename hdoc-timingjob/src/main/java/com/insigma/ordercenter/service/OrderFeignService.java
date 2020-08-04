package com.insigma.ordercenter.service;


import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("hdoc-order")
public interface OrderFeignService {

    @ApiOperation(value = "获取年假发放规则")
    @GetMapping("/personnel/getAnnualLeaveRule")
    void getAnnualLeaveRule();

}
