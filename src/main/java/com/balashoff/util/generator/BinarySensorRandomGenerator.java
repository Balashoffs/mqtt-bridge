package com.balashoff.util.generator;

import com.balashoff.mqtt.topic.MqttTopicRecord;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BinarySensorRandomGenerator {
    private final Map<Integer, String> topicsWithNumber = new HashMap<>();
    private CurrentTopicWithValue currentTopicWithValue = new CurrentTopicWithValue("", "");

    public BinarySensorRandomGenerator(List<MqttTopicRecord> records) {
        for (int i = 0; i < records.size(); i++) {
            String topic = records.get(i).topics().get(0).haTopic();
            topicsWithNumber.putIfAbsent(i + 1, topic);
        }

    }


    public String make() {
        if (currentTopicWithValue.getTopic().isEmpty()) {
            int pos = (int) ((Math.random() * topicsWithNumber.size()) + 1);
            currentTopicWithValue = new CurrentTopicWithValue(topicsWithNumber.get(pos), "ON");
        } else {
            currentTopicWithValue.setValue("OFF");
        }
        return currentTopicWithValue.getValue();
    }

    public String getTopic() {
        String topic = currentTopicWithValue.getTopic();
        if (currentTopicWithValue.getValue().equals("OFF")) {
            currentTopicWithValue.setTopic("");
            currentTopicWithValue.setValue("");
        }
        return topic;
    }
}

@AllArgsConstructor
@Getter
@Setter
class CurrentTopicWithValue {
    private String topic;
    private String value;
}
