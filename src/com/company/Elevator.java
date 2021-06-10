package com.company;

public class Elevator extends Thread {
    private int currentCapacity;
    private final int maxCapacity;
    private int currentFloor = 0;
    private int numberOfFloors;
    private boolean isUp = true;
    private final Manager manager;

    public Elevator(Manager manager, int maxCapacity, int numberOfFloors) {
        this.manager = manager;
        this.maxCapacity = maxCapacity;
        this.currentCapacity = this.maxCapacity;
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
                setCurrentCapacity(getCurrentCapacity() + passengersLeft);
                System.out.println("Elevator left passengers " + "leftPassengers: " + passengersLeft);
                int takenPassengers = manager.takePassengers(getCurrentFloor());
                setCurrentCapacity(getCurrentCapacity() - takenPassengers);
                System.out.println("Elevator took passengers " + "tookedPassengers: " + takenPassengers);
                sleep(100);
                changeFloor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public synchronized int getCurrentCapacity() {
        System.out.println("Current capacity: " + currentCapacity);
        return currentCapacity;
    }

    public synchronized void setCurrentCapacity(int capacity) {
        this.currentCapacity = capacity;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    private void changeFloor() {
        System.out.println("Current floor is: " + currentFloor);
        if (currentFloor == numberOfFloors && isUp) {
            currentFloor--;
            isUp = false;
        } else if(currentFloor == 0 && !isUp) {
            currentFloor++;
            isUp = true;
        } else if (isUp) {
            currentFloor++;
        } else {
            currentFloor--;
        }
        System.out.println("Floor changed to: " + currentFloor);
    }
}
