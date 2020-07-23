package com.insigma.ordercenter.service.sf.oms;

import com.insigma.ordercenter.entity.vo.BaseVO;
import lombok.Data;

@Data
public class OrderItem extends BaseVO {
    private String monthlyAccount;
    private String temperatureLevelName;
    private String remark;
    private String skuName;
    private String quantity;
    private String grossWeight;
    private String volume;
}
