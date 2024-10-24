import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Class: Bank
 * @author: Matt DeRespinis
 * @version: 1.0
 * Course: CSE 201, Section D, Fall 2024
 * Written: 10/24/2024 
 * 
 * This class is the main class for the Bank. It contains functions
 * that allow for the manipulation and creation of bank accounts and
 * customers. The Bank object is created in the main method.
 */
public class Bank {
    private ArrayList<Account> accountList;
    private ArrayList<Customer> customerList;

    public Bank() {
        this.accountList = new ArrayList<Account>();
        this.customerList = new ArrayList<Customer>();
    }

    public Bank(ArrayList<Account> accountList, ArrayList<Customer> customerList) {
        this.accountList = accountList;
        this.customerList = customerList;
    }

    /**
     * Retrieves a list of all accounts in the bank from a file.
     * 
     * This method reads from a file named "accounts.txt" and creates an
     * ArrayList of Account objects from the data it reads. Each line of the
     * file should contain a customer name, an account type, and a balance.
     * The method then adds each Account object to the ArrayList and returns
     * it.
     * 
     * @return The ArrayList of Account objects read from the file.
     */
    public ArrayList<Account> getAccounts() {
        String filename = "accounts.txt";
        try (Scanner scanner = new Scanner(new File(filename))) {
            while(scanner.hasNextLine()) {
                String name = scanner.nextLine();
                String type = scanner.nextLine();
                double balance = Double.parseDouble(scanner.nextLine());
                Customer accountHolder = null;
                for (Customer c : customerList) {
                    if (c.name.equals(name)) {
                        accountHolder = c;
                        break;
                    }
                }
                if (accountHolder != null) {
                    if (type.equals("Savings")) {
                        accountList.add(new Savings(accountHolder, balance, 100));
                    } else if (type.equals("Loan")) {
                        accountList.add(new Loan(accountHolder, balance, 100));
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        }
        return accountList;
    }
    

    /**
     * Retrieves a list of all customers in the bank from a file.
     * 
     * This method reads from a file named "customers.txt" and creates an
     * ArrayList of Customer objects from the data it reads. Each line of the
     * file should contain a customer name and address.
     * The method then adds each Customer object to the ArrayList and returns
     * it.
     * 
     * @return The ArrayList of Customer objects read from the file.
     */
    public ArrayList<Customer> getCustomers() {
        String filename = "customers.txt";
        try (Scanner scanner = new Scanner(new File(filename))) {
            while(scanner.hasNextLine()) {
                String name = scanner.nextLine();
                String address = scanner.nextLine();
                customerList.add(new Customer(name, address));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        }
        return customerList;
    }

    /**
     * Writes the list of accounts to a file named "accounts.txt".
     * 
     * This method writes out the customer name, account type, and balance for
     * each account in the bank's account list to the file. If the file is not
     * found, or there is an error writing to the file, the method returns false.
     * If the operation is successful, the method returns true.
     * 
     * @return True if the operation was successful, false otherwise.
     */
    public boolean writeAccounts() {
        String filename = "accounts.txt";
        try (FileWriter fw = new FileWriter(filename)) {
            for (Account account : accountList) {
                if (account instanceof Savings) {
                    fw.write(account.owner.name + "\nSavings\n" + (double) account.getBalance() + "\n");
                } else if (account instanceof Loan) {
                    fw.write(account.owner.name + "\nLoan\n" + (double) account.getBalance() + "\n");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
            return false;
        } catch (IOException e) {
            System.out.println("Error writing to file: " + filename);
            return false;
        }
        System.out.println("Accounts successfully written to file: " + filename);
        return true;
    }

    /**
     * Writes the list of customers to a file named "customers.txt".
     * 
     * This method writes out the customer name and address information for
     * each customer in the bank's customer list to the file. If the file is not
     * found, or there is an error writing to the file, the method returns false.
     * If the operation is successful, the method returns true.
     * 
     * @return True if the operation was successful, false otherwise.
     */
    public boolean writeCustomers() {
        String filename = "customers.txt";
        try (FileWriter fw = new FileWriter(filename)) {
            for (Customer customer : customerList) {
                fw.write(customer.name + "\n" + customer.addressInfo + "\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
            return false;
        } catch (IOException e) {
            System.out.println("Error writing to file: " + filename);
            return false;
        }
        System.out.println("Customers successfully written to file: " + filename);
        return true;
    }

    /**
     * Opens a new savings account for the given customer with the given
     * balance.
     * 
     * This method creates a new Savings account with the given balance and
     * associates it with the given customer. The account is then added to the
     * bank's account list.
     * 
     * @param customer The customer for whom the savings account is to be opened.
     * @param balance The initial balance of the savings account.
     * @return The newly created Savings account object.
     */
    public Account openNewSavingsAccount(Customer customer, double balance) {
        Savings s = new Savings(customer, balance, 100);
        accountList.add(s);
        return s;
    }

    /**
     * Opens a new loan account for the given customer with the given
     * balance.
     * 
     * This method creates a new Loan account with the given balance and
     * associates it with the given customer. The account is then added to the
     * bank's account list.
     * 
     * @param customer The customer for whom the loan account is to be opened.
     * @param balance The initial balance of the loan account.
     * @return The newly created Loan account object.
     */
    public Account openNewLoan(Customer customer, double balance) {
        Loan l = new Loan(customer, balance, 100);
        accountList.add(l);
        return l;
    }

    /**
     * Creates a new customer in the bank's customer list.
     * 
     * This method creates a new Customer object with the given name and address
     * and adds it to the bank's customer list. The method returns the newly
     * created Customer object.
     * 
     * @param name The name of the customer to be created.
     * @param address The address of the customer to be created.
     * @return The newly created Customer object.
     */
    public Customer createNewCustomer(String name, String address) {
        Customer newCustomer = new Customer(name, address);
        customerList.add(newCustomer);
        return newCustomer;
    }

    /**
     * Retrieves a list of all accounts associated with the customer with the
     * given name.
     * 
     * This method loops through all accounts in the bank and adds any accounts
     * associated with the given customer name to a new ArrayList. The method
     * then returns this ArrayList.
     * 
     * @param customerName The name of the customer for which accounts are to
     *        be retrieved.
     * @return An ArrayList of Account objects associated with the given
     *         customer name.
     */
    public ArrayList<Account> getAccount(String customerName) {
        ArrayList<Account> tmpAccounts = new ArrayList<>();
        for (Account a : accountList) {
            if (a.owner.name.equals(customerName)) {
                tmpAccounts.add(a);
            }
        }
        return tmpAccounts;
    }

    /**
     * Retrieves a customer from the bank's customer list by name.
     * 
     * This method loops through all customers in the bank and returns the
     * customer with the given name if found. If not found, the method returns
     * null.
     * 
     * @param name The name of the customer to be retrieved.
     * @return The customer with the given name if found, null otherwise.
     */
    public Customer getCustomer(String name) {
        for (Customer c : customerList) {
            if (c.name.equals(name)) {
                return c;
            }
        }
        return null;
    }
}
