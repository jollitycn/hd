package com.insigma.ordercenter.logistics.best.sdk.twSoNotify.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class Sender implements Serializable {
	private String name;
	private String province;
	private String city;
	private String district;
	private String address;
	private String contactName;
	private String phone;
	private String earlyPickupTime;
	private String latePickupTime;
}
