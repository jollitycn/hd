package com.insigma.ordercenter.entity;

    import com.baomidou.mybatisplus.annotation.TableName;
    import com.baomidou.mybatisplus.annotation.IdType;
    import java.time.LocalDateTime;
    import com.baomidou.mybatisplus.annotation.TableId;
    import java.io.Serializable;

    import com.fasterxml.jackson.databind.annotation.JsonSerialize;
    import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
    import io.swagger.annotations.ApiModel;
    import io.swagger.annotations.ApiModelProperty;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;

/**
* <p>
    * 库存记录操作日志表
    * </p>
*
* @author Jason
* @since 2020-07-28
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName("t_stock_operation_log")
    @ApiModel(value="StockOperationLog对象", description="库存记录操作日志表")
    public class StockOperationLog implements Serializable {

    private static final long serialVersionUID = 1L;

            @ApiModelProperty(value = "库存记录操作日志ID")
            @TableId(value = "stock_operation_log_id", type = IdType.ID_WORKER)
            @JsonSerialize(using = ToStringSerializer.class)
    private Long stockOperationLogId;

            @ApiModelProperty(value = "库存表ID")
            @JsonSerialize(using = ToStringSerializer.class)
    private Long warehouseProductRelationId;

            @ApiModelProperty(value = "创建人id")
            @JsonSerialize(using = ToStringSerializer.class)
    private Long createId;

            @ApiModelProperty(value = "创建时间")
            @JsonSerialize(using = ToStringSerializer.class)
    private LocalDateTime createTime;

            @ApiModelProperty(value = "原数量")
    private Integer originQuantity;

            @ApiModelProperty(value = "变更后数量")
    private Integer destinationQuantity;

            @ApiModelProperty(value = "变更数量")
    private int changeQuantity;
    @ApiModelProperty(value = "operationType")
    private int operationType;
    @ApiModelProperty(value = "orderId")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long orderId;


        public static final String STOCK_OPERATION_LOG_ID = "stock_operation_log_id";

        public static final String WAREHOUSE_PRODUCT_RELATION_ID = "warehouse_product_relation_id";

        public static final String CREATE_ID = "create_id";

        public static final String CREATE_TIME = "create_time";

        public static final String ORIGIN_QUANTITY = "origin_quantity";

        public static final String DESTINATION_QUANTITY = "destination_quantity";

        public static final String CHANGE_QUANTITY = "change_quantity";


        public enum OperationTypeEnum{
//  case 2:
//            operationTypeName = "库存调拨";
//                break;
//            case 3:
//            operationTypeName = "退货";
//                break;
//            case 4:
//            operationTypeName = "手工调整";
//                break;
//                case 1:    default:
//            operationTypeName = "订单发货";
                DELIVER(1, "订单发货"),
            RESTORE(2, "库存调拨"),
            REFUND(3, "退货"),
            MANUAL(4, "手工调整");

                /**
                 * 用户状态
                 */
                private Integer status;
                /**
                 * 说明
                 */
                private String desc;

            OperationTypeEnum(Integer status, String desc) {
                    this.status = status;
                    this.desc = desc;
                }

                public Integer getStatus() {
                    return status;
                }

                public void setStatus(Integer status) {
                    this.status = status;
                }

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

            }

}
