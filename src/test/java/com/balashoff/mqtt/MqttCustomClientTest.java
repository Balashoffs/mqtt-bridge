package com.balashoff.mqtt;

import com.balashoff.mqtt.config.ConfigReader;
import com.balashoff.mqtt.config.MqttBrokerConfig;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MqttCustomClientTest {

    private List<MqttBrokerConfig> configs;
    @BeforeEach
    void setUp() {
        configs = ConfigReader.getConfig("volume/mqtt_broker_config.json");
    }

    @Test
    public void mqttClientConnectionTest(){
        configs.forEach(config -> {
            MqttCustomClient mqttCustomClient = new MqttCustomClient();
            try {
                mqttCustomClient.connect(config);
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