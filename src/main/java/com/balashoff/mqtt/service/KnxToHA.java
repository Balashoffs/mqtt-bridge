package com.balashoff.mqtt.service;


import com.balashoff.ha.components.Convertor;
import com.balashoff.mqtt.MqttCustomClient;
import com.balashoff.mqtt.topic.MqttTopicRecord;

import java.util.List;
import java.util.Map;

public class KnxToHA extends AbstractSmartService{


    public KnxToHA(Map<Boolean, MqttCustomClient> clients, List<MqttTopicRecord> topics) {
        super(clients, topics);
    }

    @Override
    public void run() {

    }

    @Override
    protected void init() {
        MqttCustomClient pubHAClient = getClients().get(false);
        MqttCustomClient subKnxClient = getClients().get(false);
        for (MqttTopicRecord topic : getTopics()) {
            subKnxClient.subscribeTopic(topic.knxTopic(), s -> {
                String updateMessage = Convertor.fromKnxToHa(topic, 2);
                pubHAClient.pushMessage(topic.haTopic(), updateMessage);
            });
        }
    }
}
