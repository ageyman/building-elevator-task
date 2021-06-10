package com.company;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Manager manager = new Manager();
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            Person person = new Person(manager, random.nextInt(51), random.nextInt(51), "Person " + i);
            person.start();
        }
    }
}
