package com.balashoff.ha.components.binarysensor;

import com.balashoff.ha.components.IHADeviceClass;
import com.balashoff.mqtt.topic.MqttTopicRecord;
import com.balashoff.util.generator.BinarySensorRandomGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BinarySensorDeviceClass {
    public static List<IHADeviceClass> buildSensors(List<MqttTopicRecord> records) {
        final Map<String, List<MqttTopicRecord>> recordsByDeviceClass = new HashMap<>();
        records.forEach(record -> {
            recordsByDeviceClass.putIfAbsent(record.haDeviceCLass(), new ArrayList<>());
            recordsByDeviceClass.get(record.haDeviceCLass()).add(record);
        });

        return recordsByDeviceClass.values().stream().flatMap(entry -> {
            BinarySensorRandomGenerator generator = new BinarySensorRandomGenerator(entry);
            return BinarySensorGeneratedData.generateFrom(generator);
        }).toList();


    }
}
