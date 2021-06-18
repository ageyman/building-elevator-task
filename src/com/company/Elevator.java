package com.company;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Elevator extends Thread {
    private final int maxCapacity;
    private int currentCapacity;
    private int currentFloor = 0;
    private final int numberOfFloors;
    private boolean isUp = true;
    private final Manager manager;
    private final GUI gui = new GUI();
    private int requestsFromButton = 0;
    private final boolean shouldUseOptimization;

    public Elevator(Manager manager, int maxCapacity, int numberOfFloors, boolean shouldUseOptimization) {
        this.manager = manager;
        this.maxCapacity = maxCapacity;
        this.currentCapacity = maxCapacity;
        this.numberOfFloors = numberOfFloors;
        this.shouldUseOptimization = shouldUseOptimization;
        setGUIButtonAction();
    }

    @Override
    public void run() {
        while (true) {
            try {
                manager.hasRequests();
                int passengersLeft = manager.leavePassengers(getCurrentFloor());
                int takenPassengers = manager.takePassengers(getCurrentFloor());
                if (passengersLeft > 0 || takenPassengers > 0) {
                    currentCapacity += passengersLeft;
                    gui.updateDoorColor(true);
                    gui.updatePeopleInElevatorCount(maxCapacity - currentCapacity);
                    System.out.println("Elevator left passengers: " + passengersLeft);
                    sleep(Constants.ELEVATOR_WAIT_TIME);

                    currentCapacity -= takenPassengers;
                    gui.updateDoorColor(false);
                    gui.updatePeopleInElevatorCount(maxCapacity - currentCapacity);
                    gui.updatePeopleFlowNumbers(passengersLeft, takenPassengers);
                    System.out.println("Elevator took passengers: " + takenPassengers);
                    System.out.println("People in elevator: " + (maxCapacity - currentCapacity));
                }
                changeFloor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public int getCurrentCapacity() {
        return this.currentCapacity;
    }

    private void updateDirection() {
        final int floorToGo = manager.getNearFloorRequest();
        System.out.println("Near Floor Request: " + floorToGo);
        isUp = floorToGo > currentFloor;
    }

    private void changeFloor() throws InterruptedException {
        manager.hasRequests();
        if (shouldUseOptimization) {
            updateDirection();
        }

        System.out.println("Current floor is: " + currentFloor);
        sleep(Constants.ELEVATOR_TRAVEL_TIME);
        if (currentFloor == numberOfFloors && isUp) {
            currentFloor--;
            isUp = false;
        } else if (currentFloor == 0 && !isUp) {
            currentFloor++;
            isUp = true;
        } else if (isUp) {
            currentFloor++;
        } else {
            currentFloor--;
        }
        gui.updateFloorNumber(currentFloor);
        System.out.println("Floor changed to: " + currentFloor);
    }

    private void setGUIButtonAction() {
        gui.setAddRequestButtonAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                requestsFromButton++;
                Person.newPerson(manager, "Added from Button " + requestsFromButton).start();
            }
        });
    }
}
