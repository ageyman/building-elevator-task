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
                int takenPassengers = manager.takePassengers(getCurrentFloor());
                setCurrentCapacity(getCurrentCapacity() - takenPassengers);
                System.out.println("Elevator took passengers " + "tookedPassengers: " + takenPassengers);
                sleep(3000);
                int passengersLeft = manager.leavePassengers(getCurrentFloor());
                setCurrentCapacity(getCurrentCapacity() + passengersLeft);
                System.out.println("Elevator left passengers " + "leftPassengers: " + passengersLeft);
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
        return currentCapacity;
    }

    public synchronized void setCurrentCapacity(int currentCapacity) {
        this.currentCapacity = currentCapacity;
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
