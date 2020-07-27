package com.insigma.ordercenter.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 用户-店铺关联表
 * </p>
 *
 * @author panjuncai
 * @since 2020-07-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_user_shop_relation")
@ApiModel(value = "UserShopRelation对象", description = "用户-店铺关联表")
public class UserShopRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户关联店铺id")
    @TableId(value = "relation_id", type = IdType.ID_WORKER)
    private Long relationId;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "店铺id")
    private Long shopId;


    public static final String RELATION_ID = "relation_id";

    public static final String USER_ID = "user_id";

    public static final String SHOP_ID = "shop_id";

}
