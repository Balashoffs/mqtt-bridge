package com.balashoff.mqtt.topic;

import com.google.gson.annotations.SerializedName;

/*
  {
    "ha_topic":"homeassistant/floor_3/office_room_3_12/sensor/climate",
    "knx_topic": "",
    "ha_component":"sensor",
    "ha_device_class": "climate",
    "topic_direction": "bridge-ha"
  }
   "base_topic" - topic for/from HA
   "knx_topic" - topic for.from KNX
   "ha_component" - HA mqtt components (https://www.home-assistant.io/integrations/mqtt/)
   "ha_device_class" - The type/class of the sensor (component)
   "topic_direction" - Topic direction
        - from HA to KNX throw bridge
        - from KNX to HA throw bridge
        - from HA to bridge
        - from KNX to bridge

 */
public record MqttTopicRecord(
        @SerializedName("ha_topic") String haTopic,
        @SerializedName("knx_topic") String knxTopic,
        @SerializedName("ha_component") String haComponent,
        @SerializedName("ha_device_class") String haDeviceCLass,
        @SerializedName("topic_direction") String topicDirection
) {
}
