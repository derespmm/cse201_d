/**
 * Class: Loan
 * @author: Matt DeRespinis
 * @version: 1.0
 * Course: CSE 201, Section D, Fall 2024
 * Written: 10/24/2024 
 * 
 * This class represents a loan account. It extends the Account class and
 * contains the monthly interest rate.
 */
public class Loan extends Account {
    double monthylyInterest;

    public Loan(Customer owner, double balance, double monthylyInterest) {
        super(owner, balance);
        this.monthylyInterest = monthylyInterest;
    }

    /**
     * Makes a payment of the given amount on the loan account.
     * 
     * This method subtracts the given amount from the balance of the loan account
     * and then returns the new balance.
     * 
     * @param amount The amount to pay on the loan.
     * @return The new balance of the loan account after payment.
     */
    public double makePayment(double amount) {
        balance -= amount;
        return balance;
    }

    /**
     * Adds the monthly interest rate to the balance of the loan account.
     * 
     * This method multiplies the current balance of the loan account by the
     * monthly interest rate and adds the result to the balance. The new balance
     * is then returned.
     * 
     * @return The new balance of the loan account after adding interest.
     */
    public double addInterest() {
        balance = balance * monthylyInterest;
        return balance;
    }

    /**
     * Determines whether the loan account has been paid off.
     * 
     * This method is a placeholder for future functionality and currently
     * returns false.
     * 
     * @return False, indicating that the loan account has not been paid off.
     */
    public boolean loanPaidOff() {
        // placeholder method for future functionality
        return false;
    }
}
