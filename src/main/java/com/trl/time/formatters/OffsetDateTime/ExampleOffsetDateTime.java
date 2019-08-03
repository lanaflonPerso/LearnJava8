package com.trl.time.formatters.OffsetDateTime;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static java.time.format.DateTimeFormatter.*;

public class ExampleOffsetDateTime {

    public static void main(String[] args) {

        OffsetDateTime odt = OffsetDateTime.now();
        System.out.println(odt.format(ISO_DATE));
        System.out.println(odt.format(ISO_OFFSET_DATE));
        System.out.println(odt.format(ISO_OFFSET_DATE_TIME));

        LocalDateTime localDateTime = LocalDateTime.of(2015, Month.FEBRUARY, 20, 06, 30);
        ZoneOffset offset = ZoneOffset.of("+03:00");
        OffsetDateTime offSetByTwo = OffsetDateTime.of(localDateTime, offset);
        System.out.println(offSetByTwo);

    }

}
