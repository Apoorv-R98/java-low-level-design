package src.strategy;
import java.util.List;

import src.entities.ParkingLevel;
import src.entities.ParkingSpot;
import src.entities.Vehicle;

public interface SpotAllocationStrategy {
    ParkingSpot findSpot(Vehicle vehicle, List<ParkingLevel> levels);
}