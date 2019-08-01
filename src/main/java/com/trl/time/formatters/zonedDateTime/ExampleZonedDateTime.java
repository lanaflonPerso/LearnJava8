package com.trl.time.formatters.zonedDateTime;

import java.time.ZonedDateTime;

import static java.time.format.DateTimeFormatter.*;

public class ExampleZonedDateTime {

    public static void main(String[] args) {

        ZonedDateTime zdt = ZonedDateTime.now();
        System.out.println(zdt.format(ISO_DATE_TIME));
        System.out.println(zdt.format(ISO_ZONED_DATE_TIME));
        System.out.println(zdt.format(RFC_1123_DATE_TIME));

    }

}
