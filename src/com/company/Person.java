package com.company;

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
        super.run();
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
}
