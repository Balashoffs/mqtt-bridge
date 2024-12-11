package com.balashoff.mqtt.topic;

import com.balashoff.custom.AbstractRecordsReader;

import java.io.IOException;
import java.util.List;

import static com.balashoff.log.ErrorPrint.printException;


public class MqttTopicReader extends AbstractRecordsReader<MqttTopicRecord> {

    public MqttTopicReader(String pathToFile) {
        super(pathToFile, List.class, MqttTopicRecord.class);
    }

    @Override
    public void setRecords() {
        try {
            List<MqttTopicRecord> found = parse();
            getRecords().addAll(found);
        } catch (IOException e) {
            printException(String.format("Couldn't read file '%s'. Check it", getPathToFile()), e);

        }
    }
}
