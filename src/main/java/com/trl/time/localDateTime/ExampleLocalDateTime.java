package com.trl.time.localDateTime;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class ExampleLocalDateTime {

    public static void main(String[] args) {

        LocalDateTime ldt1 = LocalDateTime.now();
        System.out.println("LocalDateTime.now() --> " + ldt1);

        LocalDateTime ldt6 = LocalDateTime.now(ZoneId.of("Asia/Tokyo"));
        System.out.println("LocalDateTime.now(ZoneId.of(\"Asia/Tokyo\")) --> " + ldt6);

        LocalDateTime ldt3 = LocalDateTime.of(2015, 1, 1, 12, 0);
        System.out.println("LocalDateTime.of(2015, 1, 1, 12, 0) --> " + ldt3);

        LocalDateTime ldt4 = LocalDateTime.of(2015, Month.JANUARY, 1, 12, 0);
        System.out.println("LocalDateTime.of(2015, Month.JANUARY, 1, 12, 0) --> " + ldt4);

        LocalDateTime ldt5 = LocalDateTime.of(2015, 1, 1, 12, 0, 1);
        System.out.println("LocalDateTime.of(2015, 1, 1, 12, 0, 1) --> " + ldt5);

        LocalDateTime ldt2 = LocalDateTime.parse("2015-01-01T12:00:00");
        System.out.println("LocalDateTime.parse(\"2015-01-01T12:00:00\") --> " + ldt2);

        LocalDateTime ldt7 = LocalDateTime.parse("2015-01-01 12:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        System.out.println("LocalDateTime.parse(\"2015-01-01 12:00\", DateTimeFormatter.ofPattern(\"yyyy-MM-dd HH:mm\")) --> " + ldt7);


        // All plus methods
        LocalDateTime ldt_8 = LocalDateTime.now().plusYears(1).plusMonths(12).plusWeeks(52).plusDays(365)
                .plusHours(8765).plusMinutes(525949).plusSeconds(0).plusNanos(0);
        System.out.println("ldt_8 --> " + ldt_8);

        // All minus methods
        LocalDateTime ldt_9 = LocalDateTime.now().minusYears(1).minusMonths(12).minusWeeks(52).minusDays(365)
                .minusHours(8765).minusMinutes(525949).minusSeconds(0).minusNanos(0);
        System.out.println("ldt_9 --> " + ldt_9);

        // Demonstrating mixing methods
        LocalDateTime ldt_10 = LocalDateTime.now().plusYears(1).minusMonths(12).plusWeeks(52).minusDays(365)
                .plusHours(8765).minusMinutes(525949).plusSeconds(0).minusNanos(0);
        System.out.println("ldt_10 --> " + ldt_10);

    }

}
