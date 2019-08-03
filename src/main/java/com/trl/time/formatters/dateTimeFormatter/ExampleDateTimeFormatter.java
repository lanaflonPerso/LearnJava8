package com.trl.time.formatters.dateTimeFormatter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

import static java.time.format.DateTimeFormatter.ISO_WEEK_DATE;

public class ExampleDateTimeFormatter {

    public static void main(String[] args) {

        LocalDate ld = LocalDate.now();
        System.out.println("RESULT 1: " + ld.format(DateTimeFormatter.ISO_WEEK_DATE));
        System.out.println("RESULT 2: " + ld.format(ISO_WEEK_DATE));

        // =============================================================================================================

        // Localized formatting for LocalDate
        LocalDate ld_1 = LocalDate.now();
        System.out.println("SHORT: " + ld_1.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)));
        System.out.println("MEDIUM: " + ld_1.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
        System.out.println("LONG: " + ld_1.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)));
        System.out.println("FULL: " + ld_1.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)));

        // =============================================================================================================

        System.out.println("DateTimeFormatter.BASIC_ISO_DATE --> " + LocalDateTime.now().format(DateTimeFormatter.BASIC_ISO_DATE));
        System.out.println("DateTimeFormatter.ISO_LOCAL_TIME --> " + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));
        System.out.println("DateTimeFormatter.ISO_LOCAL_DATE --> " + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE));
        System.out.println("DateTimeFormatter.ISO_LOCAL_DATE_TIME --> " + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        System.out.println("DateTimeFormatter.ISO_TIME --> " + LocalDateTime.now().format(DateTimeFormatter.ISO_TIME));
        System.out.println("DateTimeFormatter.ISO_DATE --> " + LocalDateTime.now().format(DateTimeFormatter.ISO_DATE));
        System.out.println("DateTimeFormatter.ISO_DATE_TIME --> " + LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        System.out.println("DateTimeFormatter.ISO_ORDINAL_DATE --> " + LocalDateTime.now().format(DateTimeFormatter.ISO_ORDINAL_DATE));

        // =============================================================================================================

        // Passing Formatter to LocalTime format method
        LocalTime lt = LocalTime.now();
        System.out.print("SHORT: " + lt.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)));
        System.out.println(", MEDIUM: " + lt.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM)));

        // Passing LocalTime instance to Formatter's format method
        System.out.print("SHORT: " + DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).format(lt));
        System.out.println(", MEDIUM: " + DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM).format(lt));

        // =============================================================================================================

        // Passing Formatters to LocalDateTime format method
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)));
        System.out.println(ldt.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)));
        System.out.println(ldt.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)));

        // =============================================================================================================

        // ERROR
        System.out.println(LocalDate.now().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)));

        System.out.println(LocalDate.now().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)));

        // =============================================================================================================

        String localDateString = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE);
        System.out.println("LocalDateTime.now().format(DateTimeFormatter.ISO_DATE) --> " + localDateString);

        String format = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        System.out.println("LocalDateTime.now().format(DateTimeFormatter.ofPattern(\"yyyy/MM/dd\")) --> " + format);

        String format1 = LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(Locale.UK));
        System.out.println("LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(Locale.UK)) --> " + format1);

    }

}
