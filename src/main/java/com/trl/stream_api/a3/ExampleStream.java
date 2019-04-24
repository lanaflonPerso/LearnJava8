package com.trl.stream_api.a3;

import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class ExampleStream {
    public static void main(String[] args) {
//----------------------------------------------------------------------------------------------------------------------
        // Rrwenie zadachi PP za dopomohoy StreamAPI
//        System.out.println((2L & 0xff));
        List<Long> listL = new ArrayList<>(Arrays.asList(1L, 2L, 3L, 4L, 5L, 6L));
        // Sequential / Ordered
        listL.stream().
              filter(x -> (x & 0xff) == 0).
              collect(Collectors.toList());
        // Sequential / Unordered
        listL.stream().
            unordered().
            filter(x -> (x & 0xff) == 0).
            collect(Collectors.toList());
        // Parallel / Ordered
        listL.parallelStream().
            filter(x -> (x & 0xff) == 0).
            collect(Collectors.toList());
        // Parallel / Unordered
        listL.parallelStream().
            unordered().
            filter(x -> (x & 0xff) == 0).
            collect(Collectors.toList());
//----------------------------------------------------------------------------------------------------------------------
        // Chisla fibonachi
//        System.out.println("generate =  " + streamGenerate().parallel().limit(100).reduce(BigInteger::add).get());
//        System.out.println("generate1 = " + streamGenerate().limit(100).parallel().reduce(BigInteger::add).get());
//        System.out.println("iterate =   " + streamIterateArray().parallel().limit(100).reduce(BigInteger::add).get());
//        System.out.println("iterator =  " + streamIterator().parallel().limit(100).reduce(BigInteger::add).get());
//----------------------------------------------------------------------------------------------------------------------
        // Bery stream i delay zdes forEch, ya xochy slozit elementu v list
//        List<Integer> list = new ArrayList<>();
//        IntStream.range(0, 1000).parallel().forEach(list::add);
//        System.out.println(list.size());
        // Vsehda kohda rabotaiem so stream vsehda nado  dymat za sostoianie
//---------------------------------------------------------------------------------------------------------------------
        // Dano
        Stream<String> files = Stream.of("nameFile_1.txt", "nameFile2.txt", "nameFile3.txt");
        // Neobxodimo vivesti pervoiy stroky iz kazdoho syschestvyewchoho faila
//        Stream<String> firstLines = files.map(FileReader::new).
//                                          map(BufferedReader::new).
//                                          map(BufferedReader::readLine);
        // Zdes owibka , eti metodu i konstryktoru kidayt Checked isklychenia,
        // a StreamAPI ne podderzyet isklychenia
        // I etot blok koda ne kompiliryetsa!!!!!!
        // Piwem dryhoe riwenie etoi zadachi
        Stream<String> firstLines = files.
            map(fn -> {
                try {
                    return new FileReader(fn);
                } catch (FileNotFoundException e) {
                    // Eto esklychenie specialno sdelanoe dlia obrabotki cheked IO isklychenii
                    throw new UncheckedIOException(e);
                } }).
            map(BufferedReader::new).
            map(r -> {
                try {
                    return r.readLine();
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
                });
        // Pri takom riwenii nado rider nezabit zakrit
        // V danom rewenie vuidet isklychenie FileNotFoundExceptin
        // Mozno napisat v takom vide potywiv isklychenie, no toze owibochno eto rewenie
//        Stream<String> firstLines1 = files.
//            map(File::new).
//            filter(File::exists).
//            map(fn -> {
//                try {
//                    return new FileReader(fn);
//                } catch (FileNotFoundException e) {
//                     Eto esklychenie specialno sdelanoe dlia obrabotki cheked IO isklychenii
//                    throw new UncheckedIOException(e);
//                } }).
//            map(BufferedReader::new).
//            map(r -> {
//                try {
//                    return r.readLine();
//                } catch (IOException e) {
//                    throw new UncheckedIOException(e);
//                }
//            });
        // No zdes toze est problema, FileReader cheho mu vernom kohda polychim exception
        // Mozno zdelat tak
        // Manada Optional))))))
//        Stream<String> firstLines2 = files.
//            map(fn -> {
//                try {
//                    return Optional.of(new FileReader(fn));
//                } catch (FileNotFoundException e) {
//                    return Optional.<FileReader>empty();
//                }
//            }).
//            filter(Optional::isPresent).
//            map(Optional::get).
//            map(BufferedReader::new).
//            map(r -> {
//                try {
//                    return Optional.of(r.readLine());
//                } catch (IOException e) {
//                    return Optional.<String>empty();
//                }
//            }).filter(Optional::isPresent).map(Optional::get);

        // Ewcho odin primer
        // Manada Stream))))))
//        Stream<String> firstLines3 = files.flatMap(fn -> {
//            try {
//                    return Stream.of(new FileReader(fn));
//                } catch (FileNotFoundException e) {
//                    return Stream.<FileReader>empty();
//                } }).
//            map(BufferedReader::new).
//            flatMap(r -> {
//                try {
//                    return Stream.of(r.readLine());
//                } catch (IOException e) {
//                    return Stream.<String>empty();
//                }
//            });
//---------------------------------------------------------------------------------------------------------------------
    }
    // Starui sposob vupolnenia danoi zadachi pod nazvoi PP
    List<Long> longList = new ArrayList<>(Arrays.asList(1L,2L,3L,4L,5L,6L));

    public List<Long> oldSchool(){
        List<Long> l = new ArrayList<>();
        for (Long v : longList) {
            if ((v & 0xff) == 0){     // yznati wto zdes vipolniaetsa, kakoe deistvie
                l.add(v);
            }
        }
        return l;
    }

//---------------------------------------------------------------------------------------------------------------------

    // Cisla Fibonachi
    // V int vlazit rovno 45 chsel fibonachi
    // A v long vlazit 91 chislo Fibonachi
    // Izza etoho zdes vikorustovvyetsa BigInteger
    // Vse xorowo pri posledovatelnoi obrabotki
    // No bolwue problemu bydyt v rasparalelovaniii
    public static Stream<BigInteger> streamGenerate(){
        return Stream.generate(
            new Supplier<BigInteger>() {

                BigInteger first = BigInteger.ZERO;
                BigInteger second = BigInteger.ONE;

                 @Override
                public BigInteger get() {
                     BigInteger s = second.add(first);
                     first = second;
                     second = s;
                     return first;
                }
        });
    }
//---------------------------------------------------------------------------------------------------------------------
    // Vtoroe rewenie chisel fibonachi s ispolzovanie Stream.iterate()
    public static Stream<BigInteger> streamIterateArray(){
            return Stream.iterate(new BigInteger[]{BigInteger.ZERO, BigInteger.ONE},
                (BigInteger[] p) -> new BigInteger[]{p[1], p[0].add(p[1])}).
                map((BigInteger[] p) -> p[1]);
    }
//---------------------------------------------------------------------------------------------------------------------
    // Tretie rewenie chisel fibonachi s ispolzovaniem Iteratora
    public static Stream<BigInteger> streamIterator(){
        Iterator<BigInteger> bit = new Iterator<BigInteger>() {
            BigInteger first = BigInteger.ZERO;
            BigInteger second = BigInteger.ONE;

            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public BigInteger next() {
                BigInteger s = second.add(first);
                first = second;
                second = s;
                return first;
            }
        };
        Spliterator<BigInteger> bsplit =
            Spliterators.spliterator(bit, Long.MAX_VALUE,
                                     Spliterator.ORDERED | Spliterator.SORTED | Spliterator.NONNULL | Spliterator.IMMUTABLE);
        return StreamSupport.stream(bsplit, false);
    }
//---------------------------------------------------------------------------------------------------------------------

}
