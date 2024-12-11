package com.balashoff.custom;

import com.balashoff.json.FileReader;
import com.balashoff.json.JsonSerilazer;
import com.balashoff.mqtt.config.MqttBrokerRecord;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public abstract class AbstractRecordsReader<T> {
    @Getter
    private final List<T> records = new ArrayList<>() ;
    @Getter
    private final String pathToFile;
    private final Class<?> mainCLass;
    private final Class<?> subClasses;

    public List<T> parse() throws IOException {
        JsonSerilazer<List<T>> serilazer = new JsonSerilazer<>();
        String json = FileReader.readFile(pathToFile);
        return serilazer.fromJsonC(json, mainCLass, subClasses);
    }
}
