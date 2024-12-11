package com.balashoff.log;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class ErrorPrint {
    public static void printException(String customMessage, Exception e){
        log.warn("-".repeat(20));
        log.warn(customMessage);
        log.error(e.getMessage());
        log.error(e.fillInStackTrace());
        log.warn("-".repeat(20));
    }
}
