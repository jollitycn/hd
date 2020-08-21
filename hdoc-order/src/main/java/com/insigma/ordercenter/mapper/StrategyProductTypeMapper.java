package com.insigma.ordercenter.mapper;

import com.insigma.ordercenter.entity.StrategyProductType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.insigma.ordercenter.entity.vo.StrategyProductTypeVO;
import com.insigma.ordercenter.entity.vo.StrategyRegionRelationVO;
import com.insigma.ordercenter.entity.vo.StrategyWarehouseRelationVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author youwk
 * @since 2020-08-18
 */
public interface StrategyProductTypeMapper extends BaseMapper<StrategyProductType> {

    List<StrategyProductTypeVO> strategyProductTypeList(@Param("paramType")Integer paramType);

    List<StrategyProductTypeVO> strategyProduct(@Param("paramType")Integer paramType);

    StrategyProductTypeVO selectStrategyProductTypeById(@Param("strategyProductTypeId") Integer strategyProductTypeId);

    List<StrategyRegionRelationVO> selectStrategyRegionRelationsById(@Param("strategyProductTypeId") Integer strategyProductTypeId);

    List<StrategyWarehouseRelationVO> selectStrategyWarehouseRelationsById(@Param("strategyProductTypeId") Integer strategyProductTypeId);

    Integer updateStrategyProductTypeIsStop(@Param("paramId")Long paramId);

    Integer updateStrategyProductTypeIsOn(@Param("strategyProductTypeId")Integer strategyProductTypeId);



}
