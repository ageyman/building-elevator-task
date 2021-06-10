package com.company;

public class Elevator extends Thread {
    private int currentCapacity;
    private final int maxCapacity;
    private int currentFloor = 0;

    public Elevator(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.currentCapacity = this.maxCapacity;
    }

    public void moveTo(int floor) {
        final int floorsDifference = Math.abs(currentFloor - floor);
        currentFloor = floor;
        try {
            sleep(1000 * floorsDifference);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getCurrentCapacity() {
        return currentCapacity;
    }

    public void setCurrentCapacity(int currentCapacity) {
        if ((maxCapacity - currentCapacity) >= 0) {
            this.currentCapacity = currentCapacity;
        }
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }
}
