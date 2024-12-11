package com.balashoff;

import com.balashoff.mqtt.MqttClientManager;
import com.balashoff.mqtt.MqttTopicAnalyzer;
import com.balashoff.mqtt.MqttTopicController;

public class Main {
    public static void main(String[] args) {
        MqttTopicAnalyzer analyzer = new MqttTopicAnalyzer();
        analyzer.analyzeRecords();
        MqttClientManager manager = new MqttClientManager();
        boolean isConnect = manager.connectToBrokers();
        if(isConnect){
            MqttTopicController controller = new MqttTopicController(manager.mqttClients, analyzer.getTopics());
            controller.createTopicSubscribes();
        }

        while (true){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}