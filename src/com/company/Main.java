package com.company;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Manager manager = new Manager();
        Random random = new Random();
        for (int i = 1; i <= 100; i++) {
            Person person = new Person(manager, random.nextInt(Constants.FLOORS_NUMBER + 1), random.nextInt(Constants.FLOORS_NUMBER + 1), "Person " + i);
            person.start();
        }
    }
}
