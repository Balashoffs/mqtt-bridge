package com.balashoff.mqtt;

import com.balashoff.mqtt.config.MqttBrokerRecordReader;
import com.balashoff.mqtt.config.MqttBrokerRecord;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class MqttCustomClientTest {

    private List<MqttBrokerRecord> configs;

    @BeforeEach
    void setUp() {
        MqttBrokerRecordReader reader = new MqttBrokerRecordReader("volume/mqtt_broker_config.json") ;
        reader.setRecords();
        configs = reader.getRecords();
    }

    @Test
    public void mqttClientConnectionTest() {
        configs.forEach(config -> {
            MqttCustomClient mqttCustomClient = new MqttCustomClient(config);
            try {
                mqttCustomClient.connect();
                mqttCustomClient.pushMessage("/ha/hello", "world");
                Thread.sleep(5000);
                mqttCustomClient.close();
            } catch (MqttException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @AfterEach
    void tearDown() {
        configs.clear();
    }
}