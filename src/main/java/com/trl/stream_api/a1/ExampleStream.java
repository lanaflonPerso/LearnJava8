package com.trl.stream_api.a1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Sources:
 *         1. https://www.youtube.com/watch?v=gTdXjRif_yo
 * */
public class ExampleStream {
    public static void main(String[] args) throws IOException {
//---------------------------------------------------------------------------------------------------------------------
//        // прочитать текст из файла в символьную строку
//        String contents = new String(Files.readAllBytes(
//                Paths.get("src/main/resources/StreamArchivos/alice30M")), StandardCharsets.UTF_8);
//        // разбить полученную символьную строку на слова;
//        // небуквенные символы считаются разделителями
//        List<String> words = Arrays.asList(contents.split("\\PL+"));
//
//        long count = 0;
//        for (String w : words) { if (w.length() > 12) count++; }
//        System.out.println(count);
//
//        count = words.stream().filter(w -> w.length() > 12).count();
//        System.out.println(count);
//
//        count = words.parallelStream().filter(w -> w.length() > 12).count();
//        System.out.println(count);
//---------------------------Chtenia-faila-s-pomowchy-Fails.readAllBytes()---------------------------------------------
//		Path path = Paths.get("/root/Datos/Programacion/JAVA/java_lessons/src/main/resources/StreamArchivos/alice30M");
//		String contents = new String(Files.readAllBytes(path),
//				StandardCharsets.UTF_8);
//		/*Если же вместо коллекции имеется массив, то для этой цели служит метод Stream.of():*/
//		Stream<String> stringStream = Stream.of(contents.split("\\PL+"));
//        /*У метода of() имеются аргументы переменной длины, и поэтому поток данных можно создать из любого
//        количества аргументов, как показано ниже. А для создания потока данных из части массива служит метод
//                Arrays.stream(array, from, to).
//                Stream<String> song = Stream.of("gently", "down", "the", "stream");*/
//		show("words", stringStream);
//		Stream<String> song = Stream.of("gently", "down", "the", "stream");
//		show("song", song);
//		/*Чтобы создать поток данных без элементов, достаточно вызвать статический метод Stream, empty()
//		следующим образом:
//					Stream<String> silence = Stream.empty();
//					// Обобщенный тип <String> выводится автоматически;
//					// что равнозначно вызову Stream.<String>enpty ()*/
//		Stream<String> silence = Stream.empty();
//		show("silence", silence);
//--------------------------------------generate()---iterate()--------------------------------------------------------
//		/*Для создания бесконечных потоков данных в интерфейсе Stream имеются два статических метода. В
//		частности, метод generate() принимает функцию без аргументов (а формально — объект функционального
//		интерфейса Supplier<T>).Всякий раз, когда требуется потоковое значение, эта функция вызывается для
//		получения данного значения. Например, поток постоянных значений можно получить так:*/
//		Stream<String> echos = Stream.generate(() -> "Echo");
//		show("echos", echos);
//		/*а поток случайных чисел следующим образом:
//		  Stream.generate() sozdaet toze bezkonechnui stream.
//		  Premichanie chtobu generator ne ywol v bezkonechnost,nuzno eho ohranichet cherez konveirnui metod limit()*/
//		Stream<Double> randoms = Stream.generate(Math::random);
//		show("randoms", randoms);
//		/*Для получения бесконечных последовательностей вроде 0 1 2 3... служит метод iterate(). Он принимает
//		начальное значение и функцию (а формально — объект функционального интерфейса UnaryOperator<T>)
//		и повторно применяет функцию к предыдущему результату, как показано в следующем примере кода:
//		Premichanie chtobu iterator ne ywol v bezkonechnost,nuzno eho ohranichet cherez konveirnui metod limit()
// 		В результате вызова поток, limit(n) возвращается поток данных, оканчивающийся после п элементов или по завершении
// 		исходного потока данных, если тот оказывается более коротким. Метод limit () особенно удобен для ограничения
//		бесконечных потоков данных до определенной длины. Так, в следующей строке кода получается поток данных, состоящий
// 		из 100 произвольных чисел: Stream<Double> randoms = Stream.generate(Math::random).limit(100);*/
//		Stream<BigInteger> integers = Stream.iterate(BigInteger.ZERO, n -> n.add(BigInteger.ONE));
//		show("integers", integers);
//
//		Stream<String> wordsAnotherWay = Pattern.compile("\\PL+").splitAsStream(
//				contents);
//		show("wordsAnotherWay", wordsAnotherWay);
//
//		try(Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)){
//			show("lines", lines);
//		}
//--------------------------------------------------------------------------------------------------------------------
//		Stream.of("java.a1", "a2", "a3").
//				map(s -> s.substring(1)).
//				mapToInt(Integer::parseInt).
//				max().
//				ifPresent(System.out::println);
////------------------------------------------------------------------------------------------------------------------
//		IntStream.range(1,5).
//				mapToObj(i -> "b" + i).
//				forEach(v -> System.out.print(v + ", "));
////------------------------------------------------------------------------------------------------------------------
//		System.out.println();
//		Stream.of(1.0, 2.0, 3.0, 4.0).
//				mapToInt(Double::intValue).
//				mapToObj(value -> "c" + value).
//				forEach(v -> System.out.print(v + ", "));
////------------------------------------------Ochen-interesno----------------------------------------------------------
//		// Ne vuvedetsa necheho potomu chto nety terminalnoi operacii
//		// a est tolko konveirnaia operacia
//		System.out.println();
//		Stream.of("dd2", "aa2", "bb1", "bb2", "cc4").
//				filter(v ->{
//					System.out.println("Filter: " + v);
//					return true;
//				});
////------------------------------------------------------------------------------------------------------------------
//		System.out.println();
//		Stream.of("dd2", "aa2", "bb1", "bb2", "cc4").
//				filter(v ->{
//					System.out.println("Filter: " + v);
//					return true;
//				}).
//				forEach(s -> System.out.println("Pechatat s pomowchy forEach: " + s));
////------------------------------------------------------------------------------------------------------------------
//		System.out.println();
//		Stream.of("dd2", "aa2", "bb1", "bb2", "cc4").
//				map(s -> {
//					System.out.println("map: " + s);
//					return s.toUpperCase();
//				}).anyMatch(s -> {
//			System.out.println("anyMatcher: " + s);
//			return s.startsWith("A");
//		});
//-----------------------------Ochen-vazna-razstnovka-konveinux-metodov----------------------------------------------
//		System.out.println();
//		Stream.of("dd2", "aa2", "bb1", "bb2", "cc4").
//				map(s -> {
//					System.out.println("map: " + s);
//					return s.toUpperCase();
//				}).filter(s -> {
//					System.out.println("filter: " + s);
//					return s.startsWith("A");
//				}).forEach(s -> System.out.println("forEach: " + s));
//
//		System.out.println("---------------------------------------");
//		System.out.println();
//		Stream.of("dd2", "aa2", "bb1", "bb2", "cc4").
//				filter(s -> {
//					System.out.println("filter: " + s);
//					return s.startsWith("a");
//				}).map(s -> {
//					System.out.println("map: " + s);
//					return s.toUpperCase();
//				}).forEach(s -> System.out.println("forEach: " + s));
//---------------------------------Operacii-sostoiania--Kak-ykorachivat-operacii--------------------------------------
//		Stream.of("dd2", "aa2", "bb1", "bb2", "cc4").
//				sorted((s1,s2) -> {
//					System.out.printf("sort: %s;%s\n", s1, s2);
//					return s1.compareTo(s2);
//				}).filter(s -> {
//					System.out.println("filter: " + s);
//					return s.startsWith("a");
//				}).map(s -> {
//					System.out.println("map: " + s);
//					return s.toUpperCase();
//				}).forEach(s -> System.out.println("forEach: " + s));
//		System.out.println("----Namnoho-ykarachivaetsa-rabota-esli-filter-stavit-snachala----");
//		Stream.of("dd2", "aa2", "bb1", "bb2", "cc4").
//				filter(s -> {
//					System.out.println("filter: " + s);
//					return s.startsWith("a");
//				}).sorted((s1,s2) -> {
//					System.out.printf("sort: %s;%s\n", s1, s2);
//					return s1.compareTo(s2);
//				}).map(s -> {
//					System.out.println("map: " + s);
//					return s.toUpperCase();
//				}).forEach(s -> System.out.println("forEach: " + s));
//-----------------------------------------Rabota-s-methodom-limit()--------------------------------------------------
		/*Если опустить упорядочение, то можно также ускорить выполнение метода limit(). Если же требуется обработать любые
		 п элементов из потока данных и при этом неважно, какие из них будут получены, то с этой целью можно сделать следующий вызов:
							Stream<String> sample = words.parallelStream().unordered().limit(n);*/
//-------------------------------------Povtornoe-ispolzovanie-Potokov------------------------------------------------
//		Stream<String> stringStream = Stream.of("dd2", "aa2", "bb1", "bb2", "cc4").filter(s -> s.startsWith("a"));
//		stringStream.anyMatch(s -> true);  // poperacia vupolniaetsa yspewno
////		stringStream.noneMatch(s -> true); // Vuletaet Exception:
//		//Exception in thread "main" java.lang.IllegalStateException: stream has already been operated upon or closed
//
//		// a tak mozno pereispolzovat stream
//		Supplier<Stream<String>> streamSupplier =
//				() -> Stream.of("dd2", "aa2", "bb1", "bb2", "cc4").filter(s -> s.startsWith("a"));
//		streamSupplier.get().anyMatch(s -> true); // operacia proidot yspewno
//		streamSupplier.get().noneMatch(s -> true);  // zdes takze bydet vso xorowo
//----------------------------Rabota-s-methodami---collect()---flatMap()---reduce()----------------------------------
		/*collect() prenimaet colector kotorui sostoit s 4 razlichnux operacii eto postavwchik acomulator
		 obedinitel i finisher*/
        List<Person> personList = new ArrayList<>(
                Arrays.asList(new Person("Andrey", 35), new Person("Anton", 23),
                        new Person("Stas", 35), new Person("Karla", 44),
                        new Person("Arancha", 44), new Person("Vasya", 12)));
//		List<Person> filteredPerson = personList.stream().
//				filter(person -> person.getName().startsWith("A")).
//				collect(Collectors.toList());
//		System.out.println(filteredPerson);
//-------------------------------Zdes-hryperyem-lydei-po-vozrasty-i-zapisem-ix-v-map----------------------------------
//		Map<Integer, List<Person>> personGroupByAge	 = personList.stream().
//				collect(Collectors.groupingBy(p -> p.getAge()));
//		personGroupByAge.forEach((age,p) -> System.out.printf("age %s: %s\n", age, p));
//------------------------------A-zdes-mu-xotim-yznat-srednii-vozrast-vlex-lydei-v-mape-------------------------------
//		Double averageAge = personList.stream().collect(Collectors.averagingDouble(p -> p.getAge()));
//		System.out.println(averageAge);
//-------------------------------Vivod-statistiku-po-listy-kotorui-proveriaem-----------------------------------------
//		IntSummaryStatistics intSummaryStatistics = personList.stream().
//				collect(Collectors.summarizingInt(p -> p.getAge()));
//		System.out.println(intSummaryStatistics);
//-------------------------------Obedinenie-imeni-ludei-v-spiske-v-odny-strochky--------------------------------------
//		String phrase = personList.stream().
//				filter(person -> person.getAge() >= 18).
//				map(person -> person.getName()).
//				collect(Collectors.joining(", ", "V Ispanii ", " soverwenoletnie"));
        // pri neobxodimosti mozno opstit prefix i suffix
//		System.out.println(phrase);
//-----------------------------Heniriryem-iz-Colleccii-map-spomowchy-stream------------------------------------------
        // Pri heneracii map vse klychi dolznu bit raznumi, esli klychi
        // ne raznue to polychim exception
//		Map<Integer, String> map = personList.stream().
//				collect(Collectors.toMap(p -> p.getAge(), p -> p.getName(), (name1, name2) -> name1 + ";" + name2));
//				collect(Collectors.toMap(p -> p.getAge(), p -> p.getName())); // tak bydet eror iz za toho chto
        // klychi dubliryytsa
//		System.out.println(map);
//--------------------------------------Stroim-svoi-Collector---------------------------------------------------------
//		Collector<Person, StringJoiner, String> personNameCollector =
//				Collector.of(
//						() -> new StringJoiner(" | "),      // supplier
//						(j, p) -> j.add(p.getName().toUpperCase()),     // accumulator
//						StringJoiner::merge,						    // combiner
//					StringJoiner::toString								// finisher
//				);
//		String names = personList.stream().collect(personNameCollector);
//		System.out.println(names);
//---------------------------------------flatMap()----------------------------------------------------------------------------
        // flatMap() preobrazyet kazdui element potoka v potok dryhix obektov, takim obrazom kazdui obiekt bydet
        // pereobrazovan v novui potok drygix obektov
//		List<Foo> fooList = new ArrayList<>();
//		// create foos
//		IntStream.range(1,4).
//				forEach(i -> fooList.add(new Foo("Foo" + i)));
//
//		fooList.forEach(foo -> IntStream.
//				range(1,4).
//				forEach(i -> foo.listBars.add(new Bar("Bar" + i + " <- " + foo.getName()))));
//
//		fooList.stream().
//				flatMap(foo -> foo.listBars.stream()).
//				forEach(bar -> System.out.println(bar.getName()));

////---------Analogichne-rewenie-no-zdeano-nemnoho-po-drygomy
//		fooList.stream().
//				flatMap(foo -> foo.listBars.stream()).
//				forEach(bar -> System.out.println(bar.getName()));
//
//		IntStream.range(1,4).
//				mapToObj(i -> new Foo("Foo" + i)).
//				peek(foo -> IntStream.range(1,4).
//						mapToObj(i -> new Bar("Bar" + i + " <- " + foo.getName())).
//						forEach(foo.listBars::add)).
//				flatMap(foo -> foo.listBars.stream()).
//				forEach(bar -> System.out.println(bar.getName()));

//-------------Danui-premer-nam-pokazuvaet-kak-mozno-zdelat-proverky-na-null-----------------------------------------
//		Outer outer = new Outer();
//		if(outer != null && outer.nested != null && outer.nested.inner != null){
//			System.out.println(outer.nested.inner.foo);
//		}

////---------A-vot-tak-mozno-zdelat-s-pomowchy-StreamAPI
//		Optional.of(new Outer())
//				.flatMap(outer1 -> Optional.ofNullable(outer1.nested))
//				.flatMap(nested -> Optional.ofNullable(nested.inner))
//				.flatMap(inner -> Optional.ofNullable(inner.foo))
//				.ifPresent(System.out::println);

//----------------------------------------Operation-reduce------------------------------------------------------------
        // Operacia Rediyss sochetaet v sebe vse elementu potoka v edinui rezyltat
		/*Этот метод возвращает необязательное значение типа Optional, поскольку достоверный результат недостижим, если
		поток данных пусг. НА ЗАМЕТКУ! В данном примере можно сделать вызов reduce (Integer:: sum) вместо
		вызова reduce ((х, у) -> х + у).*/

//--------zdes mu opredilaem samoho naistarwoho cheloveka v spiske
        personList.stream()
                .reduce((p1, p2) -> p1.getAge() > p2.getAge() ? p1 : p2)
                .ifPresent(System.out::println);

//--------zdes vse elementu spiska zbivaem v kychy, toes imena do emon i goda do godov
		/*Ниже приведена вторая форма вызова метода reduce(). Тождественный элемент возвращается в том случае,
		если поток данных пуст и больше не нужно обращаться к классу Optional.*/
        Person person = personList.stream()
                .reduce(new Person("", 0), (p1q, p2q) -> {
                    p1q.setAge(p1q.getAge() + p2q.getAge());
                    p1q.setName(p1q.getName() + p2q.getName());
                    return p1q;
                });
        System.out.printf("name=%s; age=%s", person.getName(), person.getAge());
		/*А теперь допустим, что имеется поток объектов и требуется получить сумму некоторых свойств, например, длину всех
		символьных строк в потоке. Для этой цели не годится простая форма метода reduce(), поскольку в ней требуется
		функция (Т, Т) -> Т с одинаковыми типами аргументов и возвращаемого результата. Но в данном случае имеются два
		разных типа: String — для элементов потока данных и int — для накапливаемого результата. На этот случай имеется
		отдельная форма вызова метода reduce().
		Прежде всего нужно предоставить функцию накопления (total, word) -> total + word.length(), которая вызывается
		повторно, образуя сумму нарастающим итогом. Но если вычисление этой суммы распараллелено, то оно разделяется на
		несколько параллельных вычислений, результаты которых должны быть объединены. Для этой цели предоставляется вторая
		функция. Ниже приведена полная форма вызова метода reduce() в данном случае.*/
        List<String> wordsZ = new ArrayList<>(Arrays.asList("aa", "bbb", "cccc", "ddddd", "eeeeee"));
        int result = wordsZ.stream().reduce(0,
                (total, word) -> total + word.length(),
                (total1, total2) -> total1 + total2);
        System.out.println("\nresult: " + result);
		/*НА ЗАМЕТКУ! На практике методом reduce() приходится пользоваться нечасто. Ведь намного проще преобразовать исходный
		поток символьных строк в поток чисел и воспользоваться одним из методов для вычисления суммы, максимума или минимума.
		(Подробнее потоки чисел рассматриваются далее, в разделе 1.13.) В данном конкретном случае можно было бы сделать вызов
							words.mapToInt(String::length).sum()
		Это было бы проще и эффективнее, поскольку не потребовало бы упаковки.
		НА ЗАМЕТКУ! Иногда метод reduce () оказывается недостаточно обобщенным. Допустим, требуется накопить результаты
		во множестве типа BitSet. Если распараллелить эту коллекцию, то разместить ее элементы в одном множестве типа
		BitSet не удастся, поскольку объект типа BitSet не является потокобезопасным. Именно поэтому нельзя воспользоваться
		методом reduce(). Каждый сегмент исходной коллекции должен начинаться со своего пустого множества, а методу reduce()
		можно предоставить только одно тождественное значение. В таком случае следует воспользоваться методом collect(),
		который принимает следующие аргументы. Поставщик для получения новых экземпляров целевого объекта. Например, конструктор
		для построения хеш-множества. Накопитель, вводящий элемент в целевой объект. Например, метод add().Объединитель,
		соединяющий два объекта в один. Например, метод addAll(). Ниже показано, каким образом метод collect () вызывается
		для множества битов.
		BitSet result = stream.collect(BitSet::new, BitSet::set, BitSet::or);*/

//---------zdes mu dodaem vse godu perso iz lista  v odin obwchii god
//		Integer ageSum = personList.stream()
//				.reduce(0, (sum, p) -> sum += p.getAge(), (sum1, sum2) -> sum1 + sum2);
//		System.out.println("\n" + ageSum);

//---------na-pervui-moment-ne-ochividnaia-sityacia-ostorozno
        // zdes proiavliaetsa lenivost stream, iz za lenivosti oni ne vipolniayt blok s combinerom
//		Integer ageSum = personList.stream()
//				.reduce(0,
//						(sum, p) -> {
//							System.out.printf("acomulator: sum%s; person=%s\n", sum, p);
//							return sum += p.getAge();
//						},
//						(sum1, sum2) -> {
//							System.out.printf("combiner: sum1=%s; sum2=%s\n", sum1, sum2);
//							return sum1 + sum2;
//						});
//		System.out.println("\n" + ageSum);

//-----------zdes-delaetsa-vso-toze-samoe-no-razparalelivaetsa-potok-i-v-etom-slychai-pechataetsa-combier
        // esli vukorustovyvat paralelStream na malom kolichestve elementov collection to eto vuxodit zatratno
        // potomuchto stoimos sozdania parallelStream stoit namnoho bolwe chem prosto stream
        // chtobu bulo opravdano vukorustovyvvannia parallelStream nado chtobu bulo mnoho elementov v collection
        // gdeto vosle 10_000 pri etom rozklade bydet prerost proizvodimosti
//		Integer ageSum = personList.parallelStream()
//				.reduce(0,
//						(sum, p) -> {
//							System.out.printf("acomulator: sum%s; person=%s\n", sum, p);
//							return sum += p.getAge();
//						},
//						(sum1, sum2) -> {
//							System.out.printf("combiner: sum1=%s; sum2=%s\n", sum1, sum2);
//							return sum1 + sum2;
//						});
//		System.out.println("\n" + ageSum);

//-----------------------------Rabota-s-methodom-concat()-------------------------------------------------------------
//		List<String> stringList = new ArrayList<>(Arrays.asList("A", "B", "C"));
//		List<String> stringList1 = new ArrayList<>(Arrays.asList("1", "2", "3"));
//		Stream<String> stringStream = stringList.stream();
//		Stream<String> stringStream1 = stringList1.stream();
//		Stream<String> result = Stream.concat(stringStream, stringStream1);
//		result.forEach(v -> System.out.print(v + ", "));

//-----------------------------Rabota-s-methodom-distinct()-----------------------------------------------------------
		/*Некоторые операции могут быть распараллелены более эффективно, если требование упорядочения опускается. Вызывая
		метод Stream, unordered(), можно указать, что упорядочение не имеет значения. Это, в частности, выгодно при выполнении
		операции методом Stream.distinct(). В упорядоченном потоке метод distinct() сохраняет первый из всех равных элементов.
		Этим ускоряется распараллеливание, поскольку в потоке исполнения, обрабатывающем отдельный сегмент, неизвестно, какие
		именно элементы следует отбросить, до тех пор,пока сегмент не будет обработан. Если же допускается сохранить любой
		однозначный элемент, то все сегменты могут быть обработаны параллельно (с помощью общего множества для отслеживания дубликатов).*/
//		List<String> stringList = new ArrayList<>(Arrays.asList("A", "B", "C", "D", "E", "B"));
//		stringList.stream().distinct().forEach(v -> System.out.print(v + ", "));

//------------------------------Rabota-s-methodom-sorted()------------------------------------------------------------
        List<String> words = new ArrayList<>(Arrays.asList("A", "Ba", "Cxx", "Da", "xxxxxE", "Baas"));
//		words.stream()
//				.sorted(Comparator.comparing(String::length).reversed())
//				.forEach(v -> System.out.print(v + ", "));
//		System.out.println(words);

//-----------------------------Rabota-s-methodom-peek()---------------------------------------------------------------
		/*И наконец, метод реек () выдает другой поток данных с теми же самыми
		элементами, что и у исходного потока, но передаваемая ему функция вызывается
		всякий раз, когда извлекается элемент. Это удобно для целей отладки, как показано ниже.
		Сообщение выводится в тот момент, когда элемент доступен в потоке данных.
		Подобным образом можно проверить, что бесконечный поток данных,
		возвращаемый методом iterate (), обрабатывается по требованию. Для целей отладки в
		методе реек() можно вызвать метод, в котором устанавливается точка прерывания.*/
//		Object[] powers = Stream.iterate(1.0, p -> p * 2)
//				.limit(20)
//				.peek(e -> System.out.println("Fetching " + e))
//				.toArray();

//---------------------------Rabota-s-methodom-count()----------------------------------------------------------------
//		long count = words.stream().count();
//        System.out.println(count);

//----------------------------Robota-s-methodami-min()-max()----------------------------------------------------------
//		Optional<String> min = words.stream().min(Comparator.comparing((String::length)));
//		min.stream().forEach(System.out::println);
//
//		Optional<String> max = words.stream().max(Comparator.comparing((String::length)));
//		max.stream().forEach(System.out::println);
//
//		List<String> words1 = new ArrayList<>();
//		Optional<String> largest = words1.stream().max(String::compareToIgnoreCase);
//		System.out.println("largest: " + largest.orElse("this Collection is empty"));

//---------------------------------------findFirst()------------------------------------------------------------------
//		List<String> wordsWWW = new ArrayList<>(Arrays.asList("QA", "Ba", "Cxx", "QDa", "xxxxxE", "QBaas"));
//		Optional<String> startsWithQ = wordsWWW.stream().filter(s -> s.startsWith("Q")).findFirst();
//		startsWithQ.stream().forEach(System.out::println);

//---------------------------------------findAny()---------------------------------------------------------------------
		/*Если же требуется любое совпадение, а не только первое, то следует воспользоваться методом findAny (),
		как показано ниже. Это оказывается эффективным при распараллеливании потока данных, поскольку поток может
		известить о любом обнаруженном в нем совпадении, вместо того чтобы ограничиваться только первым совпадением.*/
//		Optional<String> optionalSAAA = wordsWWW.stream().filter(s -> s.startsWith("Q")).findAny();
//		optionalSAAA.stream().forEach(v -> System.out.print(v + ", "));

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
        final int SIZE = 10;
        List<T> firstElements = stream
                .limit(SIZE + 1)
                .collect(Collectors.toList());
        System.out.print(title + ": ");
        for (int i = 0; i < firstElements.size(); i++) {
            if (i > 0) System.out.print(", ");
            if (i < SIZE) System.out.print(firstElements.get(i));
            else System.out.print("...");
        }
        System.out.println();
    }
}

class Foo {
    private String name;
    List<Bar> listBars = new ArrayList<>();

    public Foo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Bar {
    private String name;

    public Bar(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Outer {
    Nested nested;
}

class Nested {
    Inner inner;
}

class Inner {
    String foo;
}

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "name=" + name + '\'' +
                ", age=" + age;
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