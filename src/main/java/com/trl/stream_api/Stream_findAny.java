package com.trl.stream_api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Stream_findAny {

    public static void main(String[] args) {

        List<String> words = new ArrayList<>(Arrays.asList("QA AA", "Ba", "Cxx", "QD AA", "xxxxxE", "QBaas", "QA", "QA CC"));

		/*Если же требуется любое совпадение, а не только первое, то следует воспользоваться методом findAny (),
		как показано ниже. Это оказывается эффективным при распараллеливании потока данных, поскольку поток может
		известить о любом обнаруженном в нем совпадении, вместо того чтобы ограничиваться только первым совпадением.*/

        Optional<String> optional = words.stream()
                .filter(s -> s.startsWith("Q"))
                .findAny();

        //        System.out.println(optional);

//        words.stream()
//                .filter(s -> s.startsWith("Q")).forEach(v -> System.out.print(v + ", "));


        // =============================================================================================================

        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);

        Optional integerStream = integerList.stream().parallel().filter(num -> num < 4).findAny();
        System.out.println(integerStream);

        // =============================================================================================================


    }

}
