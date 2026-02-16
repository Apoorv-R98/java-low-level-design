package src.strategy;

import src.entities.Ticket;

public interface PricingStrategy {
    double calculate(Ticket ticket);
}