package com.insigma.ordercenter.logistics.hdjf;

public class ZtkProperties {
    private String companyId;
    private String key;

    public ZtkProperties() {
    }

    public ZtkProperties(String appKey, String appSecret) {
        this.companyId = appKey;
        this.key = appSecret;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
