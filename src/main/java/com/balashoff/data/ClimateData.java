package com.balashoff.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ClimateData {
    private double temperature;
    private double humidity;
    private double tvoc;
    private double co2;
    private double pressure;
}
