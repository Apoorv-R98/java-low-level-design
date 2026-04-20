package com.elevator.strategy;

import com.elevator.model.Elevator;
import com.elevator.model.Request;

import java.util.List;

public class NearestElevatorStrategy implements ElevatorSelectionStrategy {

    @Override
    public Elevator selectElevator(List<Elevator> elevators, Request request) {
        Elevator best = null;
        int minDist = Integer.MAX_VALUE;

        for (Elevator e : elevators) {
            int dist = Math.abs(e.getCurrentFloor() - request.getSource());
            if (dist < minDist) {
                minDist = dist;
                best = e;
            }
        }
        return best;
    }
}
