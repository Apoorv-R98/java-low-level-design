
package paymentsystem.domain;
public class User {
    private final String userId;
    private final String phone;
    public User(String userId, String phone) { this.userId=userId; this.phone=phone; }
    public String getUserId(){return userId;} 
    public String getPhone(){return phone;}
}
