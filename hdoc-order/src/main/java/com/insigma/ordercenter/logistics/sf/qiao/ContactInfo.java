package com.insigma.ordercenter.logistics.sf.qiao;

import com.insigma.ordercenter.entity.vo.BaseVO;
import lombok.Data;

/**
 * @author Administrator
 */
@Data
public class ContactInfo  extends BaseVO {

//    1	contactType	Number(1)	是	地址类型：
//            1，寄件方信息 2，到件方信息
//2	company	String(100)	条件	公司名称
//3	contact	String(100)	条件	联系人
//4	tel	String(20)	条件	联系电话
//5	mobile	String(20)	否	手机
//6	zoneCode	String(30)	条件	城市代码或国家代码，如果是


private String company;
    private String mobile;
    private String zoneCode;
    private String address;
    private String contact;
    private String province;
    private String city;
    // 1，寄件方信息 2，到件方信息
    private int contactType;
    private String county;
    private String country;
    private String postCode;
    private String taxNo;
    private String tel;
}
