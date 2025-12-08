package com.expense.controller.commands;

public class ExitCommand implements Command {

    @Override
    public void execute() {
        System.out.println("Exiting...");
    }
}
