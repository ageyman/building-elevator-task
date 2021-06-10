package com.company;

public class Person extends Thread {
    private int startFloor;
    private int endFloor;
    private final String name;

    public Person(int startFloor, int endFloor, String name) {
        this.startFloor = startFloor;
        this.endFloor = endFloor;
        this.name = name;
    }
}
