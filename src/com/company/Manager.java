package com.company;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.stream.Collectors;

public class Manager {
    private final ArrayBlockingQueue<Person> peopleToLift = new ArrayBlockingQueue<Person>(1000);
    private final Set<Person> peopleInLift = new HashSet<Person>();
    private final Elevator elevator = new Elevator(this, Constants.ELEVATOR_MAX_CAPACITY, Constants.FLOORS_NUMBER);

    public Manager() {
        elevator.start();
    }

    public void addLiftRequest(Person person) {
        try {
            peopleToLift.put(person);
            System.out.println(person.getPersonName() + " is on: " + person.getStartFloor() + " and wants to go to: " + person.getEndFloor());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int takePassengers(int onFloor) {
        final Set<Person> peopleToTake = peopleToLift
                .stream()
                .filter(person -> person.getStartFloor() == onFloor)
                .limit(elevator.getCurrentCapacity())
                .collect(Collectors.toSet());
        peopleToLift.removeAll(peopleToTake);
        peopleInLift.addAll(peopleToTake);
        return peopleToTake.size();
    }

    public int leavePassengers(int onFloor) {
        final Set<Person> peopleToLeave = peopleInLift
                .stream()
                .filter(person -> person.getEndFloor() == onFloor)
                .collect(Collectors.toSet());
        peopleInLift.removeAll(peopleToLeave);
        return peopleToLeave.size();
    }

    public boolean hasRequests() {
        System.out.println("PeopleToLift: " + peopleToLift.size());
        return !peopleToLift.isEmpty();
    }
}
