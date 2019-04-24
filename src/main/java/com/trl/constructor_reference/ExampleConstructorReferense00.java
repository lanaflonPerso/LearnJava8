package com.trl.constructor_reference;

public class ExampleConstructorReferense00 {

    public static void main(String[] args) {

        PersonFactory<Person> personFactory = Person::new;
        Person person = personFactory.create("Peter", "Parker");

    }

}


interface PersonFactory<P extends Person> {
    P create(String firstName, String lastName);
}

class Person {
    String firstName;
    String lastName;

    Person() {}

    Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
