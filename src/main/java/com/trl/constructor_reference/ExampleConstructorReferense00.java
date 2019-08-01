package com.trl.constructor_reference;

import com.trl.entityes.Person;

public class ExampleConstructorReferense00 {

    public static void main(String[] args) {

        PersonFactory<Person> personFactory = Person::new;
        Person person = personFactory.create("Peter", "Parker");

    }

}


interface PersonFactory<P extends Person> {
    P create(String firstName, String lastName);
}
