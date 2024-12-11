package com.balashoff.mqtt;

import com.balashoff.log.ErrorPrint;
import com.balashoff.mqtt.config.MqttBrokerRecord;
import com.balashoff.mqtt.config.MqttBrokerRecordReader;
import lombok.extern.log4j.Log4j2;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
public class MqttClientManager {
    public Map<Boolean, MqttCustomClient> mqttClients = new HashMap<>();

    private List<MqttBrokerRecord> getMqttClientConfigs() {
        MqttBrokerRecordReader reader = new MqttBrokerRecordReader("volume/mqtt_broker_config.json");
        reader.setRecords();
        return reader.getRecords();
    }

    public boolean connectToBrokers() {
        List<MqttBrokerRecord> foundRecords = getMqttClientConfigs();
        boolean rightRecordsSize = foundRecords.size() == 2;
        if (rightRecordsSize) {
            do {
                boolean success = tryToConnect(foundRecords);
                if (success) {
                    break;
                }
                mqttClients.values().forEach(MqttCustomClient::close);
                mqttClients.clear();
                log.warn("Wait for 10s to try connecting to mqtt brokers");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    log.error(e.getMessage(), e.fillInStackTrace());
                }
            } while (true);
        } else {
            log.warn("Something go wrong. Application not found right quantity of records with mqtt broker configs");
        }
        return rightRecordsSize;
    }

    private boolean tryToConnect(List<MqttBrokerRecord> foundRecords) {
        for (MqttBrokerRecord record : foundRecords) {
            MqttCustomClient customClient = new MqttCustomClient(record);
            try {
                log.info("Try to connect to brocker - {}:{}", record.host(), record.port());
                customClient.connect();
                mqttClients.putIfAbsent(record.needForwarding(), customClient);
                log.info("Successful connection to broker - {}:{}", record.host(), record.port());
            } catch (MqttException e) {
                ErrorPrint.printException(String.format("Couldn't connect to mqtt broker: '%s:%s'", record.host(), record.port()), e);
                break;
            }
        }
        return foundRecords.size() == mqttClients.size();

    }

    public void closeAllConnection() {
        for (MqttCustomClient client : mqttClients.values()) {
            log.info("Close connection with - {}", client.getId());
            client.close();
        }
        mqttClients.clear();
    }

}
