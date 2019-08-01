package com.trl.time.formatters.OffsetDateTime;

import java.time.OffsetDateTime;

import static java.time.format.DateTimeFormatter.*;

public class ExampleOffsetDateTime {

    public static void main(String[] args) {

        OffsetDateTime odt = OffsetDateTime.now();
        System.out.println(odt.format(ISO_DATE));
        System.out.println(odt.format(ISO_OFFSET_DATE));
        System.out.println(odt.format(ISO_OFFSET_DATE_TIME));

    }

}
