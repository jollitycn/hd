package com.insigma.ordercenter.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.insigma.ordercenter.entity.Warehouse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.insigma.ordercenter.entity.dto.WarehouseDTO;
import com.insigma.ordercenter.entity.dto.WarehouseProductDTO;
import com.insigma.ordercenter.entity.dto.WarehouseProductPage;
import com.insigma.ordercenter.entity.dto.WarehouseProductPageQuery;
import com.insigma.ordercenter.entity.dto.shop.ShopQueryResponse;
import com.insigma.ordercenter.entity.vo.WarehouseVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 仓库表 Mapper 接口
 * </p>
 *
 * @author Jason
 * @since 2020-07-08
 */
public interface WarehouseMapper extends BaseMapper<Warehouse> {

    List<WarehouseProductPage> list(Page page, WarehouseProductPageQuery request);

    List<WarehouseVo> page(IPage<WarehouseVo> page, WarehouseDTO map);

    void removeProduct(Integer warehouseId, Integer productId);

    int getWarningCount(Integer warehouseId, Integer productId);

    void updateWarningCount(Integer warehouseId, Integer productId,Integer warningCount);

    int getWarningCount(String warehouseId, String productId);

    void updateWarningCount(String warehouseId, String productId, String warningCount);

    void removeProduct(String warehouseId, String productId);

}
