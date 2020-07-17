package com.insigma.ordercenter.entity.express;

import lombok.Data;

import java.io.Serializable;

/**
 * @author youwk
 * @ClassName ZjsOrderPackagesItem
 * @description TODO
 * @date 2020/7/16 17:53
 * @Version 1.0
 */
@Data
public class ZjsOrderPackagesItem implements Serializable {

    private String itemNo;
    private String itemName;
    private String itemNumber;
    private String itemValue;
    private String itemWeight;
    private String itemDesc;

}
