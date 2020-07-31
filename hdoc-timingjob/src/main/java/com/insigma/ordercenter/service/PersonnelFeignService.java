package com.insigma.ordercenter.service;


import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("personnel-service")
public interface PersonnelFeignService {

//    @ApiOperation(value = "获取年假发放规则")
//    @GetMapping("/personnel/getAnnualLeaveRule")
//    void getAnnualLeaveRule();

}
