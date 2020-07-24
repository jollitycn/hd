package com.insigma.ordercenter.entity.dto.best;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Pan Juncai
 * @version 1.0
 * @date 2020/7/24 10:32
 */
@Data
public class ProductDTO implements Serializable {
    private String itemSkuCode;
    private int itemQuantity;
    private int normalQuantity;
    private int defectiveQuantity;
    private int decimalNormalQty;
    private int decimalDefectiveQty;
    private int averageWeight;
    private String barCode;
    private String skuUdf1;
    private String Udf1;
    private double weight;
    private double grossWeight;
    private int lineNo;
    private BatchsDTO batchs;
}
