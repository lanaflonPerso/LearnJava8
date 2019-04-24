package com.trl.stream_api;

import java.util.ArrayList;
import java.util.List;

public class ExampleFilterStream {
    public static void main(String[] args) {
//---------------------------------------------------------------------------------------------------------------------

        List<String> items = new ArrayList<>();
        items.add("A");
        items.add("B");
        items.add("C");
        items.add("D");
        items.add("E");
        items.add("B");

        //Stream and filter
        //Output : B
        items.stream()
            .filter(s -> s.contains("B"))
            .forEach(System.out::println);


//---------------------------------------------------------------------------------------------------------------------
    }
}
