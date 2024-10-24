/**
 * Class: Account
 * @author: Matt DeRespinis
 * @version: 1.0
 * Course: CSE 201, Section D, Fall 2024
 * Written: 10/24/2024 
 * 
 * This class contains the owner and balance information of an account.
 */
public class Account {
    Customer owner;
    double balance;

    public Account(Customer owner, double balance) {
        this.owner = owner;
        this.balance = balance;
    }

    /**
     * Returns the balance of the account.
     * 
     * @return The current balance of the account.
     */
    public double getBalance() {
        return balance;
    }
}
