package com.company;

import java.util.LinkedList;

public class Manager {
    private final Person[] peopleToLift = {};
    private final LinkedList<Person>[] peopleOnEachFloor;
    private final Elevator elevator;

    public Manager(LinkedList<Person>[] peopleOnEachFloor, Elevator elevator) {
        this.peopleOnEachFloor = peopleOnEachFloor;
        this.elevator = elevator;
    }
}
