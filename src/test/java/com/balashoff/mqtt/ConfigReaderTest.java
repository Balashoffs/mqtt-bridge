package com.balashoff.mqtt;

import com.balashoff.mqtt.config.ConfigReader;
import com.balashoff.mqtt.config.MqttBrokerConfig;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
class ConfigReaderTest {
    @Test
    public void readMqttBrokerConfigFileTest() throws IOException {
        List<MqttBrokerConfig> configs = ConfigReader.getConfig("volume/mqtt_broker_config.json");
        assertEquals(2, configs.size());
        log.info(configs);
        assertEquals(1883, configs.get(0).port());
    }

}