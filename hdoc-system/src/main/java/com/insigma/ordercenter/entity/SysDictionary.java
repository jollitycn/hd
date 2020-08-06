package com.insigma.ordercenter.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 系统数字字典
 * </p>
 *
 * @author Jason
 * @since 2020-01-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value="SysDictionary对象", description="系统数字字典")
public class SysDictionary implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "dictionary_Id", type = IdType.ID_WORKER)
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

    @ApiModelProperty(value = "最后修改人")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long modifyId;

    @ApiModelProperty(value = "修改时间")
    @JsonSerialize(using= ToStringSerializer.class)
    private LocalDateTime modifyTime;

    @ApiModelProperty(value = "最后修改人姓名")
    @TableField(exist = false)
    private String modifyName;

}
