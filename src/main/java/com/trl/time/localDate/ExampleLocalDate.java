package com.trl.time.localDate;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

public class ExampleLocalDate {

    public static void main(String[] args) {

        // ============================================================================================= LocalDate.now()
        LocalDate ld_1 = LocalDate.now();
        System.out.println("LocalDate.now() --> " + ld_1);

        LocalDate ld_5 = LocalDate.now(ZoneId.of("Asia/Tokyo")); // Locale
        System.out.println("LocalDate.now(ZoneId.of(\"Asia/Tokyo\")) --> " + ld_5);

        // ============================================================================================== LocalDate.of()
        LocalDate ld_3 = LocalDate.of(2015, 1, 1); // Year, Month, Day
        System.out.println("LocalDate.of(2015, 1, 1) --> " + ld_3);

        LocalDate ld_4 = LocalDate.of(2015, Month.JANUARY, 1); // Year, Month, Day
        System.out.println("LocalDate.of(2015, Month.JANUARY, 1) --> " + ld_4);

        // =========================================================================================== LocalDate.parse()
        LocalDate ld_2 = LocalDate.parse("2015-01-01"); // Date
        System.out.println("LocalDate.parse(\"2015-01-01\") --> " + ld_2);

        LocalDate ld_6 = LocalDate.parse("2015-01-01", DateTimeFormatter.ISO_DATE);
        System.out.println("LocalDate.parse(\"2015-01-01\", DateTimeFormatter.ISO_DATE) --> " + ld_6);

        DayOfWeek sunday = LocalDate.parse("2016-06-12").getDayOfWeek();
        System.out.println("LocalDate.parse(\"2016-06-12\").getDayOfWeek() --> " + sunday);

        int twelve = LocalDate.parse("2016-06-12").getDayOfMonth();
        System.out.println("LocalDate.parse(\"2016-06-12\").getDayOfMonth() --> " + twelve);


        // ============================================================================================ LocalDate.plus()
        LocalDate ld_7 = LocalDate.now().plusYears(1).plusMonths(12).plusWeeks(52).plusDays(365);
        System.out.println("ld_7 --> " + ld_7);

        // =========================================================================================== LocalDate.minus()
        LocalDate ld_8= LocalDate.now().minusYears(1).minusMonths(12).minusWeeks(52).minusDays(365);
        System.out.println("ld_8 --> " + ld_8);

        // ERROR
//        LocalDate ld_9= LocalDate.now().minusYears(1).minusMonths(12).minus(3, ChronoUnit.MINUTES);
//        System.out.println("ld_9 --> " + ld_9);

        // =========================================================================================== LocalDate.is...()
        boolean leapYear = LocalDate.now().isLeapYear();
        boolean notBefore = LocalDate.parse("2016-06-12").isBefore(LocalDate.parse("2016-06-11"));
        boolean isAfter = LocalDate.parse("2016-06-12").isAfter(LocalDate.parse("2016-06-11"));

        // ==================================================================================== LocalDate.parse().with()
        LocalDate firstDayOfMonth = LocalDate.parse("2016-06-12").with(TemporalAdjusters.firstDayOfMonth());
        System.out.println("LocalDate.parse(\"2016-06-12\").with(TemporalAdjusters.firstDayOfMonth()) --> " + firstDayOfMonth);

        LocalDate lastDayOfMonth = LocalDate.parse("2016-06-12").with(TemporalAdjusters.lastDayOfMonth());
        System.out.println("LocalDate.parse(\"2016-06-12\").with(TemporalAdjusters.lastDayOfMonth()) --> " + lastDayOfMonth);

        LocalDate firstDayOfYear = LocalDate.parse("2016-06-12").with(TemporalAdjusters.firstDayOfYear());
        System.out.println("LocalDate.parse(\"2016-06-12\").with(TemporalAdjusters.firstDayOfYear()) --> " + firstDayOfYear);

        // ============================================================================ LocalDate.parse().atStartOfDay()
        LocalDateTime beginningOfDay = LocalDate.parse("2016-06-12").atStartOfDay();
        System.out.println("LocalDate.parse(\"2016-06-12\").atStartOfDay() --> " + beginningOfDay);


    }

}
