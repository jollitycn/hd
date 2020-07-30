package com.insigma.ordercenter.constant;

import io.swagger.models.auth.In;

/**
 * @author youwk
 * @ClassName OrderStrategyConstant
 * @description TODO
 * @date 2020/7/30 10:44
 * @Version 1.0
 */
public interface OrderStrategyConstant {

    /**
     *  根据订单策略自动将订单转为发货单功能
     */
    Integer AUTO_SHIPPING = 1;

    /**
     * 有备注的订单不能自动审单（用户备注，客服备注）
     */
    Integer REMARK = 2;

    /**
     * 手动单不能自动审核
     */
    Integer MANUAL = 3;


    /**
     * 发货单多少分钟生成后直接推送至承运商
     */
    Integer PUSH_MINUTES = 4;

    /**
     * 合单策略
     */
    Integer COMBINED_ORDER = 5;

    /**
     * 店铺的发货仓库策略
     */
    Integer SHOP_WAREHOUSE = 6;

    /**
     * 商品分类拆单策略
     */
    Integer PRODUCT_TYPE_DIVIDE = 7;

    /**
     * 商品分类 + 地址 匹配 承运商仓库
     */
    Integer PRODUCT_TYPE_ADDRESS = 8;


    /**
     * 商品 + 地址 匹配 承运商仓库
     */
    Integer PRODUCT_ADDRESS = 9;

    /**
     * 区域重叠按仓库优先级
     */
    Integer WAREHOUSE_PRIORITY = 10;

    /**
     * 黑名单账号拦截
     */
    Integer BLACKLIST_ACCOUNT = 11;

    /**
     * 黑名单手机号拦截
     */
    Integer BLACKLIST_MOBILE = 12;

    /**
     * 区域拦截
     */
    Integer BLACKLIST_DISTRICT = 13;

    /**
     *  地址无法解析或匹配
     */
    Integer ADDRESS_PARSE_ERROR = 14;

    /**
     * 换货策略
     */
    Integer CHANGE_PRODUCT = 15;

    /**
     * 赠品策略
     */
    Integer GIFT = 16;
}
