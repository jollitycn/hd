package com.insigma.ordercenter.logistics.zjs.express;

import lombok.Data;

/**
 * @program: hdoc-parent
 * @description: 宅急送下单请求参数类
 * @author: XuChao
 * @create: 2020-08-07 16:04
 **/
@Data
public class ZjsRequestData {

    private String clientFlag;

    private String mailNo;

    private String orderNo;

    private String busType;

    private String goodsName;

    private String goodsNum;

    private String goodsWeight;

    private String sendName;

    private String sendAddress;

    private String sendMobile;

    private String receiveName;

    private String receivePro;

    private String receiveCity;

    private String receiveDistrict;

    private String receiveAddress;

    private String receiveMobile;


}
