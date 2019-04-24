package com.trl.stream_api;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class ExampleParallelStreams {
    public static void main(String[] args) throws IOException {
//--------------------------------------------------------------------------------------------------------------------
        /*Более того, метод parallel() преобразует любой последовательный поток данных в параллельный поток, как показано
        ниже. При выполнении оконечного метода поток данных действует в параллельном режиме, и поэтому промежуточные операции
        в этом потоке распараллеливаются.*/
        String[] wordArray = new  String[10];
        Stream<String> parallelWords = Stream.of(wordArray).parallel();
        parallelWords.forEach(System.out::print);
        System.out.println();
        String contents = new String(Files.readAllBytes(
                Paths.get("src/main/resources/StreamArchivos/alice30M")), StandardCharsets.UTF_8);
        List<String> wordList = Arrays.asList(contents.split("\\PL+"));
        /*Допустим, требуется подсчитать все короткие слова в потоке символьных строк. В приведенном ниже примере демонстрируется,
        как не следует решать эту задачу.*/
        // Very bad code ahead
        int[] shortWords = new int[10];
        wordList.parallelStream().forEach(s ->
        {
            if (s.length() < 10) shortWords[s.length()]++;
            // ОШИБКА: состояние гонок!
        });
        System.out.println(Arrays.toString(shortWords));
        // Try again--the result will likely be different (and also wrong)
        Arrays.fill(shortWords, 0);
        wordList.parallelStream().forEach(s ->
        {
            if (s.length() < 10) shortWords[s.length()]++;
        });
        System.out.println(Arrays.toString(shortWords));
        // Remedy: Group and count
        Map<Integer, Long> shortWordCounts = wordList.parallelStream()
                .filter(s -> s.length() < 10)
                .collect(groupingBy(String::length, counting()));
        System.out.println("shortWordCounts: " + shortWordCounts);
        /*Как обсуждалось в разделе 1.9, объединять отображения невыгодно из-за немалых затрат. Именно поэтому в методе
        Collectors.groupingByConcurrent() используется общее параллельное отображение. Чтобы извлечь выгоду из параллелизма,
        порядок следования значений в отображении должен быть иным, чем в потоке данных:*/
        // Downstream order not deterministic
        Map<Integer, List<String>> result = wordList.parallelStream().collect(Collectors.groupingByConcurrent(String::length));
        // Значения не накапливаются в потоковом порядке
        System.out.println(result.get(14));
        result = wordList.parallelStream().collect(Collectors.groupingByConcurrent(String::length));
        System.out.println(result.get(14));
        Map<Integer, Long> wordCounts = wordList.parallelStream().collect(groupingByConcurrent(String::length, counting()));
        System.out.println(wordCounts);
        /*Совет! При выполнении потоковой операции очень важно не изменять коллекцию, поддерживающую поток данных, даже
        если такое изменение и является потокобезопасным. Напомним, что данные в потоках данных не накапливаются, а всегда
        находятся в отдельной коллекции. Если попытаться изменить коллекцию, то результат выполнения потоковых операций
        окажется неопределенным. В документации на комплект JDK такое требование называется невмешательством. Оно относится
        как к последовательным, так и к параллельным потокам данных.
        Точнее говоря, коллекцию можно изменять вплоть до момента выполнения оконечной операции, поскольку промежуточные
        потоковые операции выполняются по требованию. Так, следующий фрагмент кода вполне работоспособен, хотя и не рекомендуется:*/
        List<String> wordListWW = new ArrayList<>();
        Stream<String> wordsWW = wordListWW.stream();
        wordListWW.add("END");
        long nWW = wordsWW.distinct().count();
        /*А приведенный ниже фрагмент кода оказывается неработоспособным.*/
        Stream<String> wordsS = wordListWW.stream();
        wordsS.forEach(s -> {
            if (s.length() < 12) wordListWW.remove(s);
            // ОШИБКА: вмешательство!*/
        });
//--------------------------------------------------------------------------------------------------------------------
    }
}

/*
-----------------------------------------------------------------------------------------------------------------------
Для обеспечения нормальной работы потоков данных необходимо соблюсти ряд следующих условий.
    • Данные должны находиться в оперативной памяти. Было бы неэффективно ожидать их поступления из внешнего источника.
    • Поток данных должен эффективно разделяться на подобласти. Поток данных, поддерживаемый массивом или сбалансированным
      двоичным деревом, вполне пригоден, но этого нельзя сказать о результате вызова метода Stream.iterate ().
    • Потоковые операции должны выполнять значительный объем работы. Если общая рабочая нагрузка невелика, то затраты на
      организацию параллельных вычислений не оправдываются.
    • Потоковые операции не должны блокироваться.

Иными словами, не все потоки данных следует превращать в параллельные.Пользоваться параллельными потоками данных следует
лишь в том случае, если постоянно приходится выполнять значительный объем вычислений над данными, уже находящимися в оперативной памяти.
-----------------------------------------------------------------------------------------------------------------------
Некоторые операции могут быть распараллелены более эффективно, если требование упорядочения опускается. Вызывая метод
Stream.unordered(), можно указать, что упорядочение не имеет значения.
-----------------------------------------------------------------------------------------------------------------------
Совет! Функции, передаваемые параллельному потоку данных, не должны блокироваться. Для оперирования отдельными сегментами
параллельного потока данных применяется пул вилочного соединения. Если же блокируется несколько потоковых операций, то этот
пул может оказаться просто неспособным выполнять свои функции.
-----------------------------------------------------------------------------------------------------------------------
*/