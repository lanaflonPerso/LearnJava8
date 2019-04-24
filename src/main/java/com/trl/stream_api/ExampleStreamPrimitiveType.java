package com.trl.stream_api;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ExampleStreamPrimitiveType {
    public static void main(String[] args) throws IOException {
        /*Чтобы создать поток данных типа IntStream, достаточно вызвать методы IntStream.of() и Arrays.stream() следующим образом:*/
        IntStream stream = IntStream.of(1, 1, 2, 3, 5);
        int[] values = {1, 2, 3, 4, 5, 6, 7};
        int from = 1;
        int to = 4;
        stream = Arrays.stream(values, from, to);
        // массив values относится к типу int[]
        IntStream is1 = IntStream.generate(() -> (int) (Math.random() * 100)).limit(100);
        show("is1", is1);
        /*К потокам данных примитивных типов, как и к потокам объектов, можно применять статические методы generate() и
        iterate(). Кроме того, в классах IntStream и LongStream имеются статические методы range() и rangeClosed(),
        генерирующие диапазоны целочисленных значений с единичным шагом:*/
        IntStream is2 = IntStream.range(5, 10);
        show("is2", is2);
        IntStream is3 = IntStream.rangeClosed(5, 10);
        show("is3", is3);

        Path path = Paths.get("src/main/resources/alice30M.txt");
        String contents = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);

        Stream<String> words = Stream.of(contents.split("\\PL+"));
        IntStream is4 = words.mapToInt(String::length);
        show("is4", is4);
        /*В интерфейсе CharSequence имеются методы codePoints() и chars(), получающие поток типа IntStream кодов символов
        в Юникоде или кодовых единиц в кодировке UTF-16. (Подробнее о кодировках символов см. в главе 2.) Ниже приведен
        пример применения метода codePoints ().*/
        String sentence = "\uD835\uDD46 is the set of octonions.";
        // \uD835\uDD46 — это кодировка UTF-16 знака ®, обозначающего
        // октонионы в Юникоде (U+1D546)
        System.out.println(sentence);
        IntStream codes = sentence.codePoints();
        // Поток шестнадцатеричных значений 1D546 20 69 73 20 . . .
        System.out.println(codes.mapToObj(c -> String.format("%X ", c)).collect(Collectors.joining()));
        /*Чтобы преобразовать поток данных примитивного типа в поток объектов, достаточно воспользоваться методом boxed() следующим образом:*/
        Stream<Integer> integers = IntStream.range(0, 100).boxed();
        /*Поток объектов можно преобразовать в поток данных примитивных типов с помощью метода mapToInt(), mapToLong() или
        mapToDouble(). Так, если имеется поток символьных строк и их длины требуется обработать как целочисленные значения,
        это можно сделать и средствами класса IntStream следующим образом:*/
        IntStream is5 = integers.mapToInt(Integer::intValue);
        show("is5", is5);
    }

    public static void show(String title, IntStream intStream) {
        final int SIZE = 10;
        int[] firstElements = intStream.limit(SIZE + 1).toArray();
        System.out.print(title + ": ");
        for (int i = 0; i < firstElements.length; i++) {
            if (i > 0) System.out.print(", ");
            if (i < SIZE) System.out.print(firstElements[i]);
            else System.out.print("...");
        }
        System.out.println();
    }
}

/*
-----------------------------------------------------------------------------------------------------------------------
Как правило, методы для потоков данных примитивных типов аналогичны методам для потоков объектов. Ниже перечислены
наиболее существенные их отличия.
    • Методы типа toArray возвращают массивы примитивных типов.
    • Методы, возвращающие результат необязательного типа, возвращают значение типа OptionalInt, OptionalLong или OptionalDouble.
      Классы этих типов аналогичны классу Optional, но у них имеются методы getAsInt(), getAsLong() и getAsDouble() вместо метода get().
    • Имеются методы sum(), average(), max() и min(), возвращающие сумму, среднее, максимум и минимум соответственно.
      Эти методы не определены для потоков объектов.
    • Метод summaryStatistics() возвращает объект типа IntSummary-Statistics, LongSummaryStatistics или DoubleSummaryStatistics,
      способный одновременно сообщать о сумме, среднем, максимуме и минимуме в потоке данных.
-----------------------------------------------------------------------------------------------------------------------
HAЗАМЕТКУ! В классе Random имеются методы ints(), longs() и doubles(), возвращающие потоки данных примитивных типов,
состоящие из случайных чисел.
-----------------------------------------------------------------------------------------------------------------------
*/
