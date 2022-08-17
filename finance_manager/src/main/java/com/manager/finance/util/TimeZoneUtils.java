package com.manager.finance.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class TimeZoneUtils {
    public static LocalDateTime getGmtCurrentDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        return LocalDateTime.now(ZoneId.of("GMT")).truncatedTo(ChronoUnit.MICROS);
    }
}
