import java.util.ArrayList;
import java.util.List;

public class ParkingLevel {
    private final int levelNumber;
    private final List<ParkingSpot> spots = new ArrayList<>();

    public ParkingLevel(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    public void addSpot(ParkingSpot spot) {
        spots.add(spot);
    }

    public List<ParkingSpot> getSpots() {
        return spots;
    }
}