package com.balashoff.mqtt.topic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MqttTopicReaderTest {
    @Test
    public void readRecordsTest(){
        MqttTopicReader reader = new MqttTopicReader("volume/mqtt_topics.json");
        reader.setRecords();
        assertEquals(7, reader.getRecords().size());
    }

}