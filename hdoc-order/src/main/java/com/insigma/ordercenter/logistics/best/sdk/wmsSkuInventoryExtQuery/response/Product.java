package com.insigma.ordercenter.logistics.best.sdk.wmsSkuInventoryExtQuery.response;

public class Product {
	private String itemSkuCode;
	private String providerCode;
	private int totalQuantity;
	private int normalQuantity;
	private int defectiveQuantity;
	private int frozenQuantity;
	private Batchs batchs;

    public String getItemSkuCode()
    {
        return this.itemSkuCode;
    }

    public void setItemSkuCode(String value)
    {
        this.itemSkuCode = value;
    }

    public String getProviderCode()
    {
        return this.providerCode;
    }

    public void setProviderCode(String value)
    {
        this.providerCode = value;
    }

    public int getTotalQuantity()
    {
        return this.totalQuantity;
    }

    public void setTotalQuantity(int value)
    {
        this.totalQuantity = value;
    }

    public int getNormalQuantity()
    {
        return this.normalQuantity;
    }

    public void setNormalQuantity(int value)
    {
        this.normalQuantity = value;
    }

    public int getDefectiveQuantity()
    {
        return this.defectiveQuantity;
    }

    public void setDefectiveQuantity(int value)
    {
        this.defectiveQuantity = value;
    }

    public int getFrozenQuantity()
    {
        return this.frozenQuantity;
    }

    public void setFrozenQuantity(int value)
    {
        this.frozenQuantity = value;
    }

    public Batchs getBatchs()
    {
        return this.batchs;
    }

    public void setBatchs(Batchs value)
    {
        this.batchs = value;
    }


}
