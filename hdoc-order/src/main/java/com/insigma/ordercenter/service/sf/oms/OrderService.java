package com.insigma.ordercenter.service.sf.oms;

import com.insigma.ordercenter.entity.vo.BaseVO;
import lombok.Data;

@Data
public class OrderService extends BaseVO {
    private String serviceValue;
    private String serviceCode;
}
