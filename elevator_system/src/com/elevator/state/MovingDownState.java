package com.elevator.state;

import com.elevator.model.Elevator;

public class MovingDownState implements ElevatorState {

    @Override
    public void handle(Elevator elevator) {
        int target = elevator.pollNextRequest();

        while (elevator.getCurrentFloor() > target) {
            elevator.decrementFloor();
            elevator.notifyObservers("Moving DOWN to " + elevator.getCurrentFloor());
        }

        elevator.setState(new DoorOpenState());
    }
}
