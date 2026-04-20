package com.elevator.controller;

import com.elevator.model.Elevator;
import com.elevator.model.Request;
import com.elevator.strategy.ElevatorSelectionStrategy;
import com.elevator.strategy.NearestElevatorStrategy;

import java.util.ArrayList;
import java.util.List;

public class ElevatorController {

    private static ElevatorController instance;
    private final List<Elevator> elevators;
    private ElevatorSelectionStrategy strategy;

    private ElevatorController(int count) {
        elevators = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            elevators.add(new Elevator(i));
        }
        strategy = new NearestElevatorStrategy();
    }

    public static synchronized ElevatorController getInstance(int count) {
        if (instance == null) {
            instance = new ElevatorController(count);
        }
        return instance;
    }

    public List<Elevator> getElevators() {
        return elevators;
    }

    public void requestElevator(Request request) {
        Elevator best = strategy.selectElevator(elevators, request);
        best.addRequest(request);
    }

    public void step() {
        for (Elevator e : elevators) {
            e.step();
        }
    }
}
