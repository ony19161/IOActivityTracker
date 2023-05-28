package org.tbuddies.helpers;
import java.time.*;


public class DateHelper {
    private static final long TICKS_AT_EPOCH = 621355968000000000L;
    private static final long TICKS_PER_MILLISECOND = 10000;

    public static Instant getUTCTicks() {

        Clock baseclock = Clock.systemDefaultZone();

        return baseclock.instant();

    }

}
