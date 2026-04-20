package com.elevator.state;

import com.elevator.model.Elevator;

public class IdleState implements ElevatorState {

    @Override
    public void handle(Elevator elevator) {
        if (!elevator.hasRequests()) {
            return;
        }

        int next = elevator.peekNextRequest();

        if (next > elevator.getCurrentFloor()) {
            elevator.setState(new MovingUpState());
        } else {
            elevator.setState(new MovingDownState());
        }
    }
}
