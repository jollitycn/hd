package com.insigma.ordercenter.entity.hd;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

;
;
/**;
 * @author youwk;
 * @ClassName HdOrder;
 * @description TODO;
 * @date 2020/7/24 14:36;
 * @Version 1.0;
 */;
 @Data
public class HdOrder implements Serializable {

    //签名
    private String signId;

    //网店代码（传‘00051’）
    private String xtwldm;
    //平台订单号码
    private String tid;
    //收货地址
    private String receiver_adress;
    //收货电话
    private String receiver_phone;
    //收货邮编
    private String receiver_zip;
    //网店名称
    private String seller_nick;
    //下单日期
    private String created;
    //客户代码(平台注册用户名)
    private String buyer_nick;
    //收货城市（例如：苏州市）
    private String receiver_city;
    //收货城市代码（例如：320500）
    private String r_city;
    //收货手机(收货手机和电话有一个必填)
    private String receiver_mobile;
    //收货人
    private String receiver_name;
    //邮箱
    private String buyer_email;
    //省份（例如：江苏省）
    private String receiver_state;
    //省份代码（例如：320000）
    private String r_state;
    //地区（例如：昆山市）
    private String receiver_district;
    //地区代码（例如：320583）
    private String r_district;
    //运费
    private String post_fee;
    //客户备注
    private String seller_memo;
    //买家留言
    private String buyer_message;
    //支付宝帐号
    private String buyer_alipay_no;
    //付款日期yyyy-mm-dd hh24:mi:ss
    private String pay_time;
    //付款标志（N/Y）
    private String pay_status;
    //发票抬头
    private String invoice_name;
    //发票类型
    private String invoice_type;
    //开票金额
    private BigDecimal inv_payee;
    //开票内容
    private String inv_content;
    //订单来源（根据平台传入， HDNM_MALL2）
    private String source;
    //支付金额
    private BigDecimal trade_payment;
    //支付宝流水号
    private String alipay_no;
    //实际收款金额
    private BigDecimal received_payment;

    //订单明细
    private List<HdOrderDetail> data;

    private HdOrderPayType payType;

    private List<HdOrderCard> card;

}