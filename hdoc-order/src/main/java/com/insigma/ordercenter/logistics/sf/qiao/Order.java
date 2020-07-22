package com.insigma.ordercenter.logistics.sf.qiao;

import com.insigma.ordercenter.entity.vo.BaseVO;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Order extends BaseVO {
    private String language ;
    private String orderId ;
    private CustomsInfo customsInfo ;
    private List<WaybillNoInfo> waybillNoInfoList ;
    private List<CargoDetail> cargoDetails ;
    private String cargDoesc ;
    private List<ExtraInfo> extraInfoList ;
    private List<Service> serviceList ;
    private List<ContactInfo> contactInfoList ;
    private String monthlyCard ;
    private int payMethod;
    private int expressTypeId;
    private int parcelQty;
    private Double totalLength;
    private Double totalWidth;
    private Double totalHeight;
    private Double volume;
    private Double totalNetWeight;
    private Double totalWeight;
    private Date sendStartTm;
    private int isDocall;
    private int isSignBack;
    private int temperatureRange;
    private String custReferenceNo ;
    private String orderSource ;
    private String bizTemplateCode;
    private String remark;
    private int isOneselfPickup;
    private String filterField;
    private int isReturnQRCode;
    private String specialDeliveryTypeCode;
    private String specialDeliveryValue;
    private String realnameNum;
    private String merchantPayOrderNo;
    private int isReturnSignBackRoute;
    private int isReturnRoutelabel;
    private int isUnifiedWaybillNo;
    private String podModelAddress;
    private String collectEmpCode;
    private String inProcessWaybillNo;
}
