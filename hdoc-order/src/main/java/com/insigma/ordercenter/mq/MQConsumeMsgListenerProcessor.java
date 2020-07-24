package com.insigma.ordercenter.mq;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.util.ImageUtils;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.util.CollectionUtils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Component
@Slf4j
public class MQConsumeMsgListenerProcessor implements MessageListenerConcurrently {

  public static final String TOPIC = "TopicTest";

  @Override
  public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) { if (CollectionUtils.isEmpty(msgs)) {
      log.info("接收到的消息为空，不处理，直接返回成功");
      return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    } 
    MessageExt messageExt = msgs.get(0);
    if (messageExt.getTopic().equals(TOPIC) &&   messageExt.getTags().equals("TagA")) {
      int reconsume = messageExt.getReconsumeTimes();
      log.info("正在消费信息，长度为：{}", (messageExt.getBody()).length);
      log.info(new String(messageExt.getBody()));
      if (reconsume == 3) {
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
      }

      String body = new String(messageExt.getBody());
      JSONObject jsonObject = JSONObject.parseObject(body);
      SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
      String timestamp = format.format(new Date());
      String fileName = timestamp + (new Random()).nextInt(1000);
    }
    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
  }
}