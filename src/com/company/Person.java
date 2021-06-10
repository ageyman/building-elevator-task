package com.company;

public class Person extends Thread {
    private int startFloor;
    private int endFloor;
    private boolean isLifted = false;
    private final String personName;

    public Person(int startFloor, int endFloor, String personName) {
        this.startFloor = startFloor;
        this.endFloor = endFloor;
        this.personName = personName;
    }

    public int getStartFloor() {
        return startFloor;
    }

    public int getEndFloor() {
        return endFloor;
    }

    public boolean isLifted() {
        return isLifted;
    }

    public void setLifted() {
        isLifted = true;
    }
}
