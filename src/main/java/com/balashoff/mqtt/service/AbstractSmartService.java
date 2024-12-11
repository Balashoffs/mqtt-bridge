package com.balashoff.mqtt.service;

import com.balashoff.mqtt.MqttCustomClient;
import com.balashoff.mqtt.topic.MqttTopicRecord;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Getter(value = AccessLevel.PROTECTED)
public abstract class AbstractSmartService implements Runnable{
    private final  Map<Boolean, MqttCustomClient> clients;
    private final List<MqttTopicRecord> topics;

    protected abstract void init();
}
