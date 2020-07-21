package com.insigma.ordercenter.logistics.sf.qiao;

import com.educiot.common.base.BaseVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class OrderFilter extends BaseVO {

    //是	1	筛单类别:
    //    //1:自动筛单(系统根据地址库进行判断,并返回结果,系统无法检索到可派送的将返回不可派送)
    //    //2:可人工筛单(系统首先根据地址库判断,如果无法自动判断是否收派,系统将生成需要人工判断的任务,后续由人工处理,处理结束后,顺丰可主动推送给客户系统)
    //
    @ApiModelProperty(value = "筛单类别",required = true)
    private int filterType;

    //客户订单号
    @ApiModelProperty(value = "客户订单号",required = true)
    private String orderId;

    //月结卡号
    private String monthlyCard;

    //	地址信息（详细参看下
    //    //2.4.2.2）
    private List<ContactInfo> contactInfos;
}
