package com.balashoff.mqtt.service;

import com.balashoff.mqtt.MqttCustomClient;
import com.balashoff.mqtt.topic.MqttTopicRecord;

import java.util.List;
import java.util.Map;

public class HAToKnx extends AbstractSmartService {


    public HAToKnx(Map<Boolean, MqttCustomClient> clients, List<MqttTopicRecord> topics) {
        super(clients, topics);
    }

    @Override
    public void run() {

    }

    @Override
    protected void init() {

    }
}