package com.insigma.ordercenter.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.insigma.ordercenter.base.Result;
import com.insigma.ordercenter.entity.*;
import com.baomidou.mybatisplus.extension.service.IService;
import com.insigma.ordercenter.entity.dto.WarehouseDTO;
import com.insigma.ordercenter.entity.dto.WarehouseProductDTO;
import com.insigma.ordercenter.entity.dto.WarehouseProductPageQuery;
import com.insigma.ordercenter.entity.vo.WarehouseVo;
import com.insigma.ordercenter.service.impl.MyException;

import java.io.Serializable;

/**
 * <p>
 * 仓库表 服务类
 * </p>
 *
 * @author Jason
 * @since 2020-07-08
 */
public interface IWarehouseService extends IService<Warehouse> {

    Result<?> addWarehouse(WarehouseDTO wareHouseDTO, LoginUser redisUser) throws MyException;

    Result<?> updateWarehouse(WarehouseDTO warehouseDTO, LoginUser loginUser) throws MyException;

    Result<?> addProduct(WarehouseProductDTO req, Long userId) throws MyException;

    Result<?> modifyStock(WarehouseProductRelation whp, LoginUser redisUser);

    Result<?> deleteWarehouse(Serializable warehouseId);

    IPage listProducts(WarehouseProductPageQuery req);

    Result<?> page(IPage<WarehouseVo> page, WarehouseDTO dto);

    void removeProduct(String warehouseId, String productId, Long userId);

    void updateWarningCount(String warehouseId, String productId, String warningCount);

    int getWarningCount(String warehouseId, String productId);

    void removeProduct(Integer warehouseId, Integer productId, Long userId);

    void updateWarningCount(Integer warehouseId, Integer productId, Integer warningCount);

    int getWarningCount(Integer warehouseId, Integer productId);

}
