package com.balashoff.ha.components;

import com.balashoff.ha.components.cover.Cover;
import com.balashoff.ha.components.light.Light;
import com.balashoff.mqtt.service.ForKNX;
import com.balashoff.mqtt.topic.CustomTopicRecord;
import com.balashoff.mqtt.topic.MqttTopicRecord;


public class Convertor {

    public static ForKNX toKnx(MqttTopicRecord mqttRecord, String inputMessage){
        String newMessage;
        switch (mqttRecord.haComponent()){
            case "light":
                newMessage =  Light.convertToKnx(mqttRecord.haDeviceCLass(), inputMessage);
                return new ForKNX(mqttRecord.topics().get(0).knxTopic(), newMessage);
            case "cover":
               newMessage =  Cover.convertToKnx(mqttRecord.haDeviceCLass(), inputMessage);
               String newTopic = mqttRecord
                       .topics()
                       .stream()
                       .filter(rec -> rec.value().contains(inputMessage))
                       .map(CustomTopicRecord::knxTopic)
                       .findFirst().orElse("");

                return new ForKNX(newTopic, newMessage);
        }
        newMessage = "";
        return new ForKNX("",newMessage);
    }

    public static String toHA(String component, String device, String inputMessage){
        switch (component){
            case "light":
                return Light.convertKnx(device, inputMessage);
        }
        return "";
    }

}
