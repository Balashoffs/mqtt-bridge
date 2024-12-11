package com.balashoff.mqtt;

import org.junit.jupiter.api.Test;

class MqttClientManagerTest {

    @Test
    void connectToBrokers() {
        MqttClientManager mqttClientManager = new MqttClientManager();
        boolean isConnect = mqttClientManager.connectToBrokers();
        if(isConnect){
            mqttClientManager.closeAllConnection();
        }
    }
}