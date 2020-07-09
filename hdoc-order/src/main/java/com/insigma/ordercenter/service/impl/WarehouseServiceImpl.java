package com.insigma.ordercenter.service.impl;

import com.insigma.ordercenter.base.CodeMsg;
import com.insigma.ordercenter.base.Result;
import com.insigma.ordercenter.entity.*;
import com.insigma.ordercenter.mapper.WarehouseMapper;
import com.insigma.ordercenter.service.IWarehouseManagerService;
import com.insigma.ordercenter.service.IWarehouseRegionService;
import com.insigma.ordercenter.service.IWarehouseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * <p>
 * 仓库表 服务实现类
 * </p>
 *
 * @author LiuHao
 * @since 2020-07-08
 */
@Service
public class WarehouseServiceImpl extends ServiceImpl<WarehouseMapper, Warehouse> implements IWarehouseService {

    @Autowired
    private IWarehouseManagerService managerService;

    @Autowired
    private IWarehouseRegionService regionService;

    @Override
    @Transactional
    public Result<?> addWarehouse(WarehouseVo wareHouseVo, LoginUser redisUser) {
        Warehouse warehouse = new Warehouse();
        BeanUtils.copyProperties(wareHouseVo,warehouse);
        warehouse.setCreateId(redisUser.getUserId());
        warehouse.setCreateTime(LocalDateTime.now());
        boolean res = this.save(warehouse);
        Integer[] regionIds = wareHouseVo.getRegionIds();
        for (Integer regionId : regionIds) {
            WarehouseRegion region = new WarehouseRegion();
            region.setWarehouseId(warehouse.getWarehouseId());
            region.setRegionId(regionId);
            regionService.save(region);
        }
        WarehouseManager[] managers = wareHouseVo.getManagers();

        for (WarehouseManager manager : managers) {
            WarehouseManager wm = new WarehouseManager();
            wm.setUserId(manager.getUserId());
            wm.setUserName(manager.getUserName());
            wm.setWarehouseId(warehouse.getWarehouseId());
            managerService.save(wm);
        }

        if (res) {
            return Result.success(CodeMsg.SUCCESS);
        }
        return Result.error(CodeMsg.DATA_INSERT_ERROR);
    }
}
