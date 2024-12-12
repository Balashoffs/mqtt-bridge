package com.balashoff.ha.components.cover;

public class Cover {
    public static String convert(String device, String inputMessage) {
        switch (device){
            case "curtains":
                return "1";
            case "cover":
                return "2";
            default:
                return "";
        }
    }
}
