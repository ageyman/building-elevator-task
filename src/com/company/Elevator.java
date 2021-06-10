package com.company;

public class Elevator extends Thread {
    private int maxCapacity;
    private int startFloor = 0;
    private int currentFloor = 0;

     public void moveTo(int floor) throws InterruptedException {
         final int floorsDifference = Math.abs(currentFloor - floor);;
        currentFloor = floor;
        sleep(1000 * floorsDifference);
    }
}
