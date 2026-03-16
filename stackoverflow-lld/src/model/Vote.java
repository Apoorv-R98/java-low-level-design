
package model;

public class Vote {

    private User voter;
    private int value;

    public Vote(User voter, int value) {
        this.voter = voter;
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
