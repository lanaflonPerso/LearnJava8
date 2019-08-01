package com.trl.stream_api;

import com.trl.entityes.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Stream_reduce {

    public static void main(String[] args) {

        List<Person> personList = Arrays.asList(
                new Person("Andrey", 35), new Person("Anton", 23),
                new Person("Stas", 35), new Person("Karla", 44),
                new Person("Arancha", 44), new Person("Vasya", 12)
        );

//----------------------------------------Operation-reduce------------------------------------------------------------

        // Operacia Rediyss sochetaet v sebe vse elementu potoka v edinui rezyltat
		/*Этот метод возвращает необязательное значение типа Optional, поскольку достоверный результат недостижим, если
		поток данных пуст. НА ЗАМЕТКУ! В данном примере можно сделать вызов reduce (Integer:: sum) вместо
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
//        System.out.println("\nresult: " + result);
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

    }

}
