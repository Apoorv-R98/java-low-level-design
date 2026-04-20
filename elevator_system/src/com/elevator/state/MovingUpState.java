package com.elevator.state;

import com.elevator.model.Elevator;

public class MovingUpState implements ElevatorState {

    @Override
    public void handle(Elevator elevator) {
        int target = elevator.pollNextRequest();

        while (elevator.getCurrentFloor() < target) {
            elevator.incrementFloor();
            elevator.notifyObservers("Moving UP to " + elevator.getCurrentFloor());
        }

        elevator.setState(new DoorOpenState());
    }
}
