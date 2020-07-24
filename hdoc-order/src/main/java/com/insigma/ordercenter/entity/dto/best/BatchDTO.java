package com.insigma.ordercenter.entity.dto.best;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Pan Juncai
 * @version 1.0
 * @date 2020/7/24 10:34
 */
 @Data
public class BatchDTO implements Serializable {
    private String fixStatusCode;
    private Date productionDate;
    private Date expiryDate;
    private int quantity;
    private String packCode;
    private int decimalQuantity;
}
