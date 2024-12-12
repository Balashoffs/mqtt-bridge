package com.balashoff.mqtt.service;

import com.balashoff.ha.components.HADeviceFabric;
import com.balashoff.ha.components.IHADeviceClass;
import com.balashoff.mqtt.MqttCustomClient;
import com.balashoff.mqtt.topic.MqttTopicRecord;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
public class BridgeToHA extends AbstractSmartService {
    private List<IHADeviceClass> haDevices = null;


    public BridgeToHA(Map<Boolean, MqttCustomClient> clients, List<MqttTopicRecord> topics) {
        super(clients, topics);
    }

    @Override
    protected void init() {

        Map<String, List<MqttTopicRecord>> unionTopics = new HashMap<>();
        for (MqttTopicRecord topic : getTopics()) {
            String haDeviceClass = String.format("%s-%s", topic.haComponent(), topic.haDeviceCLass());
            unionTopics.putIfAbsent(haDeviceClass, new ArrayList<>());
            unionTopics.get(haDeviceClass).add(topic);
        }

        haDevices = HADeviceFabric.generate(unionTopics);
        log.info("Application found {} device for publishing to HA", haDevices.size());
    }

    @Override
    public void run() {
        init();
        schedule();
    }

    private void schedule() {
        if (haDevices != null && haDevices.isEmpty()) {
            log.warn("HA Devices were not generated for publishing. Check it!");
            return;
        }
        MqttCustomClient client = getClients().get(false);
        while (client.isRunning.get()) {
            haDevices.forEach(device -> client.pushMessage(device.topicHa(), device.generateData()));
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                log.error(e.getMessage(), e.fillInStackTrace());
            }
        }
        log.warn("Mqtt broker {} was broken, closing publishing message from bridge to HA", client.getId());
        haDevices.clear();
    }
}
