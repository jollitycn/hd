package com.insigma.ordercenter.logistics.sf.qiao;

import com.educiot.common.base.BaseVO;
import lombok.Data;

@Data
public class OrderFilterData  extends BaseVO {
private boolean orderId;
private int filterResult;
private String originCode;
private String destCode;
private String remark;
}
