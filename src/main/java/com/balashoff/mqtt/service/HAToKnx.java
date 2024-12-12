package com.balashoff.mqtt.service;

import com.balashoff.mqtt.MqttCustomClient;
import com.balashoff.mqtt.topic.MqttTopicRecord;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.Map;

import static com.balashoff.ha.components.Convertor.toKnx;

@Log4j2
public class HAToKnx extends AbstractSmartService {

    public HAToKnx(Map<Boolean, MqttCustomClient> clients, List<MqttTopicRecord> topics) {
        super(clients, topics);
    }

    @Override
    protected void init() {

    }

    @Override
    public void run() {
        MqttCustomClient subHAClient = getClients().get(false);
        MqttCustomClient pubKnxClient = getClients().get(true);
        for (MqttTopicRecord topic : getTopics()) {
            String realHaTopic = topic.topics().get(0).haTopic();
            subHAClient.subscribeTopic(realHaTopic, s -> {
                log.info("[KnxToHA] Get message: {}", s);
                ForKNX knxData = toKnx(topic, s);
                log.info("[KnxToHA] convert to message: {}", knxData.message());
                pubKnxClient.pushMessage(knxData.newTopic(), knxData.message());
            });
        }
        while (true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                log.error(e.getMessage(), e.getCause());
            }
        }
    }
}