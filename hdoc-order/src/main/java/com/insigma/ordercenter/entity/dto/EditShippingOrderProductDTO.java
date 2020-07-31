package com.insigma.ordercenter.entity.dto;

import com.insigma.ordercenter.entity.vo.EditOrderProductDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @program: hdoc-parent
 * @description: 修改发货单商品参数封装类
 * @author: XuChao
 * @create: 2020-07-31 16:48
 **/
@Data
public class EditShippingOrderProductDTO {

    @ApiModelProperty(value = "退货原因")
    private String changeReason;

    @ApiModelProperty(value = "商品规格")
    private List<EditOrderProductDTO> editOrderProductDTOList;

}
