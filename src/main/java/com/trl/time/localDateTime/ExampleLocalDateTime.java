package com.trl.time.localDateTime;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class ExampleLocalDateTime {

    public static void main(String[] args) {

        // ========================================================================================= LocalDateTime.now()
        LocalDateTime ldt1 = LocalDateTime.now();
        System.out.println("LocalDateTime.now() --> " + ldt1);

        LocalDateTime ldt6 = LocalDateTime.now(ZoneId.of("Asia/Tokyo"));
        System.out.println("LocalDateTime.now(ZoneId.of(\"Asia/Tokyo\")) --> " + ldt6);

        // ========================================================================================== LocalDateTime.of()
        LocalDateTime ldt3 = LocalDateTime.of(2015, 1, 1, 12, 0);
        System.out.println("LocalDateTime.of(2015, 1, 1, 12, 0) --> " + ldt3);

        LocalDateTime ldt4 = LocalDateTime.of(2015, Month.JANUARY, 1, 12, 0);
        System.out.println("LocalDateTime.of(2015, Month.JANUARY, 1, 12, 0) --> " + ldt4);

        LocalDateTime ldt5 = LocalDateTime.of(2015, 1, 1, 12, 0, 1);
        System.out.println("LocalDateTime.of(2015, 1, 1, 12, 0, 1) --> " + ldt5);

        // ======================================================================================= LocalDateTime.parse()
        LocalDateTime ldt2 = LocalDateTime.parse("2015-01-01T12:00:00");
        System.out.println("LocalDateTime.parse(\"2015-01-01T12:00:00\") --> " + ldt2);

        LocalDateTime ldt7 = LocalDateTime.parse("2015-01-01 12:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        System.out.println("LocalDateTime.parse(\"2015-01-01 12:00\", DateTimeFormatter.ofPattern(\"yyyy-MM-dd HH:mm\")) --> " + ldt7);

        LocalDateTime parse = LocalDateTime.parse("2015-02-20T06:30:00");
        System.out.println("LocalDateTime.parse(\"2015-02-20T06:30:00\") --> " + parse);

        // ===================================================================================== LocalDateTime.plus...()
        LocalDateTime ldt_8 = LocalDateTime.now().plusYears(1).plusMonths(12).plusWeeks(52).plusDays(365)
                .plusHours(8765).plusMinutes(525949).plusSeconds(0).plusNanos(0);
        System.out.println("ldt_8 --> " + ldt_8);

        // ==================================================================================== LocalDateTime.minus...()
        LocalDateTime ldt_9 = LocalDateTime.now().minusYears(1).minusMonths(12).minusWeeks(52).minusDays(365)
                .minusHours(8765).minusMinutes(525949).minusSeconds(0).minusNanos(0);
        System.out.println("ldt_9 --> " + ldt_9);

        // =============================================================================================================
        LocalDateTime ldt_10 = LocalDateTime.now().plusYears(1).minusMonths(12).plusWeeks(52).minusDays(365)
                .plusHours(8765).minusMinutes(525949).plusSeconds(0).minusNanos(0);
        System.out.println("ldt_10 --> " + ldt_10);

        // =================================================================================== LocalDateTime.ofInstant()
        LocalDateTime localDateTime = LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault());
        System.out.println("LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault()) --> " + localDateTime);

        LocalDateTime localDateTime1 = LocalDateTime.ofInstant(Calendar.getInstance().toInstant(), ZoneId.systemDefault());
        System.out.println("LocalDateTime.ofInstant(Calendar.getInstance().toInstant(), ZoneId.systemDefault()) --> " + localDateTime1);

        LocalDateTime localDateTime2 = LocalDateTime.ofEpochSecond(1465817690, 0, ZoneOffset.UTC);
        System.out.println("LocalDateTime.ofEpochSecond(1465817690, 0, ZoneOffset.UTC --> " + localDateTime2);


    }

}
