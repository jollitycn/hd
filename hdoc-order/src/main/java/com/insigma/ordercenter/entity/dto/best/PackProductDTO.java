package com.insigma.ordercenter.entity.dto.best;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Pan Juncai
 * @version 1.0
 * @date 2020/7/24 10:39
 */
 @Data
public class PackProductDTO implements Serializable {
    private String packCode;
    private String packSkuCode;
    private String packProviderCode;
    private int packQuantity;
    private int packDecimalQty;
}
