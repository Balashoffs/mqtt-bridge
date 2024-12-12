package com.balashoff.mqtt.service;

import com.balashoff.mqtt.MqttCustomClient;
import com.balashoff.mqtt.topic.MqttTopicRecord;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.Map;

import static com.balashoff.ha.components.Convertor.forTopic;

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
            subHAClient.subscribeTopic(topic.haTopic(), s -> {
                log.info("[KnxToHA] Get message: {}", s);
                String updateMessage = forTopic(topic.haComponent(), topic.haDeviceCLass(), s);
                log.info("[KnxToHA] convert to message: {}", updateMessage);
                pubKnxClient.pushMessage(topic.knxTopic(), updateMessage);
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