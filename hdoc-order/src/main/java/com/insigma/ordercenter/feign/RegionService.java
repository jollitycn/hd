package com.insigma.ordercenter.feign;

import com.insigma.ordercenter.base.Result;
import com.insigma.ordercenter.entity.SysRegion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;

@FeignClient("hdoc-system")
public interface RegionService {

    @GetMapping("/system/sys-region/{id}")
    public SysRegion detail(@PathVariable(value ="id") Serializable id);

    @GetMapping("/system/sys-region/name")
    public SysRegion name(@RequestParam(value = "name") String name) ;
}
