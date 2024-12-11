package com.balashoff.mqtt;

import com.balashoff.mqtt.config.MqttBrokerRecordReader;
import com.balashoff.mqtt.config.MqttBrokerRecord;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
class MqttBrokerRecordReaderTest {
    @Test
    public void readMqttBrokerConfigFileTest() throws IOException {
        MqttBrokerRecordReader reader = new MqttBrokerRecordReader("volume/mqtt_broker_config.json") ;
        reader.setRecords();

        assertEquals(2, reader.getRecords().size());
        log.info(reader.getRecords());
        assertEquals(1883, reader.getRecords().get(0).port());
    }

}