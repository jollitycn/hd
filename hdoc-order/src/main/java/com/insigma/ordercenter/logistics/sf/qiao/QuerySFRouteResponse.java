package com.insigma.ordercenter.logistics.sf.qiao;

import com.insigma.ordercenter.entity.vo.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="QuerySFRouteResponse", description="QuerySFRouteResponse")
public class QuerySFRouteResponse extends BaseVO {
    @ApiModelProperty("true 请求成功，false 请求失败")
    private String success;
    @ApiModelProperty("错误编码，S0000成功")
    private String errorCode;
    @ApiModelProperty("错误描述")
    private String errorMsg;
    @ApiModelProperty("返回的路由详细数据")
    private QuerySFRouteData msgData;
}
