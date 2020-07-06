package com.insigma.ordercenter.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author ：liuhao
 * @date ：Created in 2020/2/17
 */
@Data
@ApiModel(value = "数据字典返回VO")
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysDictVO extends BaseVO {

    @ApiModelProperty(value = "数字字典id")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long dictionaryId;

    @ApiModelProperty(value = "父id")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long parentId;

    @ApiModelProperty(value = "数据编码")
    private String dataCode;

    @ApiModelProperty(value = "数据类型")
    private String dataType;

    @ApiModelProperty(value = "数据名称/数据值")
    private String dataValue;

    @ApiModelProperty(value = "数据英文名称")
    private String dataEnglishValue;

    @ApiModelProperty(value = "数据说明")
    private String dataDesc;

    @ApiModelProperty(value = "数据顺序")
    private Integer dataOrder;

    @ApiModelProperty(value = "是否只读（0：否，1：只读）")
    private Integer isReadOnly;

    @ApiModelProperty(value = "状态（0：正常，1：删除）")
    private Integer isDeleted;

    @ApiModelProperty(value = "下级菜单")
    private List<SysDictVO> subs;
}
