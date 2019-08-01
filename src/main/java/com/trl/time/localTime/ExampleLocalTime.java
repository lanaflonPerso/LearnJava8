package com.trl.time.localTime;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class ExampleLocalTime {

    public static void main(String[] args) {

        // ============================================================================================= LocalTime.now()
        LocalTime lt_1 = LocalTime.now();
        System.out.println("LocalTime.now() --> " + lt_1);

        LocalTime lt_7 = LocalTime.now(ZoneId.of("Asia/Tokyo"));
        System.out.println("LocalTime.now(ZoneId.of(\"Asia/Tokyo\")) --> " + lt_7);

        // ============================================================================================== LocalTime.of()
        LocalTime lt_3 = LocalTime.of(12, 0);
        System.out.println("LocalTime.of(12,0) --> " + lt_3);

        LocalTime lt_4 = LocalTime.of(12, 0, 1); // Hour, minutes, seconds
        System.out.println("LocalTime.of(12,0,1) --> " + lt_4);

        LocalTime lt_6 = LocalTime.of(12, 0, 0, 1); // Hour, minutes, seconds, nanos
        System.out.println("LocalTime.of(12,0,0,1) --> " + lt_6);

        // =========================================================================================== LocalTime.parse()
        LocalTime lt_2 = LocalTime.parse("12:00");
        System.out.println("LocalTime.parse(\"12:00\") --> " + lt_2);

        LocalTime lt_8 = LocalTime.parse("12:00", DateTimeFormatter.ISO_TIME);
        System.out.println("LocalTime.parse(\"12:00\", DateTimeFormatter.ISO_TIME) --> " + lt_8);

        // ============================================================================================ LocalTime.plus()
        LocalTime lt_9 = LocalTime.now().plusHours(18765).plusMinutes(525949).plusSeconds(0).plusNanos(0);
        System.out.println("lt_9 --> " + lt_9);

        // ERROR
//        LocalTime lt_19 = LocalTime.now().plusHours(18765).plusMinutes(525949).plusSeconds(0).plusNanos(0).plus(3, ChronoUnit.MONTHS);
//        System.out.println("lt_19 --> " + lt_19);

        // =========================================================================================== LocalTime.minus()
        LocalTime lt_10 = LocalTime.now().minusHours(1).plusMinutes(1).plusSeconds(1).plusNanos(1);
        System.out.println("lt_10 --> " + lt_10);

        // ================================================================== LocalTime.NOON, MIN, MAX, MIDNIGHT as well
        LocalTime lt_5 = LocalTime.NOON;
        System.out.println("LocalTime.NOON --> " + lt_5);

        LocalTime lt_11 = LocalTime.MAX;
        System.out.println("LocalTime.MAX --> " + lt_11);

        LocalTime lt_12 = LocalTime.MIN;
        System.out.println("LocalTime.MIN --> " + lt_12);

        // ================================================================================================== ChronoUnit
        LocalTime lt_13 = LocalTime.of(12, 0, 0).plus(1, ChronoUnit.HOURS);
        System.out.println("lt_13 --> " + lt_13);

        LocalTime lt_14 = LocalTime.of(12, 0, 0).plus(1, ChronoUnit.MINUTES);
        System.out.println("lt_14 --> " + lt_14);

        LocalTime lt_15 = LocalTime.of(12, 0, 0).plus(1, ChronoUnit.MICROS);
        System.out.println("lt_15 --> " + lt_15);

        // ERROR
//        LocalTime lt_16 = LocalTime.of(12,0, 0).plus(1, ChronoUnit.MONTHS);
//        System.out.println("lt_16 --> " + lt_16);

//        LocalTime lt_17 = LocalTime.of(12,0, 0).plus(1, ChronoUnit.YEARS);
//        System.out.println("lt_17 --> " + lt_17);

        // ====================================================================================================== Locale
        LocalTime lt_17 = LocalTime.now(ZoneId.of("Europe/Madrid"));
        System.out.println("LocalTime.now(ZoneId.of(\"Europe/Madrid\")) --> " + lt_17);

        LocalTime lt_18 = LocalTime.now(ZoneId.of("Europe/Kiev"));
        System.out.println("LocalTime.now(ZoneId.of(\"Europe/Kiev\")) --> " + lt_18);

        // ================================================================================== LocalTime.parse().get...()
        int hours = LocalTime.parse("06:30").getHour();
        System.out.println("LocalTime.parse(\"06:30\").getHour() --> " + hours);

        int minutes = LocalTime.parse("06:30").getMinute();
        System.out.println("LocalTime.parse(\"06:30\").getMinute() --> " + minutes);

    }

}
