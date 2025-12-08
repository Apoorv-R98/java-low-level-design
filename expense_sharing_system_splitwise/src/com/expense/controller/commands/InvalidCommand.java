package com.expense.controller.commands;

public class InvalidCommand implements Command {

    private final String message;

    public InvalidCommand(String message) {
        this.message = message;
    }

    @Override
    public void execute() {
        System.out.println("Error: " + message);
    }
}
