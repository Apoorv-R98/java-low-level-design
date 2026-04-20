package com.elevator.model;

import com.elevator.exception.OverCapacityException;
import com.elevator.observer.Observer;
import com.elevator.state.ElevatorState;
import com.elevator.state.IdleState;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Elevator {

    private final int id;
    private int currentFloor;
    private final int capacity = 5;
    private int currentLoad = 0;

    private final Queue<Integer> requests = new LinkedList<>();
    private ElevatorState state;

    private final List<Observer> observers = new ArrayList<>();

    public Elevator(int id) {
        this.id = id;
        this.currentFloor = 0;
        this.state = new IdleState();
    }

    public synchronized void addRequest(Request req) {
        if (currentLoad >= capacity) {
            throw new OverCapacityException("Elevator " + id + " is full");
        }
        requests.offer(req.getSource());
        requests.offer(req.getDestination());
        currentLoad++;
    }

    public void step() {
        state.handle(this);
    }

    public boolean hasRequests() {
        return !requests.isEmpty();
    }

    public int peekNextRequest() {
        return requests.peek();
    }

    public int pollNextRequest() {
        return requests.poll();
    }

    public void setState(ElevatorState state) {
        this.state = state;
    }

    public void incrementFloor() {
        currentFloor++;
    }

    public void decrementFloor() {
        currentFloor--;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void reduceLoad() {
        currentLoad = Math.max(0, currentLoad - 1);
    }

    public void addObserver(Observer obs) {
        observers.add(obs);
    }

    public void notifyObservers(String message) {
        for (Observer obs : observers) {
            obs.update("Elevator " + id + ": " + message);
        }
    }
}
