package com.insigma.ordercenter.mapper;

import com.insigma.ordercenter.entity.ProductCombo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.insigma.ordercenter.entity.vo.ProductComboVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 组合信息表 Mapper 接口
 * </p>
 *
 * @author Jason
 * @since 2020-07-22
 */
public interface ProductComboMapper extends BaseMapper<ProductCombo> {

    List<ProductComboVO> getComboList(@Param("productId") Long productId);
}
