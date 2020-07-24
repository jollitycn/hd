package com.insigma.ordercenter.logistics.best.sdk.tmsGpsQuery.response;



public class HistoryGpsInfos {
	private double longitude;
	private double latitude;
	private long createTime;

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

    public long getCreateTime()
    {
        return this.createTime;
    }

    public void setCreateTime(long value)
    {
        this.createTime = value;
    }


}
