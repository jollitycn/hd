package com.insigma.ordercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.insigma.ordercenter.entity.WarehouseProductRelation;
import com.insigma.ordercenter.entity.WarehouseRegion;
import com.insigma.ordercenter.mapper.WarehouseRegionMapper;
import com.insigma.ordercenter.service.IWarehouseRegionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 仓库负责区域表 服务实现类
 * </p>
 *
 * @author Jason
 * @since 2020-07-08
 */
@Service
public class WarehouseRegionServiceImpl extends ServiceImpl<WarehouseRegionMapper, WarehouseRegion> implements IWarehouseRegionService {

}
