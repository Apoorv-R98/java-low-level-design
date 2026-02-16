import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ParkingLevel level1 = new ParkingLevel(1);
        level1.addSpot(new ParkingSpot("S1", SpotType.COMPACT));
        level1.addSpot(new ParkingSpot("S2", SpotType.LARGE));
        level1.addSpot(new ParkingSpot("S3", SpotType.MOTORCYCLE));

        List<ParkingLevel> levels = Arrays.asList(level1);

        ParkingLot parkingLot = new ParkingLot(
                levels,
                new DefaultSpotAllocationStrategy(),
                new HourlyPricingStrategy(20)
        );

        Vehicle car = new Vehicle("CAR123", VehicleType.CAR);
        Vehicle bike = new Vehicle("BIKE123", VehicleType.MOTORCYCLE);

        Ticket carTicket = parkingLot.parkVehicle(car);
        System.out.println("Car parked. Ticket: " + carTicket.getId());

        Ticket bikeTicket = parkingLot.parkVehicle(bike);
        System.out.println("Bike parked. Ticket: " + bikeTicket.getId());

        Thread.sleep(2000);

        double carAmount = parkingLot.unparkVehicle(carTicket.getId());
        System.out.println("Car unparked. Amount: " + carAmount);

        double bikeAmount = parkingLot.unparkVehicle(bikeTicket.getId());
        System.out.println("Bike unparked. Amount: " + bikeAmount);
    }
}