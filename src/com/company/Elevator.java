package com.company;

import java.util.concurrent.atomic.AtomicInteger;

public class Elevator extends Thread {
    private final int maxCapacity;
    private int currentCapacity;
    private int currentFloor = 0;
    private final int numberOfFloors;
    private boolean isUp = true;
    private final Manager manager;
    private final GUI gui = new GUI();

    public Elevator(Manager manager, int maxCapacity, int numberOfFloors) {
        this.manager = manager;
        this.maxCapacity = maxCapacity;
        this.currentCapacity = maxCapacity;
        this.numberOfFloors = numberOfFloors;
    }

    @Override
    public void run() {
        super.run();
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (manager.hasRequests()) {
            try {
                int passengersLeft = manager.leavePassengers(getCurrentFloor());
                currentCapacity += passengersLeft;
                gui.updateDoorColor(true);
                gui.updatePeopleInElevatorCount(maxCapacity - currentCapacity);
                System.out.println("Elevator left passengers " + "leftPassengers: " + passengersLeft);
                sleep(300);

                int takenPassengers = manager.takePassengers(getCurrentFloor());
                currentCapacity -= takenPassengers;
                gui.updateDoorColor(false);
                gui.updatePeopleInElevatorCount(maxCapacity - currentCapacity);
                System.out.println("Elevator took passengers " + "takenPassengers: " + takenPassengers);
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
        System.out.println("Current capacity is: " + currentCapacity);
        return this.currentCapacity;
    }

    private void changeFloor() throws InterruptedException {
        System.out.println("Current floor is: " + currentFloor);
        sleep(Constants.ELEVATOR_SlEEP_TIME);
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
}
