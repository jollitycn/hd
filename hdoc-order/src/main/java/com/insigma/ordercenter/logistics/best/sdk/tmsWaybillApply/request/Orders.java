package com.insigma.ordercenter.logistics.best.sdk.tmsWaybillApply.request;

import java.util.List;

public class Orders {
	private List<Order> order;

    public List<Order>  getOrder()
    {
        return this.order;
    }

    public void setOrder(List<Order>  value)
    {
        this.order = value;
    }

}
