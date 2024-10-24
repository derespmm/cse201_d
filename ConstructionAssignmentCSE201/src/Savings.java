/**
 * Class: Savings
 * @author: Matt DeRespinis
 * @version: 1.0
 * Course: CSE 201, Section D, Fall 2024
 * Written: 10/24/2024 
 * 
 * This class represents a savings account. It extends the Account class and
 * contains the monthly interest rate.
 */
public class Savings extends Account {
    double monthylyInterestRate;

    public Savings(Customer owner, double balance, double monthylyInterestRate) {
        super(owner, balance);
        this.monthylyInterestRate = monthylyInterestRate;
    }

    /**
     * Deposit the given amount into the savings account.
     * 
     * This method adds the given amount to the balance of the savings account.
     * It then returns the new balance of the account.
     * 
     * @param amount The amount of money to deposit into the savings account.
     * @return The new balance of the savings account after the deposit.
     */
    public double deposit(double amount) {
        balance += amount;
        return balance;
    }

    /**
     * Withdraws the specified amount from the savings account if sufficient balance exists.
     * 
     * This method checks if the given amount is less than or equal to the current balance.
     * If so, it subtracts the amount from the balance and returns true. If the balance is
     * insufficient, no money is withdrawn and the method returns false.
     * 
     * @param amount The amount of money to withdraw from the savings account.
     * @return True if the withdrawal was successful, false if there was insufficient balance.
     */
    public boolean withdraw(double amount) {
        if (amount <= balance) { 
            balance -= amount;
            return true;
        }
        return false;
    }

    /**
     * Adds the monthly interest rate to the balance of the savings account.
     * 
     * This method multiplies the current balance of the savings account by the
     * monthly interest rate and adds the result to the balance. The new balance
     * is then returned.
     * 
     * @return The new balance of the savings account after adding interest.
     */
    public double addInterest() {
        balance += balance * monthylyInterestRate;
        return balance;
    }
}
