package com.balashoff.mqtt.service;

import com.balashoff.ha.components.Convertor;
import com.balashoff.mqtt.MqttCustomClient;
import com.balashoff.mqtt.topic.MqttTopicRecord;

import java.util.List;
import java.util.Map;

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
        MqttCustomClient pubKnxClient = getClients().get(false);
        for (MqttTopicRecord topic : getTopics()) {
            subHAClient.subscribeTopic(topic.haTopic(), s -> {
                String updateMessage = Convertor.fromHaToKnx(topic);
                pubKnxClient.pushMessage(topic.knxTopic(), updateMessage);
            });
        }
    }
}