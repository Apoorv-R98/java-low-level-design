
package paymentsystem;
import paymentsystem.domain.*;
import paymentsystem.db.InMemoryDB;
import paymentsystem.processor.*;
import paymentsystem.strategies.*;
public class PaymentAppSystem {
    public static void main(String[]args){

        User u1=new User("U1","+9111"); User u2=new User("U2","+9222"); User merchant=new User("M1","+9333");
        InMemoryDB.users.put("U1",u1); InMemoryDB.users.put("U2",u2); InMemoryDB.users.put("M1",merchant);
        Account a1=new Account("A1","U1",100000); Account a2=new Account("A2","U2",20000); Account m=new Account("M1_AC","M1",50000);
        InMemoryDB.accounts.put("A1",a1); InMemoryDB.accounts.put("A2",a2); InMemoryDB.accounts.put("M1_AC",m);

        TransferStrategy upi=new ExternalUPITransferStrategy();
        TransferStrategy qr=new QrMerchantTransferStrategy();
        TransferStrategy merchantStrat=new MerchantTransferStrategy();


        P2PPaymentProcessor p2p=new P2PPaymentProcessor(upi);
        QrPaymentProcessor qrP=new QrPaymentProcessor(qr);
        MerchantPaymentProcessor mp=new MerchantPaymentProcessor(merchantStrat);

        System.out.println("Before balances: "+InMemoryDB.accounts);

        PaymentResult r1=p2p.process(new PaymentRequest("A1","A2",30000,"K-P2P-1"));
        System.out.println("P2P result: "+r1);

        PaymentResult r2=qrP.process(new PaymentRequest("A1","M1_AC",10000,"K-QR-1"));
        System.out.println("QR result: "+r2);

        PaymentResult r3=mp.process(new PaymentRequest("A2","M1_AC",5000,"K-M-1"));
        System.out.println("Merchant result: "+r3);

        System.out.println("After balances: "+InMemoryDB.accounts);
    }
}
