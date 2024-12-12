package com.balashoff.ha.components.cover;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

public class Cover {
    public static String convertToKnx(String device, String inputMessage) {
        switch (device) {
            case "curtains":
                return CoverState.findByHaState(inputMessage);
            default:
                return "undef";
        }
    }
}

@AllArgsConstructor
@Getter
enum CoverState {
    open("0", "OPEN"),
    close("1", "CLOSE"),
    stop("1", "STOP"),
    undef("-1", "undef");

    public final String knxState;
    public final String haState;

    public static String findByHaState(String state) {
        return Arrays
                .stream(CoverState.values())
                .filter(cs -> cs.haState.equals(state))
                .map(m -> m.knxState)
                .findFirst().orElse("undef");
    }

    public static String findByKnxState(String state) {
        return Arrays
                .stream(CoverState.values())
                .filter(cs -> cs.knxState.equals(state))
                .map(m -> m.haState)
                .findFirst().orElse("undef");
    }
}
