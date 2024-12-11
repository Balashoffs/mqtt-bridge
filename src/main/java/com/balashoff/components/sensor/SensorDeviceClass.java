package com.balashoff.components.sensor;

import com.balashoff.components.DefaultHADeviceClass;
import com.balashoff.components.IHADeviceClass;
import com.balashoff.mqtt.topic.MqttTopicRecord;

import java.util.List;

public class SensorDeviceClass {
    public static List<IHADeviceClass> buildList(String deviceClass, List<MqttTopicRecord> records) {
        return records.stream().map(record -> {
            switch (deviceClass) {
                case "climate":
                    return new SensorClimate(record);
                default:
                    return new DefaultHADeviceClass();
            }
        }).filter(cs -> cs.getClass() != DefaultHADeviceClass.class).toList();
    }
}
