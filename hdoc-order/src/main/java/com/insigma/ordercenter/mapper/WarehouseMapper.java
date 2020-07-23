package com.insigma.ordercenter.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.insigma.ordercenter.entity.Warehouse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.insigma.ordercenter.entity.dto.WarehouseDTO;
import com.insigma.ordercenter.entity.vo.WarehouseVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 仓库表 Mapper 接口
 * </p>
 *
 * @author LiuHao
 * @since 2020-07-08
 */
public interface WarehouseMapper extends BaseMapper<Warehouse> {

    List<WarehouseVo> page(IPage<WarehouseVo> page,@Param("dto") WarehouseDTO dto);

}
