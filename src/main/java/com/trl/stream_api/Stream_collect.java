package com.trl.stream_api;

import com.trl.entityes.Person;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Stream_collect {

    static List<Person> personList = Arrays.asList(
            new Person("Andrey", 35), new Person("Anton", 23),
            new Person("Stas", 35), new Person("Karla", 44),
            new Person("Arancha", 44), new Person("Vasya", 12)
    );

    public static void main(String[] args) {

        List<Person> filtered = personList.stream()
                .filter(person -> person.getAge() >= 35)
                .collect(Collectors.toList());

//        System.out.println(filtered);

        Set<Person> filtered_1 = personList.stream()
                .filter(person -> person.getAge() >= 35)
                .collect(Collectors.toSet());

//        System.out.println(filtered_1);

    }

}
