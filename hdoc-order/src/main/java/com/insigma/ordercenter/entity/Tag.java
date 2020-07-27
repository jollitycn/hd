package com.insigma.ordercenter.entity;

    import com.baomidou.mybatisplus.annotation.TableName;
    import com.baomidou.mybatisplus.annotation.IdType;
    import com.baomidou.mybatisplus.annotation.TableId;
    import java.io.Serializable;
    import io.swagger.annotations.ApiModel;
    import io.swagger.annotations.ApiModelProperty;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;

/**
* <p>
    * 商品标签表
    * </p>
*
* @author Jason
* @since 2020-07-24
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName("t_tag")
    @ApiModel(value="Tag对象", description="商品标签表")
    public class Tag implements Serializable {

    private static final long serialVersionUID = 1L;

            @ApiModelProperty(value = "标签ID")
            @TableId(value = "tag_id", type = IdType.ID_WORKER)
    private Long tagId;

            @ApiModelProperty(value = "商品ID")
    private Long productId;

            @ApiModelProperty(value = "标签")
    private String tag;


        public static final String TAG_ID = "tag_id";

        public static final String PRODUCT_ID = "product_id";

        public static final String TAG = "tag";

}
