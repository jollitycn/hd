package com.insigma.ordercenter.logistics.zjs.express;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @author youwk
 * @ClassName ZjsOrder
 * @description TODO
 * @date 2020/7/16 17:27
 * @Version 1.0
 */
@Data
public class ZjsOrder implements Serializable {

    private String sendCity;
    private String sendStreet;
    private String receiveStreet;
    private String originalNo;
    private String sendPro;
    private String invoiceState;
    private String goodsValue;
    private String clientOperatecode;
    private String insuranceType;
    private String goodsWeight;
    private String openType;
    private String mailNo;
    private String codFlag;
    private String pickupTime;
    private String sendDistrict;
    private String receiveName;
    private String sendIdentityCode;
    private String sendMobile;
    private String codAmount;
    private String serviceAgent;
    private String busType;
    private String goodsName;
    private String goodsNum;
    private String receiveMobile;
    private String insuranceMode;
    private String receivePhone;
    private String orderNo;
    private String receiveDistrict;
    private String sendName;
    private String receiveAddress;
    private String payMode;
    private String receiveUnit;
    private String receiveIdentityCode;
    private String goodsVolume;
    private String dataFlag;
    private String sendAddress;
    private String receivePro;
    private String receiveCity;
    private String sendUnit;
    private String clientFlag;
    private String sendPhone;
    private Map extendedInfo;
    private String toPay;
    private String remarks;
    private ZjsOrderPackages[] orderPackages;

}
