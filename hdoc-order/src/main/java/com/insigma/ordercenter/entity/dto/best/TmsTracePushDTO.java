package com.insigma.ordercenter.entity.dto.best;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Pan Juncai
 * @version 1.0
 * @date 2020/7/24 11:00
 */
@Data
public class TmsTracePushDTO implements Serializable {
    private String customerCode;
    private String warehouseCode;
    private String logisticsProviderCode;
    private String shippingOrder;
    private String orderCode;
    private String reforderCode;
    private String status;
    private List<TrackingInfoDTO> trackingInfoList;
}
