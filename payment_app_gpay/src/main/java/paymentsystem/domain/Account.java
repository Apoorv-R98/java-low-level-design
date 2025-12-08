
package paymentsystem.domain;
public class Account {
    private final String accountId;
    private final String userId;
    private long balance;
    public Account(String accountId,String userId,long balance){
        this.accountId=accountId;this.userId=userId;this.balance=balance;
    }
    public String getAccountId(){return accountId;}
    public String getUserId(){return userId;}
    public long getBalance(){return balance;}
    public void setBalance(long b){balance=b;}
    public String toString(){return "Account("+accountId+",bal="+balance+")";}
}
