
package paymentsystem.validation;
import paymentsystem.domain.PaymentRequest;
import paymentsystem.exceptions.ValidationException;
public class AmountValidator implements Validator {
    public void validate(PaymentRequest r){
        if(r.getAmount()<=0) throw new ValidationException("Amount must be >0");
    }
}
