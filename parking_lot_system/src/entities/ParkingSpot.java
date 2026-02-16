import java.util.concurrent.locks.ReentrantLock;

public class ParkingSpot {
    private final String id;
    private final SpotType spotType;
    private Vehicle currentVehicle;
    private final ReentrantLock lock = new ReentrantLock();

    public ParkingSpot(String id, SpotType spotType) {
        this.id = id;
        this.spotType = spotType;
    }

    public boolean assignVehicle(Vehicle vehicle) {
        lock.lock();
        try {
            if (currentVehicle != null) return false;
            if (!canFit(vehicle)) return false;
            currentVehicle = vehicle;
            return true;
        } finally {
            lock.unlock();
        }
    }

    public void removeVehicle() {
        lock.lock();
        try {
            currentVehicle = null;
        } finally {
            lock.unlock();
        }
    }

    public boolean isAvailable() {
        return currentVehicle == null;
    }

    private boolean canFit(Vehicle vehicle) {
        switch (vehicle.getType()) {
            case MOTORCYCLE:
                return true;
            case CAR:
                return spotType == SpotType.COMPACT || spotType == SpotType.LARGE;
            case TRUCK:
                return spotType == SpotType.LARGE;
            default:
                return false;
        }
    }

    public String getId() {
        return id;
    }
}