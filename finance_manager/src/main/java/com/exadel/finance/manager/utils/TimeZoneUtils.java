package com.exadel.finance.manager.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class TimeZoneUtils {
    public static LocalDateTime getGmtCurrentDate() {
        return LocalDateTime.now(ZoneId.of("GMT"));
    }
}
