package com.balashoff.mqtt.service;


import com.balashoff.mqtt.MqttCustomClient;
import com.balashoff.mqtt.topic.MqttTopicRecord;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.Map;

import static com.balashoff.ha.components.Convertor.forTopic;

@Log4j2
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
        MqttCustomClient subKnxClient = getClients().get(true);
        for (MqttTopicRecord topic : getTopics()) {
            subKnxClient.subscribeTopic(topic.knxTopic(), s -> {
                log.info("[KnxToHA] Get message: {}", s);
                String updateMessage = forTopic(topic.haComponent(), topic.haDeviceCLass(), s);
                log.info("[KnxToHA] convert to message: {}", updateMessage);
                pubHAClient.pushMessage(topic.haTopic(), updateMessage);
            });
        }
        while (true){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                log.error(e.getMessage(), e.getCause());
            }
        }
    }
}
