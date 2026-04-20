package com.elevator.state;

import com.elevator.model.Elevator;

public interface ElevatorState {
    void handle(Elevator elevator);
}
