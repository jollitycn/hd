package com.insigma.ordercenter.logistics;

import com.insigma.ordercenter.base.Result;
import com.insigma.ordercenter.entity.dto.CommonConsigneeDTO;
import com.insigma.ordercenter.entity.dto.CommonConsignorDTO;
import com.insigma.ordercenter.entity.dto.CommonProductDTO;

/**
 * @program: hdoc-parent
 * @description: 物流中心服务类
 * @author: XuChao
 * @create: 2020-08-03 15:30
 **/
public class LogisticsCentreService {


    /**
     * 下单接口
     * @param commonProduct 通用货物封装类
     * @param commonConsignee 通用收件人封装类
     * @param commonConsignor 通用发件人封装类
     * @param logisticsType 1顺丰速运 2百世汇通 3宅急送
     * @return
     */
    public static Result generateLogistics(CommonProductDTO commonProduct,
                                           CommonConsigneeDTO commonConsignee ,
                                           CommonConsignorDTO commonConsignor,
                                           int logisticsType){
        //TODO


        return null;
    }

    /**
     * 取消物流订单接口
     * @param shippingOrderId 发货单id
     * @return
     */
    public static Result cancelLogistics(Long shippingOrderId){

        //TODO
        return null;
    }

    /**
     * 查询物流单接口
     * @param shippingOrderId 发货单id
     * @return
     */
    public static Result queryLogistics(Long shippingOrderId){

        //TODO
        return null;
    }


}
