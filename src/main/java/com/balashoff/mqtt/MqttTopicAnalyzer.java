package com.balashoff.mqtt;

import com.balashoff.mqtt.topic.MqttTopicReader;
import com.balashoff.mqtt.topic.MqttTopicRecord;
import lombok.Getter;

import java.lang.reflect.Array;
import java.util.*;

@Getter
public class MqttTopicAnalyzer{
    private final Map<String, List<MqttTopicRecord>> topics = new HashMap<>();

    public void analyzeRecords(){
        MqttTopicReader mqttTopicReader = new MqttTopicReader("volume/mqtt_topics.json");
        mqttTopicReader.setRecords();
        analyze(mqttTopicReader.getRecords());
    }

    private void analyze(List<MqttTopicRecord> topicsForAnalyzer){
        topicsForAnalyzer.forEach(mqttTopicRecord -> {
            String topicDirection = mqttTopicRecord.topicDirection();
            topics.putIfAbsent(topicDirection, new ArrayList<>());
            topics.get(topicDirection).add(mqttTopicRecord);
        });
    }
}
