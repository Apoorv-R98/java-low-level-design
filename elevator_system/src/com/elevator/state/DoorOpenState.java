package com.elevator.state;

import com.elevator.model.Elevator;

public class DoorOpenState implements ElevatorState {

    @Override
    public void handle(Elevator elevator) {
        elevator.notifyObservers("Door opened at floor " + elevator.getCurrentFloor());
        elevator.reduceLoad();
        elevator.setState(new IdleState());
    }
}
