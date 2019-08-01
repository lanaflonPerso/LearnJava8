package com.trl.stream_api;

import com.trl.entityes.City;
import com.trl.entityes.Person;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class ExampleCollectors {

    public static void main(String[] args) throws IOException {

//--------------------------------------------Collectors----------------------------------------------------------------

        /*Очень часто из значений с одинаковыми характеристиками образуются группы, и этот процесс непосредственно
        поддерживается методом groupingBy().Рассмотрим задачу группирования региональных настроек по странам.
        Сначала образуется следующее отображение:*/
        Stream<Locale> locales = null;
        Map<String, List<Locale>> countryToLocales = locales.collect(Collectors.groupingBy(Locale::getCountry));

        /*Функция Locale: :getCountry() исполняет роль классификатора группирования. Затем все региональные настройки можно
        отыскать по заданному коду страны, как показано в следующем примере кода:*/
        List<Locale> swissLocales = countryToLocales.get("CH");
        // получить региональные настройки [it_CH, de_CH, fr_CH]*/

        /*Когда функция классификатора оказывается предикатной (т.е. функцией, возвращающей логическое значение типа boolean),
        элементы потока данных разделяются на основной список с элементами, для которых функция возвращает логическое значение
        true, и дополнительный список. В данном случае эффективнее воспользоваться методом partitioningBy(), чем методом
        groupingBy(). Так, в следующем примере кода все региональные настройки разделяются на те, что описывают английский
        язык, и все остальные:*/

        Map<Boolean, List<Locale>> englishAndOtherLocales = locales.collect(
                Collectors.partitioningBy(l -> l.getLanguage().equals("en")));
        List<Locale> englishLocales = englishAndOtherLocales.get(true);

//----------------------------------------------CollectingResults-------------------------------------------------------

        /*По завершении обработки потока данных нередко требуется просмотреть полученные результаты. С этой целью можно
        вызвать метод iterate(), предоставляющий устаревший итератор, которым можно воспользоваться для обхода элементов.*/
        Iterator<Integer> iter = Stream.iterate(0, n -> n + 1).limit(10).iterator();
        while (iter.hasNext())
            System.out.println(iter.next());

        Object[] numbers = Stream.iterate(0, n -> n + 1).limit(10).toArray();
//        System.out.println("Object array:" + numbers); // Note it's an Object[] array

        try {
            Integer number = (Integer) numbers[0]; // OK
//            System.out.println("number: " + number);
//            System.out.println("The following statement throws an exception:");
            Integer[] numbers2 = (Integer[]) numbers; // Throws exception
        } catch (ClassCastException ex) {
            System.out.println(ex);
        }
        /*Но чаще всего результаты требуется накапливать в структуре данных. С этой целью можно вызвать метод toArray()
        и получить элементы из потока данных.Создать обобщенный массив во время выполнения невозможно, и поэтому в результате
        вызова stream.toArray() возвращается массив типа Object []. Если же требуется массив нужного типа, этому методу
        следует передать конструктор такого массива, как показано ниже.*/
        Integer[] numbers3 = Stream.iterate(0, n -> n + 1).limit(10).toArray(Integer[]::new);
//        System.out.println("Integer array: " + numbers3); // Note it's an Integer[] array
        /*Для накопления элементов потока данных с другой целью имеется удобный метод collect(), принимающий экземпляр
        класса, реализующего интерфейс Collector. В частности, класс Collectors предоставляет немало фабричных методов для
        наиболее употребительных коллекторов. Так, для накопления потока данных в списке или множестве достаточно сделать
        один из следующих вызовов:*/
        Set<String> noVowelSet = noVowels().collect(Collectors.toSet());
        show("noVowelSet", noVowelSet);
        /*Если же требуется конкретная разновидность получаемого множества, то нужно сделать следующий вызов:*/
        TreeSet<String> noVowelTreeSet = noVowels().collect(Collectors.toCollection(TreeSet::new));
        show("noVowelTreeSet", noVowelTreeSet);
        /*Допустим, требуется накапливать все символьные строки, сцепляя их. С этой целью можно сделать следующий вызов:*/
        String result = noVowels().limit(10).collect(Collectors.joining());
//        System.out.println("Joining: " + result);
        /*А если требуется разделитель элементов, то его можно передать методу joining() следующим образом:*/
        result = noVowels().limit(10).collect(Collectors.joining(", "));
//        System.out.println("Joining with commas: " + result);
        /*Если результаты обработки потока данных требуется свести к сумме, среднему, максимуму или минимуму, воспользуйтесь
        методами типа summarizing(Int | Long I Double). Эти методы принимают функцию, преобразующую потоковые объекты в
        число и возвращающую результат типа (Int | Long | Double)SummaryStatistics, одновременно вычисляя сумму, среднее,
        максимум и минимум, как показано ниже.*/
        IntSummaryStatistics summary = noVowels().collect(Collectors.summarizingInt(String::length));
        double averageWordLength = summary.getAverage();
        double maxWordLength = summary.getMax();
//        System.out.println("Average word length: " + averageWordLength);
//        System.out.println("Max word length: " + maxWordLength);
//        System.out.println("forEach:");
        /*С другой стороны, можно вызвать метод forEach(), чтобы применить функцию к каждому элементу следующим образом:*/
        noVowels().limit(10).forEach(System.out::println);
        /*В параллельном потоке данных метод forEach() выполняет обход элементов в произвольном порядке. Если же их
        требуется обработать в потоковом порядке, то следует вызвать метод forEachOrdered(). Разумеется, в этом случае могут
        быть утрачены некоторые или даже все преимущества параллелизма.*/
        noVowels().limit(10).forEachOrdered(System.out::println);
//------------------------------------------CollectingIntoMaps----------------------------------------------------------
//        /*Допустим, имеется поток данных типа Stream<Person> и его элементы требуется накапливать в отображении, чтобы в дальнейшем
//        искать людей по их идентификационному номеру. Для этой цели служит метод Collectors. toMap (),принимающий в качестве двух
//        своих аргументов функции, чтобы получить ключи и значения из отображения, как показано в следующем примере кода:*/
//        Map<Integer, String> idToName = people()
//                .collect(Collectors
//                        .toMap(ExampleCollectors.Person::getId, ExampleCollectors.Person::getName));
//        System.out.println("idToName: " + idToName);
//        /*В общем случае, когда значения должны быть конкретными элементами, в качестве второго аргумента данному методу
//        предоставляется функция Function.identity():*/
//        Map<Integer, ExampleCollectors.Person> idToPerson = people()
//                .collect(Collectors.toMap(ExampleCollectors.Person::getId, Function.identity()));
//        System.out.println("idToPerson: " + idToPerson.getClass().getName() + idToPerson);
//        /*Если же одному и тому же ключу соответствует больше одного элемента, то возникает конфликт и коллектор генерирует
//        исключение типа IllegalStateException.*/
//        idToPerson = people().collect(
//                Collectors.toMap(ExampleCollectors.Person::getId, Function.identity(), (
//                        existingValue, newValue) -> {
//                    throw new IllegalStateException();
//                }, TreeMap::new));
//        System.out.println("idToPerson: " + idToPerson.getClass().getName() + idToPerson);
//        /*Такое поведение можно изменить, предоставив данному методу в качестве третьего аргумента функцию, разрешающую подобный
//        конфликт и определяющую значение по заданному ключу, исходя из существующего или нового значения. Такая функция может
//        возвратить существующее значение, новое значение или и то и другое.В приведенном ниже примере создается отображение,
//        содержащее региональные настройки для каждого языка в виде ключа, обозначающего название языка в региональных настройках
//        по умолчанию (например, "German"), и значения, обозначающего его локализованное название (например, "Deutsch"). В
//        данном примере не учитывается, что один и тот же язык может встретиться дважды(например, немецкий в Германии и Швейцарии),
//        и поэтому в отображении сохраняется лишь первая запись.*/
//        Stream<Locale> locales2 = Stream.of(Locale.getAvailableLocales());
//        Map<String, String> languageNames = locales2.collect(
//                Collectors.toMap(
//                        Locale::getDisplayLanguage,
//                        l -> l.getDisplayLanguage(l),
//                        (existingValue, newValue) -> existingValue));
//        System.out.println("languageNames: " + languageNames);
//        /*А теперь допустим, что требуется выяснить все языки данной страны. Для этой цели понадобится отображение типа
//        Map<String, Set<String>>. Например, значением по ключу "Switzerland" является множество [French, German,Italian].
//        Сначала для каждого языка сохраняется одноэлементное множество.А всякий раз, когда обнаруживается новый язык заданной
//        страны, образуется объединение из существующего и нового множеств:*/
//        locales2 = Stream.of(Locale.getAvailableLocales());
//        Map<String, Set<String>> countryLanguageSets = locales2.collect(
//                Collectors.toMap(
//                        Locale::getDisplayCountry,
//                        l -> Collections.singleton(l.getDisplayLanguage()),
//                        (a, b) -> { // union of a and b
//                            Set<String> union = new HashSet<>(a);
//                            union.addAll(b);
//                            return union;
//                        }));
//        System.out.println("countryLanguageSets: " + countryLanguageSets);
//        /*Более простой способ получения этого отображения будет представлен в следующем разделе. Если же потребуется
//        древовидное отображение типа ТгееМар, то в качестве четвертого аргумента методу toMap () следует предоставить
//        конструктор данного класса. Необходимо также предоставить функцию объединения. Ниже приведен один из примеров из
//        начала этого раздела, переделанный с целью получить отображение типа ТгееМар.*/
//        Map<Integer, ExampleCollectors.Person> idToPerson2 = people().collect(
//                Collectors.toMap(
//                        ExampleCollectors.Person::getId,
//                        Function.identity(),
//                        (existingValue, newValue) ->
//                        { throw new IllegalStateException(); },
//                        TreeMap::new));
//        System.out.println("XXX: " + idToPerson2);
//---------------------------------------------CollectorsDownstream-----------------------------------------------------
        /*Метод groupingBy() формирует множество, значениями которого являются списки. Если требуется каким-то образом
        обработать эти списки, то следует предоставить нисходящий коллектор. Так, если вместо списков требуются множества,
        можно воспользоваться коллектором Collectors.toSet() следующим образом:*/
        Stream<Locale> locales3 = Stream.of(Locale.getAvailableLocales());
        Map<String, Set<Locale>> countryToLocaleSet = locales3.collect(groupingBy(Locale::getCountry, toSet()));
        System.out.println("countryToLocaleSet: " + countryToLocaleSet);

        locales3 = Stream.of(Locale.getAvailableLocales());
        Map<String, Long> countryToLocaleCounts = locales3.collect(groupingBy(Locale::getCountry, counting()));
        System.out.println("countryToLocaleCounts: " + countryToLocaleCounts);

        Stream<City> cities = readCities("src/main/resources/StreamArchivos/cities.txt");
        Map<String, Integer> stateToCityPopulation = cities.collect(groupingBy(
                City::getState, summingInt(City::getPopulation)));
        System.out.println("stateToCityPopulation: " + stateToCityPopulation);
        /*Метод mapping() позволяет изящнее решить задачу из предыдущего раздела — собрать все языки, употребляемые в
        стране. В предыдущем разделе вместо метода groupingBy() применялся метод toMap(). А в приведенном ниже
        решении отпадает необходимость объединять отдельные множества.*/
        cities = readCities("src/main/resources/StreamArchivos/cities.txt");
        Map<String, Optional<String>> stateToLongestCityName = cities
                .collect(groupingBy(
                        City::getState,
                        mapping(City::getName,
                                maxBy(Comparator.comparing(String::length)))));
        System.out.println("stateToLongestCityName: " + stateToLongestCityName);

        locales3 = Stream.of(Locale.getAvailableLocales());
        Map<String, Set<String>> countryToLanguages = locales3.collect(groupingBy(
                Locale::getDisplayCountry,
                mapping(Locale::getDisplayLanguage, toSet())));
        System.out.println("countryToLanguages: " + countryToLanguages);
        /*Если функция группирования или отображения возвращает тип int, long или double, элементы можно накопить в объекте
        суммарной статистики, как пояснялось в разделе 1.8. Ниже показано, как это делается. А затем из объектов суммарной
        статистики каждой группы можно получить суммарное, подсчитанное, среднее, минимальное и максимальное значения функции.*/
        cities = readCities("src/main/resources/StreamArchivos/cities.txt");
        Map<String, IntSummaryStatistics> stateToCityPopulationSummary = cities
                .collect(groupingBy(City::getState,
                        summarizingInt(City::getPopulation)));
        System.out.println(stateToCityPopulationSummary.get("NY"));

        cities = readCities("src/main/resources/StreamArchivos/cities.txt");
        Map<String, String> stateToCityNames = cities.collect(groupingBy(
                City::getState,
                reducing("", City::getName, (s, t) -> s.length() == 0 ? t : s + ", " + t)));

        cities = readCities("src/main/resources/StreamArchivos/cities.txt");
        stateToCityNames = cities.collect(groupingBy(City::getState,
                mapping(City::getName, joining(", "))));
        System.out.println("stateToCityNames: " + stateToCityNames);

//----------------------------------------Stroim-svoi-Collector---------------------------------------------------------

        Collector<Person, StringJoiner, String> personNameCollector =
                Collector.of(
                        () -> new StringJoiner(" | "),      // supplier
                        (j, p) -> j.add(p.getName().toUpperCase()),     // accumulator
                        StringJoiner::merge,						    // combiner
                        StringJoiner::toString								// finisher
                );

        List<Person> personList = new ArrayList<>(
                Arrays.asList(
                        new Person("Andrey", 35), new Person("Anton", 23),
                        new Person("Stas", 35), new Person("Karla", 44),
                        new Person("Arancha", 44), new Person("Vasya", 12)
                ));

        String names = personList.stream().collect(personNameCollector);
//		System.out.println(names);

//----------------------------------------------------------------------------------------------------------------------
    }
    public static Stream<String> noVowels() throws IOException {
        String contents = new String(Files.readAllBytes(
                Paths.get("StreamArchvos/alice30M")),
                StandardCharsets.UTF_8);
        List<String> wordList = Arrays.asList(contents.split("\\PL+"));
        Stream<String> words = wordList.stream();
        return words.map(s -> s.replaceAll("[aeiouAEIOU]", ""));
    }

    public static <T> void show(String label, Set<T> set) {
        System.out.print(label + ": " + set.getClass().getName());
        /*И если поток данных содержит объекты, отличающиеся от символьных строк,их нужно сначала преобразовать в символьные строки:*/
        System.out.println("["
                + set.stream().limit(10).map(Object::toString)
                .collect(Collectors.joining(", ")) + "]");
    }


    public static Stream<Person> people() {
        return Stream.of(new Person( "Peter", 1001), new Person("Paul", 1002),
                new Person("Mary", 1003));
    }

    public static Stream<City> readCities(String filename) throws IOException {
        return Files.lines(Paths.get(filename)).map(l -> l.split(", "))
                .peek(System.out::println).map(a -> new City(a[0], a[1], Integer.parseInt(a[2])));
    }
}



/*
----------------------------------------------------------------------------------------------------------------------
Коллекторы можно эффективно сочетать вместе, но в итоге получаются весьма запутанные выражения. Поэтому их лучше всего
использовать вместе с методом groupingBy() или partitioningBy() для обработки значений, преобразуемых из нисходящего потока
данных. В противном случае непосредственно в потоках данных просто применяются такие методы, как mар(), reduce(), count(),
max() или min().
----------------------------------------------------------------------------------------------------------------------
НА ЗАМЕТКУ! Если вызвать метод groupingByConcurrent(), то в конечном итоге будет получено отображение, которое заполняется
параллельно, если оно применяется вместе с параллельным потоком данных. В этом отношении данный метод очень похож на метод
toConcurrentMap().
----------------------------------------------------------------------------------------------------------------------
НА ЗАМЕТКУ! Каждому из вариантов метода toMap() соответствует эквивалентный метод toConcurrentMap(), получающий параллельное
отображение. Единое параллельное отображение применяется в процессе параллельного накопления. Если же общее отображение
применяется вместе с параллельным потоком данных, то такой способ более эффективный, чем объединение множеств. Но в таком
случае элементы не накапливаются в потоковом порядке, хотя это обычно не имеет особого значения.
----------------------------------------------------------------------------------------------------------------------
 Для сведения сгруппированных элементов к числам предоставляется ряд следующих коллекторов:
•counting() — производит подсчет накопленных элементов. Так, в следующем примере кода подчитывается количество региональных
 настроекдля каждой страны:
          Map<String, Long> countryToLocaleCounts = locales.collect(groupingBy(Locale::getCountry, counting()));

•summing(Int|Long|Double) — принимает в качестве аргумента функцию, применяет ее к элементам нисходящего потока данных
 и получает их сумму. Так, в следующем примере кода вычисляется суммарное население каждого штата из потока городов:
          Map<String, Integer> stateToCityPopulation = cities.collect(groupingBy(City::getState, summingInt(City::getPopulation)));

•maxBy() и minBy() — принимают в качестве аргумента компаратор и получают максимальный и минимальный элементы из
 нисходящего потока данных. Так, в следующем примере кода получается самый крупный город в каждом штате:
        Map<String, City> stateToLargestCity = cities.collect(groupingBy(City::getState, maxBy(Comparator
                                                    .comparing(City::getPopulation))));

• mapping() — применяет функцию к результатам, полученным изнисходящего потока данных, но для обработки результатов ему
 требуется ещеодин коллектор. Так, в следующем примере кода города группируются по штатам:
        Map<String, Optional<String> stateToLongestCityName = cities.collect(groupingBy(City::getState,
                                                    mapping(City::getName, maxBy(Comparator.comparing(String::length)))));
• В каждом штате получаются названия городов, которые сводятся помаксимальной длине.
----------------------------------------------------------------------------------------------------------------------
НА ЗАМЕТКУ! Имеются три варианта метода reducing (), выполняющие общие операции сведения, описываемые в разделе 1.12.
----------------------------------------------------------------------------------------------------------------------
• static <T> Collectors, ?, List<T>> toListO
• static <T> Collector<T, ?, Set<T>> toSet()
    Возвращают коллекторы, накапливающие элементы в списке или множестве.

• static <Т,С extends Collections» Collectors, ?, С> toCollection(Supplier<C> collectionFactory)
    Возвращает коллектор, накапливающий элементы в произвольной коллекции. Получает ссылку на конструктор объектов коллекции,
    например TreeSet::new.

• static Collector<CharSequence, ?, String> joining()
• static Collector<CharSequence, ?,String> joining(CharSequence delimiter)
• static Collector<CharSequence, ?, String> joining(CharSequence delimiter, CharSequence prefix, CharSequence suffix)
    Возвращают коллектор, соединяющий символьные строки. Заданный разделитель размещается между строками, а префикс и
    суффикс — перед первой строкой и после последней строки соответственно. Если разделитель, префикс и суффикс не указаны,
    их места остаются пустыми.

• static <T> Collector<T, ?, IntSummaryStatistics> summarizinglnt(ToIntFunction<? super T> mapper)
• static<T> Collector<T, ?, LongSuxnmaryStatistics> summarizingLong(ToLongFunction<? superT> mapper)
• static <T> Collector<T, ?, DoubleSummaryStatistics> summarizingDouble(ToDoubleFunction<? Super T> mapper)
    Возвращают коллекторы, производящие объект типа (IntiLong|Double)Summary-Statistics, из которого получается подсчет,
    сумма, среднее, максимум и минимум результатов применения функции таррегО к каждому элементу потока данных.

• static<T,K,U> Collector<T, ?, Map<K, U» toMap(Function<? superT,
                                                    ? extendsK> keyMapper, FunctiorK? super T, ? extends U> valueMapper)
• static<T, K, U> Collector<T, ?, Map<K, U» toMap (Function<? superT,
                   ? extendsK> keyMapper, Function<? super T, ? extends U> valueMapper, BinaryOperator<U> mergeFunction)
• static <T, K, U, M extends Map<K, U» Collectors, ?, M>
                    toMap (Function<? super T, ? extends K> keyMapper, Function<? super T,
                        ? extends U> valueMapper, BinaryOperator<U> mergeFunction, Supplier<M> 20apSupplier)
• static <T, K, U> Collectoi^T^ConcurrentMap^, U»
                    toConcurrentMap(Function<? super T, ? Extends K> keyMapper, Function<? super T, ? extends U> valueMapper)
• static <T, K, U> Collector<T, ?, ConcurrentMap<K, U»
                    toConcurrentMap (Function<? super T, ? Extends K> keyMapper,
                        Function<? super T, ? extends U> valueMapper, BinaryOperator<U> mergeFunc tion)
• static <T, K, U, M extends ConcurrentMap<K, U» Collector<T, ?, M>
                    toConcurrentMap (Function<? super T, ? extends K> keyMapper,
                        Function<? super T, ? extends U> valueMapper, BinaryOperator<U>
                            mergeFunc tion, Supplier<M> mapSupplier)
    Возвращают коллектор, производящий обычное или параллельное отображение. Функции keyMapper() и valueMapper() применяются
    к каждому накапливаемому элементу, возвращая запись в виде пары “ключ-значение” из результирующего отображения. По умолчанию
    генерируется исключение типа HlegalStateException, когда два элемента порождают одинаковый ключ. Вместо этого можно применить
    функцию mergeFunction(), объединяющую значения по одному и тому же ключу. По умолчанию получается результирующее
    отображение типа HashMap или ConcurrentHashMap. Но вместо этого можно предоставить функцию mapSupplier(), возвращающую
    требующийся экземпляр отображения.

• static<T,K> Collectors, ?, Map<K, List<T»> groupingBy(Function<? superT, ? extendsK> classifier)
• static <T,K> CollectorCT, ?, ConcurrentMap<K, List<T»> groupingByConcurrent (Function<? super T, ? extends K> classifier)
    Возвращают коллектор, производящий обычное или параллельное отображение, где ключи являются результатом применения
    функции classifier() ко всем накапливаемым элементам, а значения — списками элементов с одинаковым ключом.

• static <Т> Collector<T,?,Map<Boolean,List<T»> partitioningBy(Predicated super T> predicate)
    Возвращает коллектор, производящий отображение, где ключи принимают логическое значение true/false, а значения являются
    списками элементов, совпадающих или не совпадающих с заданным предикатом.

• static <T> Collector<T,?,Long> counting()
    Возвращает коллектор, подсчитывающий накапливаемые элементы.

• static <Т> Collector<T, ?, Integer> summingInt(ToIntFunction<? super T> mapper)
• static <T> Collector<T, ?, Long> summingLong(ToLongFunction<? super T> mapper)
• static <T> Collector<T, ?, Double> summingDouble(ToDoubleFunction<? super T> mapper)
    Возвращают коллектор, вычисляющий сумму значений, получаемых в результате применения функции таррегО к накапливаемым элементам.

• static <Т> Collector<T, ?, Optional<T>> maxBy(Comparator^ super T> comparator)
• static <T> Collector<T, ?, Optional<T>> minBy(Comparator<? super T> comparator)
    Возвращают коллектор, вычисляющий максимальный или минимальный из накапливаемых элементов, используя порядок расположения,
    который задает comparator.

• static <Т, U, A, R> Collector<T, ?, R> mapping (Function<? super T, ? extends U> mapper, Collector<? super U, A, R> downstream)
    Возвращает коллектор, производящий отображение, где ключи являются результатом применения функции таррегО к накапливаемым
    элементам, а значения — результатом накопления элементов по одному и тому же ключу с помощью коллектора downstream().
----------------------------------------------------------------------------------------------------------------------
*/
