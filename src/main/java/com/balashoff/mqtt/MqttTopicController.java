package com.balashoff.mqtt;

import com.balashoff.mqtt.service.BridgeToHA;
import com.balashoff.mqtt.service.HAToKnx;
import com.balashoff.mqtt.service.KnxToHA;
import com.balashoff.mqtt.topic.MqttTopicRecord;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

@Log4j2
public class MqttTopicController {
    private final ExecutorService ex;
    private final Map<String, List<MqttTopicRecord>> topics;
    private final Map<Boolean, MqttCustomClient> clients;

    public MqttTopicController(Map<Boolean, MqttCustomClient> clients, Map<String, List<MqttTopicRecord>> topics) {
        ex = Executors.newFixedThreadPool(topics.size());
        this.topics = topics;
        this.clients = clients;
    }

    public void createTopicSubscribes(){
        for (Map.Entry<String, List<MqttTopicRecord>> entry : topics.entrySet()) {
            String key = entry.getKey();
            List<MqttTopicRecord> value = entry.getValue();
            Runnable runnable;
            switch (key) {
                case "knx-ha" -> {
                    runnable = new KnxToHA(clients, value);
                }
                case "ha-knx" -> {
                    runnable = new HAToKnx(clients, value);
                }
                case "bridge-ha" -> {
                    runnable = new BridgeToHA(clients, value);
                }
                default -> {
                    runnable = () -> {
                        log.info("Nothing to run(((!!!");
                    };
                }
            }
            ex.execute(runnable);
        }
    }
}
