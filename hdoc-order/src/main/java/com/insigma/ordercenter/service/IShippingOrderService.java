package com.insigma.ordercenter.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.insigma.ordercenter.base.Result;
import com.insigma.ordercenter.entity.LoginUser;
import com.insigma.ordercenter.entity.ShippingOrder;
import com.insigma.ordercenter.entity.dto.EditShippingOrderDTO;
import com.insigma.ordercenter.entity.dto.EditShippingOrderProductDTO;
import com.insigma.ordercenter.entity.dto.ShippingOrderDTO;
import com.insigma.ordercenter.entity.vo.ShippingOrderDetailVO;
import com.insigma.ordercenter.entity.vo.ShippingOrderVO;

import java.util.List;

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
     * @param loginUser
     * @return
     */
    Boolean increaseCargo(LoginUser loginUser,EditShippingOrderDTO editShippingOrderDTO);

    /**
     * 更改发货单地址
     * @param editShippingOrderDTO
     * @return
     */
    Boolean changeAddress(LoginUser loginUser, EditShippingOrderDTO editShippingOrderDTO);

    /**
     * 更改发货单商品
     * @param editParameters
     * @return
     */
    Boolean changeProduct(LoginUser loginUser,EditShippingOrderProductDTO editParameters);

    /**
     * 更改发货单仓库
     * @param editShippingOrderDTO
     * @return
     */
    Boolean changeWarehouse(LoginUser loginUser,EditShippingOrderDTO editShippingOrderDTO);

    /**
     * 取消发货单
     * @param shippingOrderId
     * @param loginUser
     * @return
     */
    Boolean cancel(LoginUser loginUser,Long shippingOrderId);

    /**
     * 冻结发货单
     * @param shippingOrderId
     * @return
     */
    Boolean frozen(LoginUser loginUser,Long shippingOrderId);

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
    Result queryLogistics(Long shippingOrderId);


    List<Long> getShippingOrderByProductType(Long orderDetailId);

    /**
     * 发货单物流下单
     * @return
     */
    boolean createLogisticsJob();

    /**
     * 保存补货单
     * @param loginUser
     * @param shippingOrderId
     * @return
     */
    Boolean saveIncreaseCargo(LoginUser loginUser, Long shippingOrderId);
}
