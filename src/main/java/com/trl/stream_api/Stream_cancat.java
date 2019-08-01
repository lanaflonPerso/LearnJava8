package com.trl.stream_api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Stream_cancat {

    public static void main(String[] args) {

//-----------------------------------------Rabota-s-methodom-concat()---------------------------------------------------

        List<String> stringList = new ArrayList<>(Arrays.asList("A", "B", "C"));
        List<String> stringList1 = new ArrayList<>(Arrays.asList("1", "2", "3"));

        Stream<String> stringStream = stringList.stream();
        Stream<String> stringStream1 = stringList1.stream();

        Stream<String> result = Stream.concat(stringStream, stringStream1);

        result.forEach(v -> System.out.print(v + ", "));

    }

}
