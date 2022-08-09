package com.exadel.managger.finance.util;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class TimeZoneUtils {
    public static LocalDateTime getGmtCurrentDate() {
        return LocalDateTime.now(ZoneId.of("GMT"));
    }
}
