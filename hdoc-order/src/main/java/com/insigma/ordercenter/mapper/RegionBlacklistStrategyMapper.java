package com.insigma.ordercenter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.insigma.ordercenter.entity.RegionBlacklistStrategy;
import com.insigma.ordercenter.entity.dto.StrategyParamDTO;
import com.insigma.ordercenter.entity.vo.RegionBlackVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 区域黑名单表 Mapper 接口
 * </p>
 *
 * @author panjuncai
 * @since 2020-07-28
 */
public interface RegionBlacklistStrategyMapper extends BaseMapper<RegionBlacklistStrategy> {
    /**
     * 分页查询区域黑名单
     *
     * @param page 分页参数
     * @param req  查询条件
     * @return com.baomidou.mybatisplus.core.metadata.IPage&lt;com.insigma.ordercenter.entity.vo.RegionBlackVO&gt;
     * @author Pan Juncai
     * @date 2020/7/29 13:51
     */
    IPage<RegionBlackVO> listRegionBlackPage(Page<RegionBlackVO> page, @Param("param") StrategyParamDTO req);
}
