package com.balashoff.ha.components.sensor;

import com.balashoff.ha.components.IHADeviceClass;
import com.balashoff.data.ClimateData;
import com.balashoff.json.JsonSerilazer;
import com.balashoff.mqtt.topic.MqttTopicRecord;
import com.balashoff.util.generator.ClimateGenerator;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SensorClimate implements IHADeviceClass {
    private final MqttTopicRecord topicRecord;
    private final ClimateGenerator climateGenerator = new ClimateGenerator();
    protected final JsonSerilazer<ClimateData> jsonAnalyzer = new JsonSerilazer<>();
    @Override
    public String generateData() {
        double t = climateGenerator.generateTemperature();
        double p = climateGenerator.generatePressure();
        double h = climateGenerator.generateHumidity();
        double c = climateGenerator.generateCo2();
        double tv = climateGenerator.generateTVOC();
        ClimateData cl = new ClimateData(t, h, p, c, tv);
        return jsonAnalyzer.toJsonC(cl, ClimateData.class);
    }

    @Override
    public String topicHa() {
        return topicRecord.haTopic();
    }

    @Override
    public String topicKnx() {
        return topicRecord.knxTopic();
    }

}
