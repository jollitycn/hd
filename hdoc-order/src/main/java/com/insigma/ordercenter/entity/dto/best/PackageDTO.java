package com.insigma.ordercenter.entity.dto.best;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Pan Juncai
 * @version 1.0
 * @date 2020/7/24 10:37
 */
@Data
public class PackageDTO implements Serializable {
    private String packCode;
    private String packLogisticsProviderCode;
    private String packShippingOrderNo;
    private double packWeight;
    private double packNetWeight;
    private int packGrossWeight;
    private double packVolume;
    private int packLength;
    private int packWidth;
    private int packHeight;
    private String warehouseCode;
    private PackProductsDTO packProducts;
}
