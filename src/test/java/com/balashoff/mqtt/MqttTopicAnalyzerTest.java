package com.balashoff.mqtt;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MqttTopicAnalyzerTest {

    @Test
    void analyzeRecords() {
        MqttTopicAnalyzer analyzer = new MqttTopicAnalyzer();
        analyzer.analyzeRecords();
        assertEquals(3, analyzer.getTopics().size());
    }
}