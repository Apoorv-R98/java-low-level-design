
package paymentsystem.exceptions;
public class IdempotencyException extends RuntimeException{
    public IdempotencyException(String msg){super(msg);}
}
