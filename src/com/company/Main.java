package com.company;

public class Main {

    public static void main(String[] args) {
        Manager manager = new Manager();
        manager.start();
        for (int i = 1; i <= Constants.PEOPLE_NUMBER; i++) {
            Person.newPerson(manager, "Person " + i).start();
        }
    }
}
