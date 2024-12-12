package com.balashoff.mqtt.service;


import com.balashoff.mqtt.MqttCustomClient;
import com.balashoff.mqtt.topic.MqttTopicRecord;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.Map;

import static com.balashoff.ha.components.Convertor.toHA;

@Log4j2
public class KnxToHA extends AbstractSmartService{


    public KnxToHA(Map<Boolean, MqttCustomClient> clients, List<MqttTopicRecord> topics) {
        super(clients, topics);
    }

    @Override
    public void run() {
        MqttCustomClient pubHAClient = getClients().get(false);
        MqttCustomClient subKnxClient = getClients().get(true);

        for (MqttTopicRecord topic : getTopics()) {
            String realKnxTopic = topic.topics().get(0).knxTopic();
            String realHaTopic = topic.topics().get(0).knxTopic();
            log.info("[KnxToHA] Get topic: {}", realKnxTopic);
            subKnxClient.subscribeTopic(realKnxTopic, s -> {
                log.info("[KnxToHA] Get message: {}", s);
                String updateMessage = toHA(topic.haComponent(), topic.haDeviceCLass(), s);
                log.info("[KnxToHA] convert to message: {}", updateMessage);
                log.info("[KnxToHA] output topic: {}", updateMessage);
                pubHAClient.pushMessage(realHaTopic, updateMessage);
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

    @Override
    protected void init() {

    }
}
