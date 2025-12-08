
package paymentsystem.validation;
import paymentsystem.domain.PaymentRequest;
import java.util.*;
public class ValidationChain {
    private final List<Validator> validators=new ArrayList<>();
    public ValidationChain add(Validator v){validators.add(v); return this;}
    public void execute(PaymentRequest r){ for(Validator v:validators) v.validate(r); }
}
