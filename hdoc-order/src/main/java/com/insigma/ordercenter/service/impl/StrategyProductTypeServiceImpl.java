package com.insigma.ordercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.insigma.ordercenter.base.CodeMsg;
import com.insigma.ordercenter.base.Result;
import com.insigma.ordercenter.constant.Constant;
import com.insigma.ordercenter.entity.LoginUser;
import com.insigma.ordercenter.entity.StrategyProductType;
import com.insigma.ordercenter.entity.StrategyRegionRelation;
import com.insigma.ordercenter.entity.StrategyWarehouseRelation;
import com.insigma.ordercenter.entity.vo.StrategyProductTypeVO;
import com.insigma.ordercenter.entity.vo.StrategyRegionRelationVO;
import com.insigma.ordercenter.entity.vo.StrategyWarehouseRelationVO;
import com.insigma.ordercenter.mapper.StrategyProductTypeMapper;
import com.insigma.ordercenter.service.IStrategyProductTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.insigma.ordercenter.service.IStrategyRegionRelationService;
import com.insigma.ordercenter.service.IStrategyWarehouseRelationService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author youwk
 * @since 2020-08-18
 */
@Service
public class StrategyProductTypeServiceImpl extends ServiceImpl<StrategyProductTypeMapper, StrategyProductType> implements IStrategyProductTypeService {

    @Resource
    private IStrategyRegionRelationService strategyRegionRelationService;

    @Resource
    private IStrategyWarehouseRelationService strategyWarehouseRelationService;


    @Override
    public List<StrategyProductTypeVO> strategyProductTypeList(Integer paramType) {
        List<StrategyProductTypeVO> strategyProductTypeVOS = baseMapper.strategyProductTypeList(paramType);
        strategyProductTypeVOS.forEach(strategyProductTypeVO -> {
            List<StrategyWarehouseRelationVO> strategyWarehouseRelationVOList = baseMapper.selectStrategyWarehouseRelationsById(strategyProductTypeVO.getStrategyProductTypeId());
            List<StrategyRegionRelationVO> strategyRegionRelationVOS = baseMapper.selectStrategyRegionRelationsById(strategyProductTypeVO.getStrategyProductTypeId());
            strategyProductTypeVO.setStrategyRegionRelations(strategyRegionRelationVOS);
            strategyProductTypeVO.setStrategyWarehouseRelations(strategyWarehouseRelationVOList);
        });
        return strategyProductTypeVOS;
    }

    @Override
    public Result addStrategyProductType(StrategyProductTypeVO strategyProductTypeVO) {
        if (null == strategyProductTypeVO && strategyProductTypeVO.getStrategyRegionRelations().size() <= 0
                && strategyProductTypeVO.getStrategyWarehouseRelations().size() <= 0) {
            return Result.error(CodeMsg.PARAMETER_ERROR);
        }
        //判断是否需要禁止
        QueryWrapper<StrategyProductType> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("param_id", strategyProductTypeVO.getParamId());
        queryWrapper.eq("param_type", strategyProductTypeVO.getParamType());

        //保存策略商品、分类信息
        StrategyProductType strategyProductType = new StrategyProductType();
        Integer integer = baseMapper.selectCount(queryWrapper);
        if (integer > 1) {
            strategyProductType.setIsStop(Constant.SYS_ONE);
        }else{
            strategyProductType.setIsStop(Constant.SYS_ZERO);
        }
        strategyProductType.setParamId(strategyProductTypeVO.getParamId());
        strategyProductType.setStrategyName(strategyProductTypeVO.getStrategyName());
        strategyProductType.setParamType(strategyProductTypeVO.getParamType());
        baseMapper.insert(strategyProductType);

        //保存策略关联区域信息
        strategyProductTypeVO.getStrategyRegionRelations().forEach(strategyRegionRelation -> {
            StrategyRegionRelation strategyRegion = new StrategyRegionRelation();
            strategyRegion.setRegionId(strategyRegionRelation.getRegionId());
            strategyRegion.setSptId(strategyProductType.getStrategyProductTypeId());
            strategyRegionRelationService.save(strategyRegion);
        });

        //保存策略关联仓库信息
        strategyProductTypeVO.getStrategyWarehouseRelations().forEach(strategyWarehouseRelation -> {
            StrategyWarehouseRelation strategyWarehouse = new StrategyWarehouseRelation();
            strategyWarehouse.setSptId(strategyProductType.getStrategyProductTypeId());
            strategyWarehouse.setWarehouseId(strategyWarehouseRelation.getWarehouseId());
            strategyWarehouseRelationService.save(strategyWarehouse);
        });

        return Result.success();
    }

    @Override
    public Result deleteStrategyProductType(Integer strategyProductTypeId) {
        if (null == strategyProductTypeId) {
            return Result.error(CodeMsg.PARAMETER_ERROR);
        }
        try {
            baseMapper.deleteById(strategyProductTypeId);
            QueryWrapper<StrategyRegionRelation> strategyRegionRelationQueryWrapper = new QueryWrapper<>();
            strategyRegionRelationQueryWrapper.eq("spt_id", strategyProductTypeId);
            strategyRegionRelationService.remove(strategyRegionRelationQueryWrapper);
            QueryWrapper<StrategyWarehouseRelation> strategyWarehouseRelationQueryWrapper = new QueryWrapper<>();
            strategyWarehouseRelationQueryWrapper.eq("spt_id", strategyProductTypeId);
            strategyWarehouseRelationService.remove(strategyWarehouseRelationQueryWrapper);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(CodeMsg.DATA_DELETE_ERROR);
        }
        return Result.success();
    }

    @Override
    public Result getStrategyProductType(Integer strategyProductTypeId) {
        StrategyProductTypeVO strategyProductTypeVO = baseMapper.selectStrategyProductTypeById(strategyProductTypeId);
        //查询 策略关联区域信息

        List<StrategyRegionRelationVO> strategyRegionRelationVOS = baseMapper.selectStrategyRegionRelationsById(strategyProductTypeId);
        //查询 策略关联仓库信息

        List<StrategyWarehouseRelationVO> strategyWarehouseRelationVOS = baseMapper.selectStrategyWarehouseRelationsById(strategyProductTypeId);

        strategyProductTypeVO.setStrategyRegionRelations(strategyRegionRelationVOS);
        strategyProductTypeVO.setStrategyWarehouseRelations(strategyWarehouseRelationVOS);

        return Result.success(strategyProductTypeVO);
    }


    @Override
    public Result updateStrategyProductType(StrategyProductTypeVO strategyProductTypeVO) {
        //先删除所有的策略分类信息，以及其他关联信息
        if (null == strategyProductTypeVO && strategyProductTypeVO.getStrategyRegionRelations().size() <= 0
                && strategyProductTypeVO.getStrategyWarehouseRelations().size() <= 0) {
            return Result.error(CodeMsg.PARAMETER_ERROR);
        }
        try {
            this.deleteStrategyProductType(strategyProductTypeVO.getStrategyProductTypeId());

            this.addStrategyProductType(strategyProductTypeVO);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(CodeMsg.DATA_UPDATE_ERROR);
        }
        return Result.success();
    }


    @Override
    public Result updateStrategyProductTypeIsStop(StrategyProductTypeVO strategyProductTypeVO) {
        if (null == strategyProductTypeVO) {
            return Result.error(CodeMsg.PARAMETER_ERROR);
        }
        try {
            //先根据相同的商品分类禁止所有的策略
            baseMapper.updateStrategyProductTypeIsStop(strategyProductTypeVO.getParamId());
            //如果是启用的话，那么就根据主键Id
            if (strategyProductTypeVO.getIsStop() == 0) {
                baseMapper.updateStrategyProductTypeIsOn(strategyProductTypeVO.getStrategyProductTypeId());
            }

        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(CodeMsg.DATA_UPDATE_ERROR);
        }
        return Result.success();
    }
}
