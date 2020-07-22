package com.insigma.ordercenter.logistics.sf.qiao;

import com.insigma.ordercenter.entity.vo.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value="QuerySFRoute", description="QuerySFRoute")
public class QuerySFRoute extends BaseVO {
    @ApiModelProperty("返回描述语语言0：中文 1：英文 2：繁体")
    private int language;
    @ApiModelProperty("查询号类别:  1:根据顺丰运单号查询,trackingNumber将被当作顺丰运单号处理 2:根据客户订单号查询,trackingNumber将被当作客户订单号处理")
    private int trackingType;
    @ApiModelProperty("查询号: trackingType=1,则此值为顺丰运单号 如果trackingType=2,则此值为客户订单号")
    private  List<String> trackingNumber;
    @ApiModelProperty("路由查询类别: 1:标准路由查询 2:定制路由查询")
    private  int methodType;
    @ApiModelProperty("参考编码(目前针对亚马逊客户,由客户传)")
    private String referenceNumber;
    @ApiModelProperty("电话号码验证")
    private String checkPhoneNo;
}
