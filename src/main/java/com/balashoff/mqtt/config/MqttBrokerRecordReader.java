package com.balashoff.mqtt.config;

import com.balashoff.custom.AbstractRecordsReader;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.List;

import static com.balashoff.log.ErrorPrint.*;

@Log4j2
public class MqttBrokerRecordReader extends AbstractRecordsReader<MqttBrokerRecord> {

    public MqttBrokerRecordReader(String pathToFile) {
        super(pathToFile, List.class, MqttBrokerRecord.class);
    }

    @Override
    public void setRecords() {
        try {
            List<MqttBrokerRecord> founds = parse();
            getRecords().addAll(founds);
        } catch (IOException e) {
            printException(String.format("Couldn't read file '%s'. Check it", getPathToFile()), e);
        }
    }
}
