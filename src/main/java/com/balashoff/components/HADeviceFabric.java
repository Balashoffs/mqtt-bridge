package com.balashoff.components;

import com.balashoff.components.sensor.SensorDeviceClass;
import com.balashoff.mqtt.topic.MqttTopicRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HADeviceFabric {
    public static List<IHADeviceClass> generate(Map<String, List<MqttTopicRecord>> topics){
        List<IHADeviceClass> generatedDevices = new ArrayList<>();
        for(String fullType : topics.keySet()){
            String[] chunks = fullType.split("-");
            String component = chunks[0];
            String deviceClass = chunks[1];
            List<IHADeviceClass> deviceClasses;
            switch (component){
                case "sensor":
                    deviceClasses = SensorDeviceClass.buildList(deviceClass, topics.getOrDefault(fullType, List.of()));
                    break;
                default:
                    deviceClasses = List.of();
                    break;

            }
            if(!deviceClasses.isEmpty()){
                generatedDevices.addAll(deviceClasses);
            }

        }
        return generatedDevices;
    }
}
