package com.insigma.ordercenter.entity.dto.best;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Pan Juncai
 * @version 1.0
 * @date 2020/7/24 10:30
 */
@Data
public class RecipientDTO implements Serializable {
    private String name;
    private String mobileNumber;
    private String province;
    private String city;
    private String district;
    private String shippingAddress;
}
