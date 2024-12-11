package com.balashoff.mqtt.config;

import com.balashoff.json.FileReader;
import com.balashoff.json.JsonSerilazer;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.List;
import static com.balashoff.log.ErrorPrint.*;

@Log4j2
public class ConfigReader {
    public static List<MqttBrokerConfig> getConfig(String filePath){
        try {
            JsonSerilazer<List<MqttBrokerConfig>> serilazer = new JsonSerilazer<>();
            String json = FileReader.readFile(filePath);
            return serilazer.fromJsonC(json, List.class, MqttBrokerConfig.class);
        } catch (IOException e) {
            printException(String.format("Couldn't read file '%s'. Check it", filePath), e);
            return List.of();
        }
    }
}
