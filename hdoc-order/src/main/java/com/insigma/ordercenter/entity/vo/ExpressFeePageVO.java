package com.insigma.ordercenter.entity.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.insigma.ordercenter.entity.ExpressFee;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author Jason
 * @program hdoc-parent
 * @description：承运商信息列表vo
 * @date Create in 2020/7/17
 */
@Data
@ApiModel(value = "承运商信息列表vo")
@EqualsAndHashCode(callSuper = true)
public class ExpressFeePageVO extends ExpressFee {
}