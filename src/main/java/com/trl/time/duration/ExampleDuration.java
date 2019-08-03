package com.trl.time.duration;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class ExampleDuration {

    public static void main(String[] args) {

        LocalTime initialTime = LocalTime.of(6, 30, 0);
        LocalTime finalTime = initialTime.plus(Duration.ofSeconds(30));
        System.out.println(finalTime);

        long thirty = Duration.between(finalTime, initialTime).getSeconds();

        long thirty_2 = ChronoUnit.SECONDS.between(finalTime, initialTime);

    }

}
