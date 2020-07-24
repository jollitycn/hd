package com.insigma.ordercenter.logistics.best.sdk.tmsWaybillApply.response;

import java.util.List;

public class Waybills {
	private List<Waybill> waybill;

    public List<Waybill>  getWaybill()
    {
        return this.waybill;
    }

    public void setWaybill(List<Waybill>  value)
    {
        this.waybill = value;
    }

}
