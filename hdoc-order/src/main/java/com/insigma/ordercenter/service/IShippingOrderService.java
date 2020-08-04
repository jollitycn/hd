package com.insigma.ordercenter.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.insigma.ordercenter.entity.ShippingOrder;
import com.insigma.ordercenter.entity.dto.EditShippingOrderDTO;
import com.insigma.ordercenter.entity.dto.EditShippingOrderProductDTO;
import com.insigma.ordercenter.entity.dto.ShippingOrderDTO;
import com.insigma.ordercenter.entity.vo.LogisticsVO;
import com.insigma.ordercenter.entity.vo.ShippingOrderDetailVO;
import com.insigma.ordercenter.entity.vo.ShippingOrderVO;

/**
 * <p>
 * 发货单表 服务类
 * </p>
 *
 * @author Xuchao
 * @since 2020-07-08
 */
public interface IShippingOrderService extends IService<ShippingOrder> {

    /**
     *  分页查询发货单列表
     * @param shippingOrderDTO
     * @return
     */
    IPage<ShippingOrderVO> getShippingOrderList(ShippingOrderDTO shippingOrderDTO);

    /**
     * 增加补货单
     * @param editShippingOrderDTO
     * @return
     */
    Boolean increaseCargo(EditShippingOrderDTO editShippingOrderDTO);

    /**
     * 更改发货单地址
     * @param shippingOrderId
     * @param editShippingOrderDTO
     * @return
     */
    Boolean changeAddress(Long shippingOrderId, EditShippingOrderDTO editShippingOrderDTO);

    /**
     * 更改发货单商品
     * @param shippingOrderId
     * @param editParameters
     * @return
     */
    Boolean changeProduct(Long shippingOrderId, EditShippingOrderProductDTO editParameters);

    /**
     * 更改发货单仓库
     * @param shippingOrderId
     * @param editShippingOrderDTO
     * @return
     */
    Boolean changeWarehouse(Long shippingOrderId, EditShippingOrderDTO editShippingOrderDTO);

    /**
     * 取消发货单
     * @param shippingOrderId
     * @return
     */
    Boolean cancel(Long shippingOrderId);

    /**
     * 冻结发货单
     * @param shippingOrderId
     * @return
     */
    Boolean frozen(Long shippingOrderId);

    /**
     * 获取发货单明细
     * @param shippingOrderId
     * @return
     */
    ShippingOrderDetailVO getShippingDetail(Long shippingOrderId);

    /**
     * 发货单拒收
     * @param shippingOrderId
     * @param sourceType
     * @param reason
     * @return
     */
    Boolean rejection(Long shippingOrderId, Integer sourceType, String reason);

    /**
     * 物流查询
     * @param shippingOrderId
     * @return
     */
    LogisticsVO queryLogistics(Long shippingOrderId);
}
