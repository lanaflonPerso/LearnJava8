package com.trl.stream_api;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Stream_generate_iterate {
    public static void main(String[] args) {

        Path path = Paths.get("/home/trl/GitHub/LearnJava8/src/main/resources/StreamArchvos/alice30M.txt");

        // прочитать текст из файла в символьную строку
        String contents = null;
        try {
            contents = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

//--------------------------------------generate()---iterate()--------------------------------------------------------

		/*Для создания бесконечных потоков данных в интерфейсе Stream имеются два статических метода. В
		частности, метод generate() принимает функцию без аргументов (а формально — объект функционального
		интерфейса Supplier<T>).Всякий раз, когда требуется потоковое значение, эта функция вызывается для
		получения данного значения. Например, поток постоянных значений можно получить так:*/
        Stream<String> echos = Stream.generate(() -> "Echo").limit(20);
//		show("echos", echos);

		/*а поток случайных чисел следующим образом:
		  Stream.generate() sozdaet toze bezkonechnui stream.
		  Premichanie chtobu generator ne ywol v bezkonechnost,nuzno eho ohranichet cherez konveirnui metod limit()*/
        Stream<Double> randoms = Stream.generate(Math::random).limit(20);
//		show("randoms", randoms);

		/*Для получения бесконечных последовательностей вроде 0 1 2 3... служит метод iterate(). Он принимает
		начальное значение и функцию (а формально — объект функционального интерфейса UnaryOperator<T>)
		и повторно применяет функцию к предыдущему результату, как показано в следующем примере кода:
		Premichanie chtobu iterator ne ywol v bezkonechnost,nuzno eho ohranichet cherez konveirnui metod limit()
 		В результате вызова поток, limit(n) возвращается поток данных, оканчивающийся после п элементов или по завершении
 		исходного потока данных, если тот оказывается более коротким. Метод limit () особенно удобен для ограничения
		бесконечных потоков данных до определенной длины. Так, в следующей строке кода получается поток данных, состоящий
 		из 100 произвольных чисел: Stream<Double> randoms = Stream.generate(Math::random).limit(100);*/
        Stream<BigInteger> integers = Stream.iterate(BigInteger.ZERO, n -> n.add(BigInteger.ONE)).limit(1_000_000);
//		show("integers", integers);

        Stream<String> wordsAnotherWay = Pattern.compile("\\PL+").splitAsStream(contents);
//		show("wordsAnotherWay", wordsAnotherWay);

        try (Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)) {
//			show("lines", lines);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
