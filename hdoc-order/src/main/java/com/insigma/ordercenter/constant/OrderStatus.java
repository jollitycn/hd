package com.insigma.ordercenter.constant;

public interface OrderStatus {

    /**
     * 订单状态
     * 订单状态（0：新建状态，1：手动审核状态，2：待审核状态，3：审核异常状态，4：待出库状态，
     * 5：已出库状态，6：冻结状态，7：发货异常状态，8：已完成状态，9：取消状态）
     */
    int CREATED = 0;
    int HANDLE = 1;
    int UNCHECKED = 2;
    int CHECK_ERROR = 3;
    int WAIT_FOR_WAREHOUSE =4;
    int OUT_OF_WAREHOUSE =5;
    int FROZEN = 6;
    int SHIPPING_ERROR =7;
    int FINISHED =8;
    int CANCELED =9;

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
