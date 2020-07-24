package com.insigma.ordercenter.mq;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
@Slf4j
public class MQProducerConfiguration {

  @Value("${rocketmq.producer.groupName}")
  private String groupName;

  @Value("${rocketmq.producer.namesrvAddr}")
  private String namesrvAddr;

  @Value("${rocketmq.producer.maxMessageSize}")
  private Integer maxMessageSize;

  @Value("${rocketmq.producer.sendMsgTimeout}")
  private Integer sendMsgTimeout;

  @Value("${rocketmq.producer.retryTimesWhenSendFailed}")
  private Integer retryTimesWhenSendFailed;

  @Bean
  public DefaultMQProducer getRocketMQProducer() throws RocketMQException {
    if (StringUtils.isEmpty(this.groupName)) {
      throw new RocketMQException(RocketMQErrorEnum.PARAMM_NULL, "groupName is blank", Boolean.valueOf(false));
    }

    if (StringUtils.isEmpty(this.namesrvAddr)) {
      throw new RocketMQException(RocketMQErrorEnum.PARAMM_NULL, "nameServerAddr is blank", Boolean.valueOf(false));
    }

    DefaultMQProducer producer = new DefaultMQProducer(this.groupName);
    producer.setNamesrvAddr(this.namesrvAddr);

    if (this.maxMessageSize != null) {
      producer.setMaxMessageSize(this.maxMessageSize.intValue());
    }

    if (this.sendMsgTimeout != null) {
      producer.setSendMsgTimeout(this.sendMsgTimeout.intValue());
    }

    if (this.retryTimesWhenSendFailed != null) {
      producer.setRetryTimesWhenSendFailed(this.retryTimesWhenSendFailed.intValue());
    }

    log.info(String.format("RocketMQ producer is start ! groupName:[%s],namesrvAddr:[%s]", new Object[]{this.groupName, this.namesrvAddr}));
    return producer;
  }
}