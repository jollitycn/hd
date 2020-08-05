package com.insigma.ordercenter.constant;

public interface OrderStatus {

    /**
     * 订单状态
     * 订单状态（0：新建状态，1：手动审核状态，2：待审核状态，3：审核异常状态，4：待出库状态，
     * 5：已出库状态，6：冻结状态，7：发货异常状态，8：已完成状态，9：取消状态）
     */
    int ORDER_ZERO = 0;
    int ORDER_ONE = 1;
    int ORDER_TWO = 2;
    int ORDER_THREE = 3;
    int ORDER_FOUR =4;
    int ORDER_FIVE =5;
    int ORDER_SIX =6;
    int ORDER_SEVEN =7;
    int ORDER_EIGHT =8;
    int ORDER_NINE =9;

    /**
     * 发货单状态
     * 发货单状态（0：待出库，1：待取货，2：已发货，3：冻结，4：取消 5：拒收 6:异常 7：已完成）
     */
    int SHIPPING_ORDER_ZERO = 0;
    int SHIPPING_ORDER_ONE = 1;
    int SHIPPING_ORDER_TWO = 2;
    int SHIPPING_ORDER_THREE = 3;
    int SHIPPING_ORDER_FOUR =4;
    int SHIPPING_ORDER_FIVE =5;
    int SHIPPING_ORDER_SIX =6;
    int SHIPPING_ORDER_SEVEN =7;

}
