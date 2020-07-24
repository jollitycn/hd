package com.insigma.ordercenter.logistics.best.sdk.twSoNotify.request;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ServiceList implements Serializable {
    private List<Service> service;
}
