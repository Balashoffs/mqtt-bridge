package com.balashoff.mqtt.topic;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/*
  {
    "topics": [
      {
        "ha_topic": "homeassistant/floor_3/office_room_3_12/climate",
        "knx_topic": ""
      }
    ],
    "ha_component": "sensor",
    "ha_device_class": "climate",
    "topic_direction": "bridge-ha"
  },
 */
public record MqttTopicRecord(
        @SerializedName("topics") List<CustomTopicRecord> topics,
        @SerializedName("ha_component") String haComponent,
        @SerializedName("ha_device_class") String haDeviceCLass,
        @SerializedName("topic_direction") String topicDirection
) {
}
