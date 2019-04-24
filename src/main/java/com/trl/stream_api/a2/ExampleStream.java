package com.trl.stream_api.a2;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class ExampleStream {
    public static void main(String[] args) {
//---------------------------------------------------------------------------------------------------------------------
        // Stream Api позволяет писать обработку структур данных в стиле SQL,
        // то если раньше задача получить сумму всех нечетных чисел из коллекции решалась следующим кодом:
//        Integer sumOddOld = 0;
//        Collection<Integer> collection00 = new ArrayList<Integer>(
//            Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18));
//
//        for(Integer i: collection00) {
//            if(i % 2 != 0) {
//                sumOddOld += i;
//            }
//        }
//        System.out.println(sumOddOld);
//---------------------------------------------------------------------------------------------------------------------
        // То с помощью Stream Api можно решить такую задачу в функциональном стиле:
//        Collection<Integer> collection01 = new ArrayList<Integer>(
//            Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18));
//
//        Integer sumOdd = collection01.stream().filter(o -> o % 2 != 0).reduce((s1, s2) -> s1 + s2).orElse(0);
//        System.out.println(sumOdd);
//---------------------------------------------------------------------------------------------------------------------
        // Более того, Stream Api позволяет решать задачу параллельно лишь
        // изменив stream() на parallelStream() без всякого лишнего кода, т.е.
//        Collection<Integer> collection02 = new ArrayList<Integer>(
//            Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18));
//
//        Integer sumOdd02 = collection02.
//                            parallelStream().
//                                filter(o -> o % 2 != 0).
//                                    reduce((s1, s2) -> s1 + s2).
//                                        orElse(0);
//
//        System.out.println(sumOdd02);
//---------------------------------------------------------------------------------------------------------------------
        //        Stream<Long> stream = Stream.iterate(2L, k -> k + 3);
//        Stream.iterate(0L, k -> k + 1).             // Sozdaiotsa posledovanost longov, nachinaya s nyla
//                parallel().                               // Ya xochy wtob on bil paralelnum
//                 filter(k -> k % 3 == 2).                 // Xochy ostavit v etom strime ostalis tolko chisla kotorue posle dilenia na 3 ostatok raven 2
//                   map(k -> "#" + k).                    // Zapisrvia v etot map ya peredelal long v string spomowchiy konkatenocii
//                     limit(10).                           // S pomowchiy limit() ya vozmy tolko 10 pervux elementov
//                       forEach(k -> System.out.println(k));  // Zdes ya eti 10 elementov pichatay
//---------------------------------------------------------------------------------------------------------------------
        //        Stream.iterate(0L, k -> k + 1).
//            parallel().
//            filter(k -> k % 3 == 2).
//            map(k -> "#" + k).
//            limit(10).
//            forEach(new Consumer<String>() {
//                @Override
//                public void accept(String k) {
//                    // Estli vklychen paralel() to zdes ya smotriy v skolko
//                    // potokov vupolniaetsa eta rabota.
//                    System.out.println(Thread.currentThread());
////                    System.out.println(k);
//                }
//            });
//---------------------------------------------------------------------------------------------------------------------
        // filter: meniaet kolichestvo, no NE menaet elementu
        // map: NE meniaet kolichestvo, no menaet elementu
        // flatMap:

        Supplier<Double> d = () -> Math.random();
        Supplier<Double> d1 = Math::random;
        System.out.println(d1.get());

        Consumer<Integer> c = (value) -> System.out.println(value);
        c.accept(3);

        Function<Integer, Double>  f = (value) -> {return Double.valueOf(value);};
        System.out.println(f.apply(45));

        MyFunction<String, Integer> myFunction = Integer::valueOf;
        MyFunction<Integer, String> myFunction2 = String::valueOf;

        Function<String, Stream<String>> functionN = new Function<String, Stream<String>>() {
            @Override
            public Stream<String> apply(String s) {
                return null;
            }
        };

        Function<String, Stream<String>> functionL = s -> Arrays.asList(s.split(" ")).stream();

        Arrays.asList("1", "2 33", "4 55 666").stream().flatMap(functionL).forEach(System.out::println);
//---------------------------------------------------------------------------------------------------------------------
        Stream<Double> generate = Stream.generate(Math::random);  // Kak ya ponial sozdaiot odin strim s odnim znacheiem double

        // manoid
        //1) Associatuvnaia operacia
        //2) Neitralnui element
//        Stream<Integer> iterate = Stream.
//                            iterate(1, k -> k + 1).
//                            limit(10).parallel().
//---------------------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------------------
//		String[] arrayStr2 = {"aa", "ab", "lk", "nne", "ae", "bee", "ze", "cae", "aaaaze"};
//		Arrays.stream(arrayStr2).forEach(System.out::print);
//---------------------------------------------------------------------------------------------------------------------
//        new Thread(ExampleStreamAPI::printHello).start();
//        new Thread(() -> System.out.println("Hello")).start();
//---------------------------------------------------------------------------------------------------------------------
//        Arrays.asList("A", "BB", "CCC").
//            parallelStream().
//            map(str ->  str.length()).
//            filter(k -> k % 2 == 1).
//            forEach(System.out::println);
//---------------------------------------------------------------------------------------------------------------------
//        List<String> stringList = new ArrayList<>(Arrays.asList("A", "BB", "CCC"));
//        Stream<String> stringStream = stringList.parallelStream();
//        stringStream.
//            map(new Function<String, Integer>() {
//                @Override
//                public Integer apply(String s) {
//                    return s.length();
//                }
//            }).
//            filter(new Predicate<Integer>() {
//                @Override
//                public boolean test(Integer integer) {
//                    return integer % 2 == 1;
//                }
//            }).
//            forEach(new Consumer<Integer>() {
//                @Override
//                public void accept(Integer integer) {
//                    System.out.println(integer);
//                }
//            });
//---------------------------------------------------------------------------------------------------------------------
//        List<String> list = new ArrayList<>(Arrays.asList("A", "BB", "CCC"));
//        for (Iterator<String> iterator = list.iterator(); iterator.hasNext(); ) {
//            System.out.println(iterator.next());
//        }
//        for (String s : list) {
//            System.out.println(s);
//        }
//        list.parallelStream().forEach(System.out::println);
//---------------------------------------------------------------------------------------------------------------------
//        Stream<Long> stream = Stream.iterate(2L, k -> k + 3);
//        Stream<Long> stream = Stream.iterate(0L, k -> k + 1);
//        Stream<Long> stream = Stream.iterate(0L, k -> k + 1).filter(k -> k % 3 == 0);
//        Stream.iterate(0L, k -> k + 1).
//                filter(k -> k % 3 == 0).
//                map(k -> "~" + k).
//                limit(10).
//                forEach(k -> System.out.println(k));
//---------------------------------------------------------------------------------------------------------------------
//        List<Integer> list = Arrays.asList("A", "BB", "CCC").
//            parallelStream().
//            map(str -> str.length()).
//            filter(k -> k % 2 == 1).
//            collect(Collectors.toList());
////        list.stream().forEach(v -> System.out.println(v));
//        for (Integer value : list) {
//            System.out.println(value);
//        }
//---------------------------------------------------------------------------------------------------------------------
////        Stream<String> stream = Arrays.asList("A", "BB", "CCC").parallelStream();
//        Stream<String> stream = Arrays.asList("A", "BB", "CCC").stream();
//        // Klass Stream ne iavliaetsa potoko bezopasnum snaryze
//        // Stream ne zawchewchonnui ot paralelnux potokov v takom kontekste, on
//        // zwchewchonuui ot potokov kotorue on sam sozdaiot v nytri streama
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                stream.map(k -> k +"~").forEach(System.out::println);
//            }
//        }).start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                stream.map(k -> k +"#").forEach(System.out::println);
//            }
//        }).start();
//---------------------------------------------------------------------------------------------------------------------
//        Stream<String> stream = Arrays.asList("A", "BB", "CCC").parallelStream();
//        stream.
//            map(k -> k + ",").
//            filter(i -> i != "rr").
//            map(u -> u + "~").
//            map(u -> u + "...").
//            forEach(System.out::println);
//---------------------------------------------------------------------------------------------------------------------
//        Set<Integer> set = new HashSet<>();
//        set.add(1);
//        set.add(2);
//        set.add(3);
//        set.add(4);
//        set.add(5);
//        set.add(6);
//        Stream<Integer> stream00 = set.stream();
//        Stream<String> stream01 = stream00.map(value -> "~" + value);
//        List<String> collect = stream01.collect(Collectors.toList());
//        System.out.println(collect);
//---------------------------------------------------------------------------------------------------------------------
//		Set<Integer> set = new HashSet<>();
//		set.add(1);
//		set.add(2);
//		set.add(3);
//		set.add(4);
//		set.add(5);
//		set.add(6);
//		Stream<Integer> stream = set.stream();
//		stream.
//				filter(k -> k % 2 == 0).
//				forEach(v -> System.out.print(v + ", "));
//
//		System.out.println("\n" + set);
//---------------------------------------------------------------------------------------------------------------------
    }

    public static void printHello(){
        System.out.println("Hello");
    }
}


interface MyFunction<T, R>{
    R apply (T t);
}