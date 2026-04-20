package com.elevator.strategy;

import com.elevator.model.Elevator;
import com.elevator.model.Request;

import java.util.List;

public interface ElevatorSelectionStrategy {
    Elevator selectElevator(List<Elevator> elevators, Request request);
}
