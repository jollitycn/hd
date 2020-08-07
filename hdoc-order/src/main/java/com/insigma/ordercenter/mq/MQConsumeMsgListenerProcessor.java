package com.insigma.ordercenter.mq;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.insigma.ordercenter.entity.*;
import com.insigma.ordercenter.entity.hd.HdOrder;
import com.insigma.ordercenter.entity.hd.HdOrderDetail;
import com.insigma.ordercenter.feign.RegionService;
import com.insigma.ordercenter.service.IOrderService;
import com.insigma.ordercenter.service.IOriginalOrderDetailService;
import com.insigma.ordercenter.service.IOriginalOrderService;
import com.insigma.ordercenter.service.IProductService;
import com.insigma.ordercenter.utils.DateUtils;
import com.insigma.ordercenter.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Component
@Slf4j
public class MQConsumeMsgListenerProcessor implements MessageListenerConcurrently {

    public static final String TOPIC = "TopicTest";

    @Autowired
    private IOriginalOrderService originalOrderService;

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IOriginalOrderDetailService originalOrderDetailService;

    @Autowired
    private IProductService productService;

    @Autowired
    private RegionService regionService;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        if (CollectionUtils.isEmpty(msgs)) {
            log.info("接收到的消息为空，不处理，直接返回成功");
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
        MessageExt messageExt = msgs.get(0);
        try {
            int reconsume = messageExt.getReconsumeTimes();
            log.info("reconsumeTimes = {}",reconsume );
            log.info("正在消费信息，长度为：{}", (messageExt.getBody()).length);
            log.info(new String(messageExt.getBody()));
            if (reconsume == 3) {
                log.warn("重复消费数据，不要搞事");
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
            String body = new String(messageExt.getBody());
            //消费数据序列换成卡兑换订单对象
            HdOrder hdOrder = JsonUtil.jsonToBean(body, HdOrder.class);
            if (null == hdOrder) {
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
            if (null == hdOrder.getCard() || hdOrder.getCard().size() ==0) {
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
            //写入原始订单表
            OriginalOrder originalOrder = new OriginalOrder();
            originalOrder.setAddress(hdOrder.getReceiver_adress());
            originalOrder.setConsumerName(hdOrder.getReceiver_name());
            originalOrder.setOrderStatus(0);
            originalOrder.setOriginalOrderNo(hdOrder.getTid());
            originalOrder.setOrderTime(hdOrder.getCreated());
            originalOrder.setCreateTime(LocalDateTime.now());
            originalOrder.setMobilePhone(hdOrder.getReceiver_mobile());
            originalOrder.setOrderNo(orderService.generateOrderNo(1L));
            originalOrder.setRemark(hdOrder.getBuyer_message());
            originalOrder.setShopId(1L);
            if (StringUtils.isNotBlank(hdOrder.getR_state()) && StringUtils.isNotBlank(hdOrder.getR_city())
                    && StringUtils.isNotBlank(hdOrder.getR_district())) {
                SysRegion province = regionService.detail(hdOrder.getR_state());
                SysRegion city = regionService.detail(hdOrder.getR_city());
                SysRegion district = regionService.detail(hdOrder.getR_district());
                originalOrder.setProvince(province.getName());
                originalOrder.setCity(city.getName());
                originalOrder.setDistrict(district.getName());
            } else if (StringUtils.isNotBlank(hdOrder.getReceiver_state()) && StringUtils.isNotBlank(hdOrder.getReceiver_city()) && StringUtils.isNotBlank(hdOrder.getReceiver_district())) {
                originalOrder.setProvince(hdOrder.getReceiver_state());
                originalOrder.setCity(hdOrder.getReceiver_city());
                originalOrder.setDistrict(hdOrder.getReceiver_district());
            }
            originalOrder.setAddress(hdOrder.getReceiver_adress());
            //保存提货卡号
            originalOrder.setCardNo(hdOrder.getCard().get(0).getCard_no());
            originalOrderService.save(originalOrder);
            //写入原始订单详情
            List<HdOrderDetail> hdOrderDetails = hdOrder.getData();
            for (HdOrderDetail hdOrderDetail : hdOrderDetails) {
                OriginalOrderDetail detail =  new OriginalOrderDetail();
                detail.setOriginalOrderId(originalOrder.getOriginalOrderId());
                //通过商品编号 查询到我们系统的商品编号
                Product product = productService.getOne(Wrappers.<Product>lambdaQuery().eq(Product::getProductNo, hdOrderDetail.getOuter_sku_i()));
                if (null != product) {
                    detail.setProductId(product.getProductId());
                    detail.setProductType(product.getProductType());
                }
                detail.setProductName(hdOrderDetail.getTitle());
                detail.setProductPrice(hdOrderDetail.getPrice());
                detail.setQuantity(hdOrderDetail.getNum());
                detail.setTotalPrice(hdOrderDetail.getPayment());
                detail.setDiscountFee(hdOrderDetail.getDiscount_fee());
                detail.setDivideOrderFee(hdOrderDetail.getDivide_order_fee());
                originalOrderDetailService.save(detail);
            }
            System.out.println(hdOrder);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}