package com.insigma.ordercenter.logistics.best.sdk.tmsWaybillApply.request;

public class Order {
	private String customerOrderCode;
	private double parcelWeight;
	private String note;
	private Sender sender;
	private Receiver receiver;

    public String getCustomerOrderCode()
    {
        return this.customerOrderCode;
    }

    public void setCustomerOrderCode(String value)
    {
        this.customerOrderCode = value;
    }

    public double getParcelWeight()
    {
        return this.parcelWeight;
    }

    public void setParcelWeight(double value)
    {
        this.parcelWeight = value;
    }

    public String getNote()
    {
        return this.note;
    }

    public void setNote(String value)
    {
        this.note = value;
    }

    public Sender getSender()
    {
        return this.sender;
    }

    public void setSender(Sender value)
    {
        this.sender = value;
    }

    public Receiver getReceiver()
    {
        return this.receiver;
    }

    public void setReceiver(Receiver value)
    {
        this.receiver = value;
    }


}
