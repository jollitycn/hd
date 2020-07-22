package com.insigma.ordercenter.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.insigma.ordercenter.entity.ShippingOrder;
import com.insigma.ordercenter.entity.dto.EditShippingOrderDTO;
import com.insigma.ordercenter.entity.dto.ShippingOrderDTO;
import com.insigma.ordercenter.entity.vo.ShippingOrderVO;
import com.insigma.ordercenter.mapper.ShippingOrderMapper;
import com.insigma.ordercenter.service.IShippingOrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * 发货单表 服务实现类
 * </p>
 *
 * @author Xuchao
 * @since 2020-07-08
 */
@Service
public class ShippingOrderServiceImpl extends ServiceImpl<ShippingOrderMapper, ShippingOrder> implements IShippingOrderService {

//    @Autowired
//    private OrderSh


    @Override
    public IPage<ShippingOrderVO> getShippingOrderList(ShippingOrderDTO shippingOrderDTO) {

        //处理分页参数
        Page<ShippingOrderVO> page = new Page<>(shippingOrderDTO.getPageNum(), shippingOrderDTO.getPageSize());

        //查询
        IPage<ShippingOrderVO> result=baseMapper.getShippingOrderList(page,shippingOrderDTO);

        return result;

    }

    @Override
    public Boolean increaseCargo(EditShippingOrderDTO editShippingOrderDTO) {

        //写入发货单对象
        ShippingOrder shippingOrder = new ShippingOrder();

        shippingOrder.setAddress("1");
        shippingOrder.setReceiveName("1");
        shippingOrder.setReceiveRemark("1");
        shippingOrder.setMobilePhone("1");
        shippingOrder.setCreateTime(LocalDateTime.now());
        return this.save(shippingOrder);
    }

    /**
     * 更改发货单地址
     *
     * @param shippingOrderId
     * @param editShippingOrderDTO
     * @return
     */
    @Override
    public Boolean changeAddress(Long shippingOrderId, EditShippingOrderDTO editShippingOrderDTO) {
        return null;
    }

    /**
     * 更改发货单商品
     *
     * @param shippingOrderId
     * @param editShippingOrderDTO
     * @return
     */
    @Override
    public Boolean changeProduct(Long shippingOrderId, EditShippingOrderDTO editShippingOrderDTO) {
        return null;
    }

    /**
     * 更改发货单仓库
     *
     * @param shippingOrderId
     * @param editShippingOrderDTO
     * @return
     */
    @Override
    public Boolean changeWarehouse(Long shippingOrderId, EditShippingOrderDTO editShippingOrderDTO) {
        return null;
    }

    /**
     * 取消发货单
     *
     * @param shippingOrderId
     * @return
     */
    @Override
    public Boolean cancel(Long shippingOrderId) {
        return null;
    }

    /**
     * 冻结发货单
     *
     * @param shippingOrderId
     * @return
     */
    @Override
    public Boolean frozen(Long shippingOrderId) {
        return null;
    }


}
