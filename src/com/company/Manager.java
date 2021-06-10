package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class Manager {
    private final Person[] peopleToLift;
    private final LinkedList<Person>[] peopleOnEachFloor;
    private final ArrayList<Person> peopleCurrentlyInElevator = new ArrayList();
    private final Elevator elevator;
    private final int floorsToManage;

    public Manager(Person[] peopleToLift, Elevator elevator, int floorsToManage) {
        this.peopleToLift = peopleToLift;
        this.floorsToManage = floorsToManage;
        this.elevator = elevator;
        this.peopleOnEachFloor = new LinkedList[floorsToManage];
    }

    public void start() {
        Arrays
                .stream(peopleToLift)
                .forEach(person -> peopleOnEachFloor[person.getStartFloor()].addFirst(person));

        boolean isEveryoneLifted = Arrays.stream(peopleToLift).allMatch(person -> person.isLifted());

        while (!isEveryoneLifted) {
            movePeopleToElevator();
            moveElevatorToNextFloor();
            removePeopleFromElevator();
        }
    }

    private void moveElevatorToNextFloor() {
        elevator.moveTo(findElevatorNextFloorToMove());
    }

    private int findElevatorNextFloorToMove() {
        return peopleOnEachFloor[elevatorCurrentFloor()]
                .stream()
                .mapToInt(p -> p.getEndFloor())
                .boxed()
                .collect(Collectors.toList())
                .stream()
                .min(Comparator.comparingInt(i -> Math.abs(i - elevatorCurrentFloor()))).get();
    }

    private void movePeopleToElevator() {
      final int peopleCurrentlyOnFloor = peopleOnEachFloor[elevatorCurrentFloor()].size();
      if (peopleCurrentlyOnFloor == 0) {
          return;
      }
      final int availablePlacesInElevator = elevator.getCurrentCapacity();
      int peopleToGetInElevator = peopleCurrentlyOnFloor > availablePlacesInElevator ? availablePlacesInElevator : peopleCurrentlyOnFloor;
      removePeopleFromFloorsList(peopleToGetInElevator);
      elevator.setCurrentCapacity(elevator.getMaxCapacity() - peopleToGetInElevator);
    }

    private void removePeopleFromElevator() {

    }

    private int elevatorCurrentFloor() {
        return elevator.getCurrentFloor();
    }

    private void removePeopleFromFloorsList(int peopleToRemove) {
        for (int i = 0; i < peopleToRemove; i++) {
           final Person person = peopleOnEachFloor[elevatorCurrentFloor()].removeLast();
           peopleCurrentlyInElevator.add(person);
        }
    }
}
