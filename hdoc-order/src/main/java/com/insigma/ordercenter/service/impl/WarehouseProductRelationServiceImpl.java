package com.insigma.ordercenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.insigma.ordercenter.entity.WarehouseProductRelation;
import com.insigma.ordercenter.entity.vo.ProductStockInfoVO;
import com.insigma.ordercenter.mapper.WarehouseProductRelationMapper;
import com.insigma.ordercenter.service.IWarehouseProductRelationService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * <p>
 * 仓库商品关联表 服务实现类
 * </p>
 *
 * @author LiuHao
 * @since 2020-07-08
 */
@Service
public class WarehouseProductRelationServiceImpl extends ServiceImpl<WarehouseProductRelationMapper, WarehouseProductRelation> implements IWarehouseProductRelationService {

    @Override
    public Integer getTotalStock(Long productId) {

        return baseMapper.getTotalStock(productId);
    }

    @Override
    public List<ProductStockInfoVO> getProductStockInfo(Long productId) {

        return baseMapper.getProductStockInfo(productId);
    }



public static void main(String[] args) {
        String randomStr = "2d4s";
        String clientFlag = "test";
        String strData1 = " {\n" +
                "        \"clientFlag\": \"test\",\n" +
                "        \"mailNo\": \"A0314324743040\",\n" +
                "        \"orderNo\": \"Test0001\",\n" +
                "        \"busType\": \"4\",\n" +
                "        \"openType\": \"1\",\n" +
                "        \"originalNo\": null,\n" +
                "        \"serviceAgent\": \"H1\",\n" +
                "        \"goodsName\": \"手机\",\n" +
                "        \"goodsNum\": \"2\",\n" +
                "        \"goodsVolume\": \"8*4*3\",\n" +
                "        \"goodsWeight\": \"7.1\",\n" +
                "        \"remarks\": \"配送前请先电话联系\",\n" +
                "        \"invoiceState\": \"1\",\n" +
                "        \"dataFlag\": \"BJ01\",\n" +
                "        \"sendName\": \"张三\",\n" +
                "        \"sendPro\": \"北京市\",\n" +
                "        \"sendCity\": \"北京市\",\n" +
                "        \"sendDistrict\": \"顺义区\",\n" +
                "        \"sendStreet\": null,\n" +
                "        \"sendAddress\": \"空港物流园 8 街 3 号\",\n" +
                "        \"sendIdentityCode\": null,\n" +
                "        \"sendUnit\": \"*****商城\",\n" +
                "        \"sendMobile\": \"13801088567\",\n" +
                "        \"sendPhone\": \"010-69470033\",\n" +
                "        \"receiveName\": \"李老师\",\n" +
                "        \"receivePro\": \"山东\",\n" +
                "        \"receiveCity\": \"济南市\",\n" +
                "        \"receiveDistrict\": \"市中区\",\n" +
                "        \"receiveStreet\": null,\n" +
                "        \"receiveAddress\": \"王官庄东区工业园北院 13 号\",\n" +
                "        \"receiveIdentityCode\": null,\n" +
                "        \"receiveUnit\": \"*****有限公司\",\n" +
                "        \"receiveMobile\": \"18615591688\",\n" +
                "        \"receivePhone\": \"021-45574847\",\n" +
                "        \"insuranceMode\": \"2072\",\n" +
                "        \"insuranceType\": \"2067\",\n" +
                "        \"goodsValue\": \"1995\",\n" +
                "        \"codFlag\": \"1\",\n" +
                "        \"codAmount\": \"1995\",\n" +
                "        \"toPay\": null,\n" +
                "        \"payMode\": null,\n" +
                "        \"pickupTime\": \"2017-12-2100: 01: 00\",\n" +
                "        \"clientOperatecode\": null,\n" +
                "        \"extendedInfo\": {\n" +
                "            \"aa\": \"拓展 1\",\n" +
                "            \"bb\": \"拓展 2\"\n" +
                "        },\n" +
                "        \"orderPackages\": [\n" +
                "            {\n" +
                "                \"packageNo\": \"\",\n" +
                "                \"packageBarcode\": \"FC2000001\",\n" +
                "                \"packageWeight\": null,\n" +
                "                \"packageVolume\": null,\n" +
                "                \"packageAmount\": null,\n" +
                "                \"packageInfo\": null,\n" +
                "                \"item\": [\n" +
                "                    {\n" +
                "                        \"itemNo\": null,\n" +
                "                        \"itemName\": null,\n" +
                "                        \"itemNumber\": null,\n" +
                "                        \"itemValue\": null,\n" +
                "                        \"itemWeight\": null,\n" +
                "                        \"itemDesc\": null\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            {\n" +
                "                \"packageNo\": \"\",\n" +
                "                \"packageBarcode\": \"FC2000002\",\n" +
                "                \"packageWeight\": null,\n" +
                "                \"packageVolume\": null,\n" +
                "                \"packageAmount\": null,\n" +
                "                \"packageInfo\": null,\n" +
                "                \"item\": [\n" +
                "                    {\n" +
                "                        \"itemNo\": null,\n" +
                "                        \"itemName\": null,\n" +
                "                        \"itemNumber\": null,\n" +
                "                        \"itemValue\": null,\n" +
                "                        \"itemWeight\": null,\n" +
                "                        \"itemDesc\": null\n" +
                "                    }\n" +
                "                ]\n" +
                "            }\n" +
                "        ]\n" +
                "    }";
        String strData2 = "{" +
                "\"clientFlag\": \"test\",\n" +
                "\"key\": \"DH100621\",\n" +
                "\"applynum\": 5\n" +
                "}";
        String strData3 = "[{\"clientFlag\": \"test\",\"dataFlag \": \"shangHai\",\"mailNo\": \"A000011545343\", \"orderNo\": \"JY784373\"},{\"clientFlag\": \"test\",\"dataFlag \": \"shangHai\",\"mailNo\": \"A000011545343\", \"orderNo\": \"JY784373\"}]";
        String clientSecretKey = "aafc04a1bacb487fa8d03f2a7bfdb555";
        String constant = "z宅J急S送g";
        String randomStr2 = "4drg";
        String str = randomStr+clientFlag+strData3+clientSecretKey+constant+randomStr2;

        try {
            String strMd5 = DigestUtils.md5Hex(str.getBytes("UTF-8")).replace("-",
                    "").toLowerCase().substring(7, 28);
            String md5 = randomStr + strMd5+ randomStr2;
            System.out.println(md5);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
