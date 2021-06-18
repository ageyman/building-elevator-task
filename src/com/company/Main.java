package com.company;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Manager manager = new Manager();
        Random random = new Random();
        for (int i = 1; i <= Constants.PEOPLE_NUMBER; i++) {
            Person.newPerson(manager, "Person " + i).start();
        }
    }
}
