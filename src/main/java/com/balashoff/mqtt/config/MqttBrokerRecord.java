package com.balashoff.mqtt.config;

import com.google.gson.annotations.SerializedName;

public record MqttBrokerRecord(
        @SerializedName("mqtt_broker_host") String host,
        @SerializedName("mqtt_broker_port") int port,
        @SerializedName("mqtt_broker_user") String user,
        @SerializedName("mqtt_broker_password") String password,
        @SerializedName("need_forwarding") boolean needForwarding
) {
}
