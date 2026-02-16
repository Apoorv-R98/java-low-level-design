package src;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import src.Exception.InvalidTicketException;
import src.Exception.ParkingFullException;
import src.entities.ParkingLevel;
import src.entities.ParkingSpot;
import src.entities.Ticket;
import src.entities.Vehicle;
import src.strategy.PricingStrategy;
import src.strategy.SpotAllocationStrategy;

public class ParkingLot {
    private final List<ParkingLevel> levels;
    private final SpotAllocationStrategy allocationStrategy;
    private final PricingStrategy pricingStrategy;
    private final Map<String, Ticket> activeTickets = new ConcurrentHashMap<>();

    public ParkingLot(List<ParkingLevel> levels,
                      SpotAllocationStrategy allocationStrategy,
                      PricingStrategy pricingStrategy) {
        this.levels = levels;
        this.allocationStrategy = allocationStrategy;
        this.pricingStrategy = pricingStrategy;
    }

    public Ticket parkVehicle(Vehicle vehicle) {
        ParkingSpot spot = allocationStrategy.findSpot(vehicle, levels);
        if (spot == null) throw new ParkingFullException();
        Ticket ticket = new Ticket(vehicle, spot);
        activeTickets.put(ticket.getId(), ticket);
        return ticket;
    }

    public double unparkVehicle(String ticketId) {
        Ticket ticket = activeTickets.remove(ticketId);
        if (ticket == null) throw new InvalidTicketException();
        ticket.markExit();
        ticket.getSpot().removeVehicle();
        return pricingStrategy.calculate(ticket);
    }
}