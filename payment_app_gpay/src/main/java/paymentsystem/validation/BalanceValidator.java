
package paymentsystem.validation;
import paymentsystem.domain.*;
import paymentsystem.db.InMemoryDB;
import paymentsystem.exceptions.ValidationException;
import paymentsystem.domain.PaymentRequest;
public class BalanceValidator implements Validator {
    public void validate(PaymentRequest r){
        Account a=InMemoryDB.accounts.get(r.getFromAccount());
        if(a==null) throw new ValidationException("Source account missing");
        if(a.getBalance()<r.getAmount()) throw new ValidationException("Insufficient balance");
    }
}
