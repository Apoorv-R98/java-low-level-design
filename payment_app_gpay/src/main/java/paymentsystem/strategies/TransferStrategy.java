
package paymentsystem.strategies;
import paymentsystem.domain.Payment;
public interface TransferStrategy {
    void executeTransfer(Payment p) throws Exception;
}
