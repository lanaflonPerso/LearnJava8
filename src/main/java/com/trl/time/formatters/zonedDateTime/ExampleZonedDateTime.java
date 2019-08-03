package com.trl.time.formatters.zonedDateTime;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;

import static java.time.format.DateTimeFormatter.*;

public class ExampleZonedDateTime {

    public static void main(String[] args) {

        ZonedDateTime zdt = ZonedDateTime.now();
        System.out.println(zdt.format(ISO_DATE_TIME));
        System.out.println(zdt.format(ISO_ZONED_DATE_TIME));
        System.out.println(zdt.format(RFC_1123_DATE_TIME));

        Set<String> allZoneIds = ZoneId.getAvailableZoneIds();

        ZoneId zoneId = ZoneId.of("Europe/Paris");

        ZonedDateTime.parse("2015-05-03T10:15:30+01:00[Europe/Paris]");

    }

}
