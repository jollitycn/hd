package com.insigma.ordercenter.service.sf.oms;

import com.insigma.ordercenter.entity.vo.BaseVO;
import lombok.Data;

import java.util.List;

@Data
public class TransportRequest extends BaseVO {

    private List<OrderItem> orderItems;
    private List<OrderService> orderServices;

}

