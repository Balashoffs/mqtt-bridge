package com.balashoff.ha.components.light;



public class Light {
    public static String convertToKnx(String device, String inputMessage) {
        return switch (device) {
            case "switch" -> convertSwitchHa(inputMessage);
            case "brightness" -> convertBrightnessHa(inputMessage);

            default -> "";
        };
    }

    public static String convertKnx(String device, String inputMessage) {
        return switch (device) {
            case "switch" -> convertSwitchKnx(inputMessage);
            case "brightness" -> convertBrightnessKnx(inputMessage);

            default -> "";
        };
    }

    private static String convertSwitchHa(String inputMessage) {
        return switch (inputMessage){
            case "ON" -> "1";
            case "OFF" -> "0";
            default -> "";
        };
    }

    private static String convertBrightnessHa(String inputMessage) {
        return inputMessage;
    }

    private static String convertSwitchKnx(String inputMessage) {
        return switch (inputMessage){
            case "1" -> "ON";
            case "0" -> "OFF";
            default -> "";
        };
    }

    private static String convertBrightnessKnx(String inputMessage) {
        return inputMessage;
    }
}
