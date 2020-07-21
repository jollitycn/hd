package com.insigma.ordercenter.logistics.sf.qiao;

import com.educiot.common.base.BaseVO;
import lombok.Data;

@Data
public class RouteLabelData  extends BaseVO {
    private String waybillNo;
    private String destDeptCode;
    private String sourceTeamCode;
    private String sourceDeptCode;
    private String sourceCityCode;
    private String sourceTransferCode;
    private String destCityCode;
    private String destDeptCodeMapping;
    private String destTeamCode;
    private String destTeamCodeMapping;
    private String limitTypeCode;
    private String destRouteLabel;
    private String cargoTypeCode;
    private String destTransferCode;
    private String proName;
    private String expressTypeCode;
    private String codingMapping;
    private String codingMappingOut;
    //String(30)	否	XB标志 0:不需要打印XB
    //1:需要打印XB
    private String xbFlag;

    //String(60)	否	打印标志
    //    返回值总共有9位,每位只
    //    有0和1两种,0表示按丰密
    //    面单默认的规则,1是显示,
    //    顺序如下,如111110000
    //    表示打印寄方姓名、寄方
    //    电话、寄方公司名、寄方
    //    地址和重量,收方姓名、收
    //    方电话、收方公司和收方
    //            地址按丰密面单默认规则
    //1:寄方姓名
    //2:寄方电话
    //3:寄方公司名
    //4:寄方地址
    //5:重量
    //6:收方姓名
    //7:收方电话
    //8:收方公司名
    //9:收方地址
    private String printFlag;

    //String(600)	否	二维码
    //    根据规则生成字符串信息,
    //    格式为MMM={'k1':'(目的
    //        地中转场代码)','k2':'(目的
    //        地原始网点代码)','k3':'(目
    //        的地单元区域)','k4':'(附件
    //        通过三维码(express_type_code、 limit_type_code、 cargo_type_code)映射时效类型)','k5':'(运单
    //        号)','k6':'(AB标识)','k7':'(
    //                校验码)'}
    private String twoDimensionCode;
    private String proCode;
    private String printIcon;
    private String abFlag;
    private String errMsg;
    private String destPortCode;
    private String destCountry;
    private String destPostCode;
    private String goodsValueTotal;
    private String currencySymbol;
    private String goodsNumber;
}
