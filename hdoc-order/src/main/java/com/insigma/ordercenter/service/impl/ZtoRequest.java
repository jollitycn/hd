package com.insigma.ordercenter.service.impl;


import java.util.List;

public class ZtoRequest  {
    private List<String> billCodes;
    private String siteId;

    public ZtoRequest ( ) {
    }

    public List<String> getBillCodes ( ) {
        return this.billCodes;
    }

    public String getSiteId ( ) {
        return this.siteId;
    }

    public void setBillCodes ( List<String> billCodes ) {
        this.billCodes = billCodes;
    }

    public void setSiteId ( String siteId ) {
        this.siteId = siteId;
    }

    @Override
    public boolean equals ( final Object o ) {
        if (o == this) return true;
        if (!(o instanceof ZtoRequest)) return false;
        final ZtoRequest other = (ZtoRequest) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$billCodes = this.getBillCodes();
        final Object other$billCodes = other.getBillCodes();
        if (this$billCodes == null ? other$billCodes != null : !this$billCodes.equals(other$billCodes)) return false;
        final Object this$siteId = this.getSiteId();
        final Object other$siteId = other.getSiteId();
        if (this$siteId == null ? other$siteId != null : !this$siteId.equals(other$siteId)) return false;
        return true;
    }

    protected boolean canEqual ( final Object other ) {
        return other instanceof ZtoRequest;
    }

    @Override
    public int hashCode ( ) {
        final int PRIME = 59;
        int result = 1;
        final Object $billCodes = this.getBillCodes();
        result = result * PRIME + ($billCodes == null ? 43 : $billCodes.hashCode());
        final Object $siteId = this.getSiteId();
        result = result * PRIME + ($siteId == null ? 43 : $siteId.hashCode());
        return result;
    }

    @Override
    public String toString ( ) {
        return "ZtoRequest(billCodes=" + this.getBillCodes() + ", siteId=" + this.getSiteId() + ")";
    }
}
