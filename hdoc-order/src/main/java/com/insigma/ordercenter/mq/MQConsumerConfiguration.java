package com.insigma.ordercenter.mq;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
@Slf4j
public class MQConsumerConfiguration {

    @Value("${rocketmq.consumer.namesrvAddr}")
    private String namesrvAddr;
    @Value("${rocketmq.consumer.groupName}")
    private String groupName;
    @Value("${rocketmq.consumer.consumeThreadMin}")
    private int consumeThreadMin;
    @Value("${rocketmq.consumer.consumeThreadMax}")
    private int consumeThreadMax;
    @Value("${rocketmq.consumer.topics}")
    private String topics;
    @Value("${rocketmq.consumer.consumeMessageBatchMaxSize}")
    private int consumeMessageBatchMaxSize;
    @Autowired
    private MQConsumeMsgListenerProcessor mqMessageListenerProcessor;

    @Bean
    public DefaultMQPushConsumer getRocketMQConsumer() throws RocketMQException {
        if (StringUtils.isEmpty(this.groupName)) {
            throw new RocketMQException(RocketMQErrorEnum.PARAMM_NULL, "groupName is null !!!", Boolean.valueOf(false));
        }
        if (StringUtils.isEmpty(this.namesrvAddr)) {
            throw new RocketMQException(RocketMQErrorEnum.PARAMM_NULL, "namesrvAddr is null !!!", Boolean.valueOf(false));
        }
        if (StringUtils.isEmpty(this.topics)) {
            throw new RocketMQException(RocketMQErrorEnum.PARAMM_NULL, "topics is null !!!", Boolean.valueOf(false));
        }
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("youwk");
        consumer.setNamesrvAddr(this.namesrvAddr);
        consumer.setConsumeThreadMin(this.consumeThreadMin);
        consumer.setConsumeThreadMax(this.consumeThreadMax);
        consumer.registerMessageListener(this.mqMessageListenerProcessor);
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        consumer.setConsumeMessageBatchMaxSize(this.consumeMessageBatchMaxSize);

        try {
            String[] topicTagsArr = this.topics.split(";");
            for (String topicTags : topicTagsArr) {
                String[] topicTag = topicTags.split("~");
                consumer.subscribe("topic-order2", "*");
            }
            consumer.start();
            log.info("consumer is start !!! groupName:{},topics:{},namesrvAddr:{}", new Object[]{this.groupName, this.topics, this.namesrvAddr});
        } catch (MQClientException e) {
            log.error("consumer is start !!! groupName:{},topics:{},namesrvAddr:{}", new Object[]{this.groupName, this.topics, this.namesrvAddr, e});
            throw new RocketMQException(e);
        }
        return consumer;
    }
}