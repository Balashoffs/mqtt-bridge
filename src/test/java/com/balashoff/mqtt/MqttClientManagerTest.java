package com.balashoff.mqtt;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MqttClientManagerTest {

    @Test
    void connectToBrokers() {
        MqttClientManager mqttClientManager = new MqttClientManager();
        boolean isConnect = mqttClientManager.connectToBrokers();
        if(isConnect){
            mqttClientManager.disconnect();
        }
    }
}