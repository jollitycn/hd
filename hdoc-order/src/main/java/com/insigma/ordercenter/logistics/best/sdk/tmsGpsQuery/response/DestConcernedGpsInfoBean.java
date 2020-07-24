package com.insigma.ordercenter.logistics.best.sdk.tmsGpsQuery.response;



public class DestConcernedGpsInfoBean {
	private String address;
	private double longitude;
	private double latitude;
	private String status;

    public String getAddress()
    {
        return this.address;
    }

    public void setAddress(String value)
    {
        this.address = value;
    }

    public double getLongitude()
    {
        return this.longitude;
    }

    public void setLongitude(double value)
    {
        this.longitude = value;
    }

    public double getLatitude()
    {
        return this.latitude;
    }

    public void setLatitude(double value)
    {
        this.latitude = value;
    }

    public String getStatus()
    {
        return this.status;
    }

    public void setStatus(String value)
    {
        this.status = value;
    }


}
