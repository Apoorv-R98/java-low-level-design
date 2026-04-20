package com.elevator.model;

import com.elevator.exception.InvalidRequestException;

public class Request {
    private final int source;
    private final int destination;
    private final Direction direction;

    public Request(int source, int destination) {
        if (source == destination) {
            throw new InvalidRequestException("Source and destination cannot be same");
        }
        this.source = source;
        this.destination = destination;
        this.direction = (destination > source) ? Direction.UP : Direction.DOWN;
    }

    public int getSource() {
        return source;
    }

    public int getDestination() {
        return destination;
    }

    public Direction getDirection() {
        return direction;
    }
}
