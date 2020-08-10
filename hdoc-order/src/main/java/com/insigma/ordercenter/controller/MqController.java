package com.insigma.ordercenter.controller;

import com.insigma.ordercenter.base.Result;
import com.insigma.ordercenter.entity.Warehouse;
import com.insigma.ordercenter.entity.dto.SendMqDTO;
import com.insigma.ordercenter.entity.dto.SendParaDTO;
import com.insigma.ordercenter.utils.JsonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.apache.shiro.crypto.hash.Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author youwk
 * @ClassName MqController
 * @description TODO
 * @date 2020/7/23 11:24
 * @Version 1.0
 */
@Api(tags = {"消息队列测试"})
@RequestMapping("/mq")
@RestController
public class MqController {

    @Autowired
    private DefaultMQProducer producer;

    @PutMapping("/send")
    @ApiOperation("发送消息")
    public Result<?> send(String msg) {
        Message message = null;
        String str = "{\"signId\":\"hdnm\",\"xtwldm\":\"00045\",\"tid\":\"200723173549840\",\"receiver_adress\":\"olp\",\"receiver_phone\":\"18898980909\",\"receiver_zip\":\"\",\"seller_nick\":\"平台\",\"created\":\"2020-07-23 17:35:49\",\"buyer_nick\":\"19958719050\",\"receiver_city\":\"230400\",\"r_city\":\"230400\",\"receiver_mobile\":\"18898980909\",\"receiver_name\":\"离家\",\"buyer_email\":\"\",\"receiver_state\":\"230000\",\"r_state\":\"230000\",\"receiver_district\":\"230405\",\"r_district\":\"230405\",\"post_fee\":\"0.00\",\"seller_memo\":\"\",\"buyer_message\":\"\",\"buyer_alipay_no\":\"\",\"pay_time\":\"2020-07-23 17:35:49\",\"pay_status\":\"Y\",\"invoice_name\":\"\",\"invoice_type\":\"\",\"inv_payee\":0.0,\"inv_content\":\"\",\"source\":\"HDNM_MALL2\",\"trade_payment\":0.00,\"alipay_no\":\"\",\"received_payment\":0.00,\"data\":[{\"xtwldm\":\"00045\",\"tid\":\"200723173549840\",\"oid\":\"233\",\"outer_iid\":\"233_0\",\"outer_sku_id\":\"LY100011\",\"num_iid\":233,\"num\":1,\"price\":0.0,\"payment\":0.0,\"divide_order_fee\":0.0,\"discount_fee\":0.0,\"title\":\"有机dada油茶籽油500ml*2礼盒装K2100\",\"proportion\":0.00},{\"xtwldm\":\"00045\",\"tid\":\"200723173549840\",\"oid\":\"234\",\"outer_iid\":\"234_0\",\"outer_sku_id\":\"LY100021\",\"num_iid\":234,\"num\":2,\"price\":0.0,\"payment\":0.0,\"divide_order_fee\":0.0,\"discount_fee\":0.0,\"title\":\"有机dada大米5kg普通装K100\",\"proportion\":0.00}],\"payType\":{\"xtwldm\":\"00045\",\"tid\":\"200723173549840\",\"pay_type\":\"AA\",\"pay_fee\":0.00},\"card\":[{\"tid\":\"200723173549840\",\"card_no\":\"\"}]}";
//        String str = "null";
        try {
            message = new Message("topic-order",
                    "TagA",
                   str.getBytes(RemotingHelper.DEFAULT_CHARSET));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            producer.send(message, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.println("消息发送成功");
                }

                @Override
                public void onException(Throwable e) {
                    e.printStackTrace();
                    System.out.println("消息发送异常");
                }
            });
        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Result.success();
    }

//    @PutMapping("/sendMSG")
//    @ApiOperation("发送消息")
    public  void sendMSG( SendParaDTO sendMqDTO) throws Exception{
        Message message = null;
        sendMqDTO.setCreateTime(new Date());
        try {
            message = new Message("topic-order",
                    "TagA",
                    sendMqDTO.toString().getBytes(RemotingHelper.DEFAULT_CHARSET));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            producer.send(message, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.println("消息发送成功");
                }

                @Override
                public void onException(Throwable e) {
                    e.printStackTrace();
                    System.out.println("消息发送异常");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        //return Result.success();
    }

    public static void main(String[] args) throws Exception {
        SendParaDTO sendMqDTO = new SendParaDTO();

        //sendMSG(sendMqDTO);
    }


    }
