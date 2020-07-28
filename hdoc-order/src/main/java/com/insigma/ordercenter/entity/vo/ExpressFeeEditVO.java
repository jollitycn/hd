package com.insigma.ordercenter.entity.vo;

import com.insigma.ordercenter.entity.ExpressFee;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Jason
 * @program hdoc-parent
 * @description：承运商信息列表vo
 * @date Create in 2020/7/17
 */
@Data
@ApiModel(value = "承运商信息列表vo")
@EqualsAndHashCode(callSuper = true)
public class ExpressFeeEditVO extends ExpressFee {
}