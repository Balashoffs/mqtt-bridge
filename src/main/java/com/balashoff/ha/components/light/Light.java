package com.balashoff.ha.components.light;



public class Light {
    public static String convert(String device, String inputMessage) {
        return switch (device) {
            case "switch" -> convertSwitch(inputMessage);
            case "brightness" -> convertBrightness(inputMessage);

            default -> "";
        };
    }

    private static String convertSwitch(String inputMessage) {
        return switch (inputMessage){
            case "ON" -> "1";
            case "OFF" -> "0";
            default -> "";
        };
    }

    private static String convertBrightness(String inputMessage) {
        return inputMessage;
    }
}
