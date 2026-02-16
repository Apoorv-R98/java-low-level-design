public class InvalidTicketException extends ParkingException {
    public InvalidTicketException() {
        super("Invalid ticket");
    }
}