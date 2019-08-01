package com.trl.stream_api;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class ReuseStream {

    public static void main(String[] args) {

        Stream<String> stringStream_1 = Stream.of("dd2", "aa2", "bb1", "bb2", "cc4").filter(s -> s.startsWith("a"));
        stringStream_1.anyMatch(s -> true);  // poperacia vupolniaetsa yspewno
//		stringStream.noneMatch(s -> true); // Vuletaet Exception:
        // Exception in thread "main" java.lang.IllegalStateException: stream has already been operated upon or closed

        // a tak mozno pereispolzovat stream
        Supplier<Stream<String>> streamSupplier =
                () -> Stream.of("dd2", "aa2", "bb1", "bb2", "cc4").filter(s -> s.startsWith("a"));
        streamSupplier.get().anyMatch(s -> true); // operacia proidot yspewno
        streamSupplier.get().noneMatch(s -> true);  // zdes takze bydet vso xorowo

    }

}
