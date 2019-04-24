package com.trl.stream_api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExampleForEchStream {
    public static void main(String[] args) {
//--------------------------------------------------------------------------------------------------------------------

//--------------------------------------------------------------------------------------------------------------------
        // Do java 8 forecha v iterable nebylo, i profod delalsia tak
//        Map<String, Integer> items00 = new HashMap<>();
//        items00.put("A", 10);
//        items00.put("B", 20);
//        items00.put("C", 30);
//        items00.put("D", 40);
//        items00.put("E", 50);
//        items00.put("F", 60);
//
//        for (Map.Entry<String, Integer> entry : items00.entrySet()) {
//            System.out.println("Item : " + entry.getKey() + " Count : " + entry.getValue());
//        }
//--------------------------------------------------------------------------------------------------------------------
        // V java 8 dobavili default method v Iterable forech, i sechas mozno tak delat.
        Map<String, Integer> items01 = new HashMap<>();
        items01.put("A", 10);
        items01.put("B", 20);
        items01.put("C", 30);
        items01.put("D", 40);
        items01.put("E", 50);
        items01.put("F", 60);

        items01.forEach((k,v)->System.out.println("Item : " + k + " Count : " + v));

        items01.forEach((k,v)->{
            System.out.println("Item : " + k + " Count : " + v);
            if("E".equals(k)){ System.out.println("Hello E"); }
        });
//--------------------------------------------------------------------------------------------------------------------
        // Forech s Listom
        List<String> items = new ArrayList<>();
        items.add("A");
        items.add("B");
        items.add("C");
        items.add("D");
        items.add("E");

        //com.trl.lambda
        //Output : A,B,C,D,E
        items.forEach(item->System.out.println(item));

        //Output : C
        items.forEach(item->{
            if("C".equals(item)){
                System.out.println(item);
            }
        });

        //method reference
        //Output : A,B,C,D,E
        items.forEach(System.out::println);

        //Stream and filter
        //Output : B
        items.stream()
            .filter(s->s.contains("B"))
            .forEach(System.out::println);
    }
}
