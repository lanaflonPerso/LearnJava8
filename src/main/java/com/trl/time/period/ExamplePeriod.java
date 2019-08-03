package com.trl.time.period;

import java.time.*;
import java.time.temporal.ChronoUnit;

public class ExamplePeriod {

    public static void main(String[] args) {

        final Period p_1 = Period.ofYears(1);        // 1 year
        final Period p_2 = Period.ofMonths(12);      // 1 year
        final Period p_3 = Period.ofWeeks(52);       // 1 year
        final Period p_4 = Period.ofDays(366);       // 1 year   (leap)
        final Period p_5 = Period.of(1, 12, 366);      // 3 year

        LocalDate ldt1 = LocalDate.of(2000, Month.JANUARY, 1);
        LocalDate ldt2 = ldt1.plus(p_1).plus(p_2).plus(p_3).plus(p_4).plus(p_5);
        System.out.println("Before: " + ldt1 + " After: " + ldt2);

        // ============================================================================================== Period.parse()

        // Creates a period of 41 years, 2 months, and 3 days
        Period period1 = Period.parse("P41Y2M3D");
        System.out.println("Period.parse(\"P41Y2M3D\") --> " + period1);

        // Creates a period of 4 weeks
        Period period2 = Period.parse("P4W");
        System.out.println("Period.parse(\"P4W\") --> " + period2.getDays() + " days");

        Period p = Period.parse("P1Y");
        System.out.println("Period.parse(\"P1Y\") --> " + p.getMonths());

        // ============================================================================================= period.get...()

        Period period = Period.of(5, 1, 14);
        int years_1 = period.getYears();
        int months_1 = period.getMonths();
        long days_1 = period.getDays();
        System.out.println(years_1 + " years, " + months_1 + " months, " + days_1 + " days");

        // ================================================================================================ period.get()

        long years_2 = period.get(ChronoUnit.YEARS);
        long months_2 = period.get(ChronoUnit.MONTHS);
        long days_2 = period.get(ChronoUnit.DAYS);
        System.out.println(years_2 + " years, " + months_2 + " months, " + days_2 + " days");

        // ============================================================================================ period.with...()

        Period p1 = Period.of(1, 1, 1); // 1 year, 1 month, 1 day
        p1 = p1.withYears(5); // Changes years only
        System.out.println(p1); // 5 years, 1 month, 1 day

        Period p2 = Period.of(1, 1, 1); // 1 year, 1 month, 1 day
        p2 = p2.withMonths(5); // Changes months only
        System.out.println(p2); // 1 years, 5 months, 1 day

        Period p3 = Period.of(1, 1, 1); // 1 year, 1 month, 1 day
        p3 = p3.withDays(5); // Changes days only
        System.out.println(p3); // 1 years, 1 month, 5 day

        // ============================================================================================ period.plus...()

        Period period_1 = Period.of(5, 2, 1);
        period_1 = period_1.plusYears(10);
        period_1 = period_1.plusMonths(10);
        period_1 = period_1.plusDays(30);

        // Plus a total 10 years, 10 months and 30 days
        System.out.println("Period value: " + period_1);

        // ================================================================================= period.plus(Period.of...())

        Period period_2 = Period.of(5, 2, 1);
        period_2 = period_2.plus(Period.ofYears(10));
        period_2 = period_2.plus(Period.ofMonths(10));
        period_2 = period_2.plus(Period.ofDays(15));

        // Plus a total 10 years, 10 months and 30 days
        System.out.println("Period value: " + period_2);

        // =========================================================================================== period.minus...()

        Period period_3 = Period.of(5, 2, 1);
        period_3 = period_3.minusYears(10);
        period_3 = period_3.minusMonths(10);
        period_3 = period_3.minusDays(30);

        // Minus a total 10 years, 10 months and 30 days
        System.out.println("Period value: " + period_3);

        // ================================================================================ period.minus(Period.of...())

        Period period_4 = Period.of(5, 2, 1);
        period_4 = period_4.minus(Period.ofYears(10));
        period_4 = period_4.minus(Period.ofMonths(10));
        period_4 = period_4.minus(Period.ofDays(15));

        // Minus a total 10 years, 10 months and 30 days
        System.out.println("Period value: " + period_4);

        // ============================================================================================== period.is...()

        Period p10D = Period.parse("P10D").minusDays(10);
        System.out.println("Is zero: " + p10D.isZero());

        // Period equals negative value
        Period p2015M = Period.parse("P2015M");
        p2015M = p2015M.minusMonths(2016); // 2015-2016 is -1 Months
        System.out.println("Is negative: " + p2015M.isNegative());

        // ============================================================================================ Period.between()

        int days = Period.between(LocalDate.now(), LocalDate.of(2019, 8, 15)).getDays();
        System.out.println("Period.between(LocalDate.of(2019, 8, 15), LocalDate.now()).getDays() --> " + days);

        long days_0 = ChronoUnit.DAYS.between(LocalDate.now(), LocalDate.of(2019, 8, 15));
        System.out.println("ChronoUnit.DAYS.between(LocalDate.of(2019, 8, 15), LocalDate.now()) --> " + days_0);

        // ERROR
//        Period p_0 = Period.between(LocalDateTime.now().toLocalTime(), LocalTime.now());

        // =============================================================================================================

        final String WAR_OF_1812_START_DATE = "1812-06-18";
        final String WAR_OF_1812_END_DATE = "1815-02-18";

        LocalDate warBegins = LocalDate.parse(WAR_OF_1812_START_DATE);
        LocalDate warEnds = LocalDate.parse(WAR_OF_1812_END_DATE);

        Period period_b = Period.between(warBegins, warEnds);
        System.out.println("WAR OF 1812 TIME FRAME: " + period_b);

    }

}