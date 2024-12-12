package com.balashoff.ha.components;

import com.balashoff.ha.components.cover.Cover;
import com.balashoff.ha.components.light.Light;


public class Convertor {

    public static String forTopic(String component, String device, String inputMessage){
        switch (component){
            case "light":
                return Light.convert(device, inputMessage);
            case "cover":
                return Cover.convert(device, inputMessage);
        }
        return "";
    }

}
