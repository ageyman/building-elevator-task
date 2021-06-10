package com.company;

public class Main {

    public static void main(String[] args) {
	Manager manager = new Manager();

        Person person1 = new Person(manager, 0, 1, "Person1");
        Person person2 = new Person(manager, 5, 2, "Person2");
        Person person3 = new Person(manager, 1, 3, "Person3");
        Person person4 = new Person(manager, 2, 4, "Person4");
        Person person5 = new Person(manager, 3, 5, "Person5");

        person1.start();
        person2.start();
        person3.start();
        person4.start();
        person5.start();
    }
}
