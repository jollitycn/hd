package com.insigma.ordercenter.logistics.sf.qiao;

import com.insigma.ordercenter.entity.vo.BaseVO;
import lombok.Data;

@Data
public class CargoDetail extends BaseVO {

    private String name;
    private String unit;
    private double amount;
    private String currency;
    private String sourceArea;
    private String productRecordNo;
    private String goodPrepardNo;
    private String hsCode;
    private String taxNo;
    private String goodsCode;
    private String brand;
    private String specifications;
    private String length;
    private String manufacturer;
    private double shipmentWeight;
    private double height;
    private double width;
    private double volume;
    private double cargoDeclaredValue;
    private double count;
    private double weight;
    private String cargoId;
    private String snCode;
    private int intelligentInspection;
    private String stateBarCode;
}
