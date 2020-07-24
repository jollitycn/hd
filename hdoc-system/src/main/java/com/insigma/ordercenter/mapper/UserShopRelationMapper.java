package com.insigma.ordercenter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.insigma.ordercenter.entity.UserShopRelation;
import com.insigma.ordercenter.entity.vo.ShopInfoVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户-店铺关联表 Mapper 接口
 * </p>
 *
 * @author panjuncai
 * @since 2020-07-24
 */
public interface UserShopRelationMapper extends BaseMapper<UserShopRelation> {

    /**
     * 获取所有在使用中的店铺列表
     *
     * @return java.util.List&lt;com.insigma.ordercenter.entity.vo.ShopInfoVO&gt;
     * @author Pan Juncai
     * @date 2020/7/24 16:04
     */
    List<ShopInfoVO> listEnabledShop();

    /**
     * 查询指定用户关联的店铺
     *
     * @param userId 用户id
     * @return java.util.List&lt;com.insigma.ordercenter.entity.vo.ShopInfoVO&gt;
     * @author Pan Juncai
     * @date 2020/7/24 16:13
     */
    List<ShopInfoVO> listShopByUserId(@Param("userId") Long userId);
}
