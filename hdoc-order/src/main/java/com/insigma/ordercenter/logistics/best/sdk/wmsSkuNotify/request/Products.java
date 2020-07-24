package com.insigma.ordercenter.logistics.best.sdk.wmsSkuNotify.request;

import java.util.List;

public class Products {
	private List<Product> product;

    public List<Product>  getProduct()
    {
        return this.product;
    }

    public void setProduct(List<Product>  value)
    {
        this.product = value;
    }

}
