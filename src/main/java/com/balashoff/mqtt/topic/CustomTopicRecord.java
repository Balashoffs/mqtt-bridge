package com.balashoff.mqtt.topic;

import com.google.gson.annotations.SerializedName;

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
public record CustomTopicRecord(
        @SerializedName("ha_topic") String haTopic,
        @SerializedName("knx_topic") String knxTopic,
        @SerializedName("value") String value
) {
}
