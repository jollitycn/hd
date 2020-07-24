package com.insigma.ordercenter.entity.dto.best;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Pan Juncai
 * @version 1.0
 * @date 2020/7/24 10:27
 */
@Data
public class WmsSoStatusPushDTO implements Serializable {
    private String customerCode;
    private String warehouseCode;
    private String orderCode;
    private String extTradeId;
    private String orderType;
    private String orderStatus;
    private String extOrderType;
    private String operator;
    private Date operatorTime;
    private String logisticsProviderCode;
    private String shippingOrderNo;
    private String remark;
    private String skuItem;
    private String totalVolume;
    private String totalWeight;
    private String totalNetWeight;
    private RecipientDTO recipient;
    private ProductsDTO products;
    private PackagesDTO packages;
}
