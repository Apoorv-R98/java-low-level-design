package com.elevator;

import com.elevator.controller.ElevatorController;
import com.elevator.exception.ElevatorException;
import com.elevator.model.Elevator;
import com.elevator.model.Request;
import com.elevator.observer.FloorDisplay;

public class Main {

    public static void main(String[] args) {

        ElevatorController controller = ElevatorController.getInstance(1);

        Elevator elevator = controller.getElevators().get(0);

        for (int i = 0; i <= 5; i++) {
            elevator.addObserver(new FloorDisplay(i));
        }

        try {
            controller.requestElevator(new Request(0, 4));
            controller.requestElevator(new Request(2, 1));

            for (int i = 0; i < 10; i++) {
                controller.step();
            }

        } catch (ElevatorException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
