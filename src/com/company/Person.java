package com.company;

import java.util.Random;

public class Person extends Thread {
    private final int startFloor;
    private final int endFloor;
    private final Manager manager;
    private final String personName;

    public Person(Manager manager, int startFloor, int endFloor, String personName) {
        this.manager = manager;
        this.startFloor = startFloor;
        this.endFloor = endFloor;
        this.personName = personName;
    }

    @Override
    public void run() {
        manager.addLiftRequest(this);
    }

    public int getStartFloor() {
        return startFloor;
    }

    public int getEndFloor() {
        return endFloor;
    }

    public String getPersonName() {
        return personName;
    }

    static Person newPerson(Manager manager, String name) {
        Random random = new Random();
        final int startFloor = random.nextInt(Constants.FLOORS_NUMBER + 1);
        int endFloor = random.nextInt(Constants.FLOORS_NUMBER + 1);
        while (startFloor == endFloor) {
            endFloor = random.nextInt(Constants.FLOORS_NUMBER + 1);
        }

        return new Person(manager,
                startFloor,
                endFloor,
                name);

    }
}
