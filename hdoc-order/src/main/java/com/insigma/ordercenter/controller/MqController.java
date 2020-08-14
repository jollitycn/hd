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
//        String str = "{\"signId\":\"hdnm\",\"xtwldm\":\"00045\",\"tid\":\"200723173549840\",\"receiver_adress\":\"olp\",\"receiver_phone\":\"18898980909\",\"receiver_zip\":\"\",\"seller_nick\":\"平台\",\"created\":\"2020-07-23 17:35:49\",\"buyer_nick\":\"19958719050\",\"receiver_city\":\"230400\",\"r_city\":\"230400\",\"receiver_mobile\":\"18898980909\",\"receiver_name\":\"离家\",\"buyer_email\":\"\",\"receiver_state\":\"230000\",\"r_state\":\"230000\",\"receiver_district\":\"230405\",\"r_district\":\"230405\",\"post_fee\":\"0.00\",\"seller_memo\":\"\",\"buyer_message\":\"\",\"buyer_alipay_no\":\"\",\"pay_time\":\"2020-07-23 17:35:49\",\"pay_status\":\"Y\",\"invoice_name\":\"\",\"invoice_type\":\"\",\"inv_payee\":0.0,\"inv_content\":\"\",\"source\":\"HDNM_MALL2\",\"trade_payment\":0.00,\"alipay_no\":\"\",\"received_payment\":0.00,\"data\":[{\"xtwldm\":\"00045\",\"tid\":\"200723173549840\",\"oid\":\"233\",\"outer_iid\":\"233_0\",\"outer_sku_id\":\"LY100011\",\"num_iid\":233,\"num\":1,\"price\":0.0,\"payment\":0.0,\"divide_order_fee\":0.0,\"discount_fee\":0.0,\"title\":\"有机dada油茶籽油500ml*2礼盒装K2100\",\"proportion\":0.00},{\"xtwldm\":\"00045\",\"tid\":\"200723173549840\",\"oid\":\"234\",\"outer_iid\":\"234_0\",\"outer_sku_id\":\"LY100021\",\"num_iid\":234,\"num\":2,\"price\":0.0,\"payment\":0.0,\"divide_order_fee\":0.0,\"discount_fee\":0.0,\"title\":\"有机dada大米5kg普通装K100\",\"proportion\":0.00}],\"payType\":{\"xtwldm\":\"00045\",\"tid\":\"200723173549840\",\"pay_type\":\"AA\",\"pay_fee\":0.00},\"card\":[{\"tid\":\"200723173549840\",\"card_no\":\"\"}]}";
        String str = "null";
        try {
            message = new Message("e-storedvalue-card-dispatch",
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
    public static void sendMSG( SendParaDTO sendMqDTO) throws Exception{

        sendMqDTO.setCreateTime(new Date());
        String s = "http://10.47.2.166:9072/clit-service/rocketMQ/sendMessage";
        OutputStreamWriter out = null;
        BufferedReader br = null;
        String result = "";
        try {
            URL url = new URL(s);
            //打开和url之间的连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //请求方式
            conn.setRequestMethod("POST");
            //设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");

            conn.setDoOutput(true);
            conn.setDoInput(true);

            //获取URLConnection对象对应的输出流
            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            //发送请求参数即数据
            out.write(sendMqDTO.toString());
            //flush输出流的缓冲
            out.flush();

            //获取URLConnection对象对应的输入流
            InputStream is = conn.getInputStream();
            //构造一个字符流缓存
            br = new BufferedReader(new InputStreamReader(is));
            String str = "";
            while ((str = br.readLine()) != null){
                result += str;
            }
            System.out.println(result);
            //关闭流
            is.close();
            conn.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (out != null){
                    out.close();
                }
                if (br != null){
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        SendParaDTO sendMqDTO = new SendParaDTO();
        sendMSG(sendMqDTO);
    }


}