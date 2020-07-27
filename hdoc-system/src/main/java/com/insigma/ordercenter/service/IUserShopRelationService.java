package com.insigma.ordercenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.insigma.ordercenter.entity.UserShopRelation;
import com.insigma.ordercenter.entity.query.UpdateUserShopQuery;
import com.insigma.ordercenter.entity.vo.ShopInfoVO;

import java.util.List;

/**
 * <p>
 * 用户-店铺关联表 服务类
 * </p>
 *
 * @author panjuncai
 * @since 2020-07-24
 */
public interface IUserShopRelationService extends IService<UserShopRelation> {

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
    List<ShopInfoVO> listShopByUserId(Long userId);

    /**
     * 修改用户关联的店铺：先删除原有关系，建立新的关系
     *
     * @param param 修改信息
     * @author Pan Juncai
     * @date 2020/7/24 16:31
     */
    void updateUserShop(UpdateUserShopQuery param);
}
