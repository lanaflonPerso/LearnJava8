package com.trl.stream_api.a1;

import com.trl.entityes.Person;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Sources:
 * 1. https://www.youtube.com/watch?v=gTdXjRif_yo
 */
public class ExampleStream {

    public static void main(String[] args) throws IOException {

//---------------------------------------------------------------------------------------------------------------------

        // прочитать текст из файла в символьную строку
        String contents = new String(Files.readAllBytes(
                Paths.get("/home/trl/GitHub/LearnJava8/src/main/resources/StreamArchvos/alice30M.txt")), StandardCharsets.UTF_8);

        // разбить полученную символьную строку на слова;
        // небуквенные символы считаются разделителями
        List<String> words_1 = Arrays.asList(contents.split("\\PL+"));

        long count = 0;
        for (String w : words_1) {
            if (w.length() > 6) count++;
        }
//        System.out.println(count);

        count = words_1.stream()
                .filter(w -> w.length() > 6)
                .count();
//        System.out.println(count);

        count = words_1.parallelStream()
                .filter(w -> w.length() > 6)
                .count();
//        System.out.println(count);

//---------------------------Chtenia-faila-s-pomowchy-Fails.readAllBytes()---------------------------------------------

        Path path = Paths.get("/home/trl/GitHub/LearnJava8/src/main/resources/StreamArchvos/alice30M.txt");
        String contents_1 = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);

        /*Если же вместо коллекции имеется массив, то для этой цели служит метод Stream.of():*/
        Stream<String> stringStream = Stream.of(contents_1.split("\\PL+"));

        /*У метода of() имеются аргументы переменной длины, и поэтому поток данных можно создать из любого
        количества аргументов, как показано ниже. А для создания потока данных из части массива служит метод
                Arrays.stream(array, from, to).
                Stream<String> song = Stream.of("gently", "down", "the", "stream");*/
//		show("words", stringStream);

        Stream<String> song = Stream.of("gently", "down", "the", "stream");
//		show("song", song);

		/*Чтобы создать поток данных без элементов, достаточно вызвать статический метод Stream, empty()
		следующим образом:
					Stream<String> silence = Stream.empty();
					// Обобщенный тип <String> выводится автоматически;
					// что равнозначно вызову Stream.<String>enpty ()*/
        Stream<String> silence = Stream.empty();
//		show("silence", silence);



//----------------------------------------------------------------------------------------------------------------------

//		Stream.of("a1", "a2", "a3")
//                .map(s -> s.substring(1))
//                .mapToInt(Integer::parseInt)
//                .max()
//                .ifPresent(System.out::println);

//----------------------------------------------------------------------------------------------------------------------

//		IntStream.range(1,5)
//                .mapToObj(i -> "b" + i)
//                .forEach(v -> System.out.print(v + ", "));

//----------------------------------------------------------------------------------------------------------------------

//		Stream.of(1.0, 2.0, 3.0, 4.0)
//                .mapToInt(Double::intValue)
//                .mapToObj(value -> "c" + value)
//                .forEach(v -> System.out.print(v + ", "));

//---------------------------------------------Ochen-interesno----------------------------------------------------------

        // Ne vuvedetsa necheho potomu chto nety terminalnoi operacii a est tolko konveirnaia operacia
//		Stream.of("dd2", "aa2", "bb1", "bb2", "cc4")
//                .filter(v ->{
//					System.out.println("Filter: " + v);
//					return true;
//				});

//		System.out.println();
//		Stream.of("dd2", "aa2", "bb1", "bb2", "cc4")
//                .filter(v ->{
//					System.out.println("Filter: " + v);
//					return true;
//				})
//                .forEach(s -> System.out.println("forEach: " + s));

//        System.out.println();
//        Stream.of("dd2", "aa2", "bb1", "bb2", "cc4")
//                .map(s -> {
//                    System.out.println("map: " + s);
//                    return s.toUpperCase();
//                })
//                .anyMatch(s -> {
//                    System.out.println("anyMatcher: " + s);
//                    return s.startsWith("A");
//                });

//--------------------------------Ochen-vazna-razstnovka-konveinux-metodov----------------------------------------------

//		Stream.of("dd2", "aa2", "bb1", "bb2", "cc4")
//                .map(s -> {
//					System.out.println("map: " + s);
//					return s.toUpperCase();
//				})
//                .filter(s -> {
//					System.out.println("filter: " + s);
//					return s.startsWith("A");
//				})
//                .forEach(s -> System.out.println("forEach: " + s));

//		Stream.of("dd2", "aa2", "bb1", "bb2", "cc4")
//                .filter(s -> {
//					System.out.println("filter: " + s);
//					return s.startsWith("a");
//				})
//                .map(s -> {
//					System.out.println("map: " + s);
//					return s.toUpperCase();
//				})
//                .forEach(s -> System.out.println("forEach: " + s));

//-----------------------------------Operacii-sostoiania--Kak-ykorachivat-operacii--------------------------------------

//		Stream.of("dd2", "aa2", "bb1", "bb2", "cc4")
//                .sorted((s1,s2) -> {
//					System.out.printf("sort: %s;%s\n", s1, s2);
//					return s1.compareTo(s2);
//				})
//                .filter(s -> {
//					System.out.println("filter: " + s);
//					return s.startsWith("a");
//				})
//                .map(s -> {
//					System.out.println("map: " + s);
//					return s.toUpperCase();
//				})
//                .forEach(s -> System.out.println("forEach: " + s));

        // Namnoho ykarachivaetsa rabota esli filter stavit snachala
//		Stream.of("dd2", "aa2", "bb1", "bb2", "cc4")
//                .filter(s -> {
//					System.out.println("filter: " + s);
//					return s.startsWith("a");
//				})
//                .sorted((s1,s2) -> {
//					System.out.printf("sort: %s;%s\n", s1, s2);
//					return s1.compareTo(s2);
//				})
//                .map(s -> {
//					System.out.println("map: " + s);
//					return s.toUpperCase();
//				})
//                .forEach(s -> System.out.println("forEach: " + s));



//-------------------------------Rabota-s-methodami---collect()---flatMap()---reduce()----------------------------------

		/*collect() prenimaet colector kotorui sostoit s 4 razlichnux operacii eto postavwchik acomulator
		 obedinitel i finisher*/
        List<Person> personList = new ArrayList<>(
                Arrays.asList(
                        new Person("Andrey", 35), new Person("Anton", 23),
                        new Person("Stas", 35), new Person("Karla", 44),
                        new Person("Arancha", 44), new Person("Vasya", 12)
                ));
        List<Person> filteredPerson = personList.stream()
                .filter(person -> person.getName()
                .startsWith("A"))
                .sorted(Comparator.comparing(Person::getAge))
                .collect(Collectors.toList());
//		System.out.println(filteredPerson);

//---------------------------------Zdes-hryperyem-lydei-po-vozrasty-i-zapisem-ix-v-map----------------------------------

        Map<Integer, List<Person>> personGroupByAge = personList.stream().
                collect(Collectors.groupingBy(Person::getAge));
//		personGroupByAge.forEach((age,p) -> System.out.printf("age %s: %s\n", age, p));

//--------------------------------A-zdes-mu-xotim-yznat-srednii-vozrast-vlex-lydei-v-mape-------------------------------

        Double averageAge = personList.stream().collect(Collectors.averagingDouble(Person::getAge));
//		System.out.println(averageAge);

//---------------------------------Vivod-statistiku-po-listy-kotorui-proveriaem-----------------------------------------

		IntSummaryStatistics intSummaryStatistics = personList.stream().
				collect(Collectors.summarizingInt(Person::getAge));
//		System.out.println(intSummaryStatistics);

//---------------------------------Obedinenie-imeni-ludei-v-spiske-v-odny-strochky--------------------------------------

		String phrase = personList.stream().
				filter(person -> person.getAge() >= 18).
				map(Person::getName).
				collect(Collectors.joining(", ", "V Ispanii ", " soverwenoletnie"));
         // pri neobxodimosti mozno opystit prefix i suffix
//		System.out.println(phrase);

//--------------------------------Heniriryem-iz-Colleccii-map-spomowchy-stream------------------------------------------

        // Pri heneracii map vse klychi dolznu bit raznumi, esli klychi
        // ne raznue to polychim exception
		Map<Integer, String> map = personList.stream().
				collect(Collectors.toMap(Person::getAge, Person::getName, (name1, name2) -> name1 + ";" + name2));
//				collect(Collectors.toMap(Person::getAge, Person::getName)); // tak bydet eror iz za toho chto klychi dubliryytsa
//		System.out.println(map);








//---------------------------------anyMatch()---noneMatch()---allMatch()----------------------------------------------
		/*Если же требуется лишь выяснить, имеется ли вообще совпадение, то следует воспользоваться методом anyMatch (),
		как показано ниже. Этот метод принимает предикатный аргумент, и поэтому ему не требуется метод filter ().
		Имеются также методы allMatch () и noneMatch (), возвращающие логическое значение true, если с предикатом совпадают
		все элементы в потоке данных или не совпадает ни один из его элементов соответственно. Эти методы также
		выгодно выполнять в параллельном режиме.*/
//		List<String> wordsWWW = new ArrayList<>(Arrays.asList("QA", "Ba", "Cxx", "QDa", "xxxxxE", "QBaas"));
//		List<String> wordsWWW = new ArrayList<>(Arrays.asList("A", "Ba", "Cxx", "Da", "xxxxxE", "Baas"));
//		boolean anyMatch = wordsWWW.stream().parallel().anyMatch(s -> s. startsWith("Q"));
//		System.out.println(anyMatch);
//
//		boolean noneMatch = wordsWWW.stream().parallel().noneMatch(s -> s. startsWith("Q"));
//		System.out.println(noneMatch);
//
//		boolean allMatch = wordsWWW.stream().parallel().allMatch(s -> s. startsWith("Q"));
//		System.out.println(allMatch);

//--------------------------------------------------------------------------------------------------------------------
    }

    public static <T> void show(String title, Stream<T> stream) {
//        final int SIZE = 10;
        List<T> firstElements = stream
//                .limit(SIZE + 1)
                .collect(Collectors.toList());
        System.out.print(title + ": ");
        for (int i = 0; i < firstElements.size(); i++) {
            if (i > 0) System.out.print(", ");
            if (i < firstElements.size()) System.out.print(firstElements.get(i));
//            else System.out.print("...");
        }
        System.out.println();
    }
}

/*
----------------------------------------------------------------------------------------------------------------------
На первый взгляд поток данных похож на коллекцию, поскольку он позволяет преобразовывать и извлекать данные.
Но у потока данных имеются следующие существенные отличия.
    1. Поток данных не сохраняет свои элементы. Они могут храниться в основной коллекции или формироваться по требованию.
    2. Потоковые операции не изменяют их источник. Например, метод filter() не удаляет элементы из нового
       потока данных, но выдает новый поток, в котором они отсутствуют.
    3. Потоковые операции выполняются по требованию, когда это возможно. Это означает, что они не выполняются до тех
       пор, пока не потребуется их результат. Так, если требуется подсчитать только пять длинных слов вместо всех
       слов, метод filter () прекратит фильтрацию после пятого совпадения. Следовательно, потоки данных могут быть
       бесконечными!
----------------------------------------------------------------------------------------------------------------------
Такая последовательность операций весьма характерна для манипулирования потоками данных. Конвейер операций
организуется в следующие три стадии.
    1. Создание потока данных.
    2. Указание промежуточных операций для преобразования исходного потока
       данных в другие потоки — возможно, в несколько этапов.
    3. Выполнение оконечной операции для получения результата. Эта операция принуждает к выполнению по требованию
       тех операций, которые ей предшествуют. А впоследствии поток данных может больше не понадобиться.
----------------------------------------------------------------------------------------------------------------------
НА ЗАМЕТКУ! В прикладном программном интерфейсе Java API имеется целый ряд методов,возвращающих потоки данных. Так, в
классе Pattern имеется метод splitAsStream(),разделяющий последовательность символов типа CharSequence по регулярному
выражению. Например, для разделения символьной строки на отдельные слова можно воспользоваться следующим оператором:
			Stream<String> words = Pattern.compile("\\PL+").splitAsStream(contents);
А статический метод Files.lines() возвращает поток данных типа Stream, содержащий все строки из файла, как показано ниже.
			try (Stream<String> lines = Files.lines(path)) {
				Обработать строки
			}
----------------------------------------------------------------------------------------------------------------------
НА ЗАМЕТКУ! Аналогичный метод flatMap() можно обнаружить и в других классах, а не
в тех, что представляют потоки данных. Это общий принцип вычислительной техники.
Допустим, имеется обобщенный тип G (например, Stream) и функции fArgInt () и д(),
преобразующие некоторый тип т в тип G<U>, а тип и — в тип G<v> соответственно. В таком случае
эти функции можно составить вместе, используя метод flatMapO, т.е. применить сначала
функцию fArgInt О, а затем функцию д(). В этом состоит главная идея теории монад. Впрочем,
метод flatMapO можно применять, и не зная ничего о монадах.
----------------------------------------------------------------------------------------------------------------------
• Stream<T> filter (Predicated super T> p)
    Возвращает поток данных, содержащий все его элементы, совпадающие с указанным предикатом р.

• long count()
    Возвращает количество элементов в исходном потоке данных. Это оконечная операция.

• default Stream<E> stream ()
• default Stream<E> parallelStream()
    Возвращают последовательный или параллельный поток данных, состоящий из элементов исходной коллекции.

• static <T> Stream<T> of(T... values)
	Возвращает поток данных, элементами которого являются заданные значения.

• static <Т> Stream<T> empty()
	Возвращает поток данных без элементов.

• static <Т> Stream<T> generate(Supplier<T> s)
	Возвращает бесконечный поток данных, элементы которого составляются путем повторного
	вызова функции s().

• static <Т> Stream<T> iterate (Т seed, UnaryOperator<T> fArgInt)
	Возвращает бесконечный поток данных, элементы которого содержат начальные значения
	seed, функция fArgInt () сначала вызывается с начальным значением seed, а затем со значением
	предыдущего элемента и т.д.

• Stream<T> filter (Predicated super T> predicate)
	Возвращает поток данных, элементы которого совпадают с указанным предикатом.

• <R> Stream<R> map(Function<? super T,? extends R> mapper)
	Возвращает поток данных, содержащий результаты применения функции mapper () к
	элементам исходного потока данных.

• <R> Stream<R> flatMap(Function<? super T,? extends Stream<? extends R» mapper)
	Возвращает поток данных, получаемый сцеплением результатов применения функции map-
	peri) к элементам исходного потока данных. (Следует иметь в виду, что каждый результат
	представляет собой отдельный поток данных.)

• Stream<T> limit(long maxSize)
	Возвращает поток данных, состоящий из элементов исходного потока данных вплоть до
	заданной длины maxSize.

• Stream<T> skip(long n)
	Возвращает поток данных, все элементы которого, кроме начальных п элементов, взяты из
	исходного потока данных.

• static <Т> Stream<T> concat(Stream<? extends T> a, Stream<? extends T> b)
	Возвращает поток данных, элементы которого последовательно составлены из элементов
	потока а и элементов потока b.

• Stream<T> distinct()
	Возвращает поток данных, состоящий из неповторяющихся элементов исходного потока.

• Stream<T> sorted()
• Stream<T> sorted (Comparators super T> comparator)
	Возвращают поток данных, состоящий из отсортированных элементов исходного потока.
	Первый метод требует, чтобы элементы были экземплярами класса, реализующего интерфейс Comparable.

• Stream<T> peek(Consumers super T> action)
	Возвращает поток данных, состоящий из тех же самых элементов, что и у исходного потока,
	передавая каждый элемент указанной функции action () по мере употребления этого элемента.

• Optional<T> max(Comparator<? super T> comparator)
• 0ptional<T> min (Comparators super T> comparator)
	Возвращают максимальный или минимальный элемент из исходного потока данных, используя порядок расположения,
	который определяет заданный comparator, или же пустое значение типа Optional, если исходный поток данных пуст. Это
	оконечные операции.

• Optional<T> findFirst()
• Optional<T> findAny()
	Возвращают первый или любой элемент из исходного потока данных или же значение типа Optional, если исходный поток
	данных пуст. Это оконечные операции.

• boolean anyMatch (Predicates super T> predicate)
• boolean allMatch (Predicates super T> predicate)
• boolean попёМаЬсЬ (Predicates super T> predicate)
	Возвращают логическое значение true, если с заданным предикатом совпадают любые или
	все элементы исходного потока данных или же не совпадает ни один из его элементов.

• Iterator<T> iterator()
	Возвращает итератор для получения элементов исходного потока данных. Это оконечная операция.

• void forEach(Consumer<? super T> action)
	Вызывает функцию action() для каждого элемента исходного потока данных. Это оконечная операция.

• Obj есt[] toArray()
• <А> А[] toArray (IntFunctiorKA[] > generator)
	Возвращают массив объектов или объект типа А, если им передается ссылка на конструктор А[]::new. Это оконечные операции.

• <R,A> R collect (Collector<? super T, A, R> collector)
	Накапливает элемент в исходном потоке данных, используя заданный коллектор. Для многих коллекторов в классе Collectors
	имеются фабричные методы.

• Optional<T> reduce(BinaryOperator<T> accumulator)
• T reduce (T identity, BinaryOperator<T> accumulator)
• <U> U reduce (U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner)
	Формируют накапливаемый итог элементов потока данных с помощью заданной функции accumulator(). Если же предоставляется
	аргумент identity, то он становится первым накапливаемым значением. А если предоставляется аргумент combiner, то он может
	быть использован для объединения итогов по сегментам потока данных, которые накапливаются отдельно.

• <R> Rcollect (Supplier<R> supplier, BiConsumer<R, ? superT> accumulator, BiConsumer<R, R> combiner)
	Накапливает элементы в результат типа R. Для каждого сегмента потока данных вызывается функция supplier(), предоставляющая
	первоначальный результат. Функция accumulator() вызывается для добавления к нему элементов изменчивым способом, а функция
	combiner() — для объединения обоих результатов.
----------------------------------------------------------------------------------------------------------------------
*/