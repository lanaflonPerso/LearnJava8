package com.trl.stream_api;

import java.util.stream.Stream;

public class Stream_peek {

    public static void main(String[] args) {

//--------------------------------------------Rabota-s-methodom-peek()--------------------------------------------------

		/*И наконец, метод реек () выдает другой поток данных с теми же самыми
		элементами, что и у исходного потока, но передаваемая ему функция вызывается
		всякий раз, когда извлекается элемент. Это удобно для целей отладки, как показано ниже.
		Сообщение выводится в тот момент, когда элемент доступен в потоке данных.
		Подобным образом можно проверить, что бесконечный поток данных,
		возвращаемый методом iterate (), обрабатывается по требованию. Для целей отладки в
		методе реек() можно вызвать метод, в котором устанавливается точка прерывания.*/

		Object[] powers = Stream.iterate(1.0, p -> p * 2)
				.limit(20)
				.peek(e -> System.out.println("Fetching " + e))
				.toArray();

    }

}
