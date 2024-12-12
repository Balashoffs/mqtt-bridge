package com.balashoff.ha.components;

import com.balashoff.ha.components.cover.Cover;
import com.balashoff.ha.components.light.Light;


public class Convertor {

    public static String forTopicHA(String component, String device, String inputMessage){
        switch (component){
            case "light":
                return Light.convertHA(device, inputMessage);
            case "cover":
                return Cover.convert(device, inputMessage);
        }
        return "";
    }

    public static String forTopicKNX(String component, String device, String inputMessage){
        switch (component){
            case "light":
                return Light.convertKnx(device, inputMessage);
            case "cover":
                return Cover.convert(device, inputMessage);
        }
        return "";
    }

}
