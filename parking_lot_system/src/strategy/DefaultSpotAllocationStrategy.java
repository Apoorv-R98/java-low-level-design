package src.strategy;
import java.util.List;

import src.entities.ParkingLevel;
import src.entities.ParkingSpot;
import src.entities.Vehicle;

public class DefaultSpotAllocationStrategy implements SpotAllocationStrategy {
    @Override
    public ParkingSpot findSpot(Vehicle vehicle, List<ParkingLevel> levels) {
        for (ParkingLevel level : levels) {
            for (ParkingSpot spot : level.getSpots()) {
                if (spot.isAvailable() && spot.assignVehicle(vehicle)) {
                    return spot;
                }
            }
        }
        return null;
    }
}