package com.elevator.observer;

public class FloorDisplay implements Observer {

    private final int floor;

    public FloorDisplay(int floor) {
        this.floor = floor;
    }

    @Override
    public void update(String message) {
        System.out.println("Floor " + floor + " Display: " + message);
    }
}
