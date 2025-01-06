package lmsystem.model;
import java.util.Date;

public class Fine {
    private double amount;
    private Date dueDate;
    private User user; 

    public Fine(double amount, Date dueDate, User user) {
        this.amount = amount;
        this.dueDate = dueDate;
        this.user = user;
    }

    public double getAmount() { return amount; }
    public Date getDueDate() { return dueDate; }
    public User getUser () { return user; } 
}