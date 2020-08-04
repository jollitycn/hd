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

    List<WarehouseProductPage> list(@Param("page") Page page, @Param("request") WarehouseProductPageQuery request);

    List<WarehouseVo> page(@Param("page") IPage<WarehouseVo> page,@Param("map")  WarehouseDTO map);

    void removeProduct(@Param("warehouseId") Integer warehouseId,@Param("productId")  Integer productId);

    int getWarningCount(@Param("warehouseId") Integer warehouseId,@Param("productId")  Integer productId);

    void updateWarningCount(@Param("warehouseId") Integer warehouseId, @Param("productId") Integer productId,@Param("warningCount") Integer warningCount);

    int getWarningCount(@Param("warehouseId") String warehouseId, @Param("productId") String productId);

    void updateWarningCount(@Param("warehouseId") String warehouseId,@Param("productId")  String productId, @Param("warningCount")  String warningCount);

    void removeProduct(@Param("warehouseId") String warehouseId,@Param("productId")  String productId);

}
