import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class: BankApplication
 * @author: Matt DeRespinis
 * @version: 1.0
 * Course: CSE 201, Section D, Fall 2024
 * Written: 10/24/2024 
 * 
 * This class is the main class for the Bank Application. It creates a Bank
 * object and a Scanner object, and then enters a loop where it prints the
 * menu, gets the user's input, processes the input, and then repeats. The
 * loop continues until the user chooses to quit, at which point the Scanner
 * object is closed and the program exits.
 */
public class BankApplication {
    
    /**
     * Main method for the BankApplication class. This is the entry point
     * for the program. It creates a Bank object and a Scanner object, and
     * then enters a loop where it prints the menu, gets the user's input,
     * processes the input, and then repeats. The loop continues until
     * the user chooses to quit, at which point the Scanner object is
     * closed and the program exits.
     */
    public static void main(String[] args) {
        Bank bank = new Bank();
        Scanner scanner = new Scanner(System.in);
        char userInput;
        Customer customer = null;

        bank.getCustomers();
        bank.getAccounts();

        customer = getOrCreateCustomer(bank, scanner);

        do {
            printMenu();
            userInput = Character.toUpperCase(scanner.nextLine().charAt(0));
            customer = processMenuInput(userInput, customer, bank, scanner);
        } while (userInput != 'Q');

        scanner.close();
    }

    /**
     * Retrieves an existing customer by name or prompts the user to create a new customer if not found.
     * 
     * This method continuously asks the user to enter a customer name. If the customer is found
     * in the bank's customer list, it returns that customer. If not, it prompts the user to 
     * create a new customer with the given name. The process repeats until a valid customer 
     * is obtained.
     * 
     * @param bank The bank object containing the list of customers.
     * @param scanner The scanner object used to read user input.
     * @return The existing or newly created Customer object.
     */
    private static Customer getOrCreateCustomer(Bank bank, Scanner scanner) {
        String customerName;
        Customer customer = null;
        
        do {
            System.out.println("Enter customer name: ");
            customerName = scanner.nextLine().trim();
            
            if (bank.getCustomer(customerName) == null) {
                System.out.println("This customer is not in the customer list.");
                System.out.println("Would you like to create a new customer? (Y/N)");
                char userInput = Character.toUpperCase(scanner.nextLine().charAt(0));
                
                if (userInput == 'Y') {
                    customer = createNewCustomer(bank, scanner, customerName);
                } else {
                    System.out.println("No customer created.");
                }
            } else {
                customer = bank.getCustomer(customerName);
            }
        } while (customer == null);
        
        return customer;
    }
    
    /**
     * Creates a new customer in the bank's customer list.
     * 
     * This method prompts the user to enter a customer address and then creates a new
     * customer with the given name and address in the bank's customer list. It returns
     * the newly created customer object.
     * 
     * @param bank The bank object containing the list of customers.
     * @param scanner The scanner object used to read user input.
     * @param customerName The name of the customer to be created.
     * @return The newly created Customer object.
     */
    private static Customer createNewCustomer(Bank bank, Scanner scanner, String customerName) {
        System.out.println("Enter customer address: ");
        String address = scanner.nextLine().trim();
        bank.createNewCustomer(customerName, address);
        System.out.println("New customer " + customerName + " created.");
        return bank.getCustomer(customerName);
    }
    

    /**
     * Prints the main menu to the console.
     * 
     * This method prints out the options for the user to choose from, including
     * opening a new savings or loan account, managing an existing account, switching
     * to a different customer, writing the current state of the bank to storage, or
     * quitting the program.
     */
    private static void printMenu() {
        System.out.println("S) Open new savings account");
        System.out.println("L) Open new loan account");
        System.out.println("M) Manage existing accounts");
        System.out.println("T) Switch current customer");
        System.out.println("W) Write to storage");
        System.out.println("Q) Quit");
    }

    /**
     * Processes user input from the main menu and performs the corresponding action.
     * 
     * This method takes a character input from the user and executes the appropriate
     * action, such as creating a new savings or loan account, managing existing accounts,
     * switching to a different customer, writing data to storage, or quitting the program.
     * It returns the current customer after processing the input.
     * 
     * @param userInput The character input from the user representing their menu choice.
     * @param customer The current customer interacting with the bank.
     * @param bank The bank instance containing customer and account information.
     * @param scanner The scanner object used for reading user input.
     * @return The updated Customer object after processing the user's input.
     */
    private static Customer processMenuInput(char userInput, Customer customer, Bank bank, Scanner scanner) {
        switch (userInput) {
            case 'S':
                createSavings(customer, bank, scanner);
                break;
            case 'L':
                createLoan(customer, bank, scanner);
                break;
            case 'M':
                manageAccounts(customer, bank, scanner);
                break;
            case 'T':
                customer = getOrCreateCustomer(bank, scanner);
                break;
            case 'W':
                bank.writeCustomers();
                bank.writeAccounts();
                break;
            case 'Q':
                System.out.println("Exiting...");
                bank.writeCustomers();
                bank.writeAccounts();
                break;
            default:
                System.out.println("Invalid input. Please try again.");
                break;
        }
        return customer;
    }

    /**
     * Creates a new loan account for the specified customer.
     * 
     * This method prompts the user to enter the amount of money they wish to borrow
     * and uses that amount as the initial balance for the new loan account. The loan
     * is then opened in the bank system and associated with the specified customer.
     * 
     * @param customer The customer taking out the loan.
     * @param bank The bank instance where the loan account will be created.
     * @param scanner The scanner object used to read user input.
     */
    private static void createLoan(Customer customer, Bank bank, Scanner scanner) {
        System.out.println("How much money do you want to take out? ");
        double initialBalance = Double.parseDouble(scanner.nextLine());
        bank.openNewLoan(bank.getCustomer(customer.getName()), initialBalance);
    }

/**
 * Creates a new savings account for the specified customer.
 * 
 * This method prompts the user to enter an initial balance and then
 * creates a new savings account with that balance in the bank system,
 * associating it with the specified customer.
 * 
 * @param customer The customer for whom the savings account is created.
 * @param bank The bank instance where the savings account will be opened.
 * @param scanner The scanner object used to read user input.
 */
    private static void createSavings(Customer customer, Bank bank, Scanner scanner) {
        System.out.println("Enter initial balance: ");
        double initialBalance = Double.parseDouble(scanner.nextLine());
        bank.openNewSavingsAccount(bank.getCustomer(customer.getName()), initialBalance);
    }

    /**
     * Allows the user to manage all of the accounts associated with the specified
     * customer. This method first prints out a list of all of the accounts associated
     * with the customer, including the type of account and the current balance. The
     * user is then prompted to enter the number of the account they want to open or
     * type 0 to return to the main menu. If a valid account number is entered, the
     * user is then presented with options to deposit/withdraw money, get the current
     * balance, or return to the main menu. If an invalid account number is entered,
     * the user is notified and given the option to try again.
     * 
     * @param customer The customer whose accounts the user wants to manage.
     * @param bank The bank object where the accounts are stored.
     * @param scanner The scanner object used to read user input.
     */
    private static void manageAccounts(Customer customer, Bank bank, Scanner scanner) {
        ArrayList<Account> currentCustomerAccountList = bank.getAccount(customer.getName());

        if (currentCustomerAccountList.isEmpty()) {
            System.out.println(customer.getName() + " has no accounts.");
        } else {
            System.out.println(customer.getName() + " has " + currentCustomerAccountList.size() + " accounts.");
            int i = 1;
            for (Account account : currentCustomerAccountList) {
                if (account instanceof Savings) {
                    System.out.println(i + ") Savings, balance: " + account.getBalance());
                } else if (account instanceof Loan) {
                    System.out.println(i + ") Loan, balance: " + account.getBalance());
                } else {
                    System.out.println("Unknown account type.");
                }
                i++;
            }
            System.out.println("Enter the number of the account you want to open, or type 0 to return to the menu: ");
            try {
                int userAccountNumber = Integer.parseInt(scanner.nextLine()) - 1;
                if (userAccountNumber >= 0 && userAccountNumber < currentCustomerAccountList.size()) {
                    Account currentAccount = currentCustomerAccountList.get(userAccountNumber);
                    manageAccount(currentAccount, bank, scanner);
                } else if (userAccountNumber == -1) {
                    System.out.println("Returning to main menu...");
                } else {
                    System.out.println("Invalid account number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    /**
     * Manages a specific account for the customer.
     * 
     * This method determines the type of the account (Savings or Loan)
     * and displays the appropriate menu options for that account type.
     * It processes user input to perform actions such as withdrawing,
     * depositing, making payments, or checking balance.
     * 
     * @param currentAccount The account to be managed, either Savings or Loan.
     * @param bank The bank instance containing customer and account information.
     * @param scanner The scanner object used for reading user input.
     */
    private static void manageAccount(Account currentAccount, Bank bank, Scanner scanner) {
        char userInput;
        if (currentAccount instanceof Savings) {
            Savings currentSavingsAccount = (Savings) currentAccount;
            printSavingsMenu();
            userInput = Character.toUpperCase(scanner.nextLine().charAt(0));
            processSavingsMenu(userInput, currentSavingsAccount, scanner);
        } else {
            printLoanMenu();
            Loan currentLoanAccount = (Loan) currentAccount;
            userInput = Character.toUpperCase(scanner.nextLine().charAt(0));
            processLoanMenu(userInput, currentLoanAccount, scanner);
        }
    }

    /**
     * Prints the savings account menu to the console.
     * 
     * This method displays the menu options for managing a savings account,
     * including withdrawing, depositing, getting the balance, and returning to
     * the main menu.
     */
    private static void printSavingsMenu() {
        System.out.println("W) Withdraw");
        System.out.println("D) Deposit");
        System.out.println("B) Get balance");
        System.out.println("R) Return to main menu");
    }

    /**
     * Processes user input from the loan account menu and performs the corresponding action.
     * 
     * This method takes a character input from the user, determines which action to take
     * based on the input, and then performs that action. Actions include making a payment
     * on the loan, checking the balance of the loan, or returning to the main menu.
     * It provides feedback to the user based on the action taken.
     * 
     * @param userInput The character input from the user representing their menu choice.
     * @param currentAccount The current loan account being managed.
     * @param scanner The scanner object used for reading user input.
     */
    private static void processLoanMenu(char userInput, Loan currentAccount, Scanner scanner) {
        switch (userInput) {
            case 'M':
                System.out.println("Enter payment amount: ");
                currentAccount.makePayment(Double.parseDouble(scanner.nextLine()));
                break;
            case 'B':
                System.out.println("Balance: " + currentAccount.getBalance());
                break;
            case 'R':
                System.out.println("Returning to main menu...");
                break;
            default:
                System.out.println("Invalid input. Please try again.");
                break;
        }
    }

    /**
     * Prints the menu for managing a loan account.
     * 
     * This method prints out options for the user to manage their loan account,
     * including making a payment, getting the balance, or returning to the main menu.
     */
    private static void printLoanMenu() {
        System.out.println("M) Make payment");
        System.out.println("B) Get balance");
        System.out.println("R) Return to main menu");
    }

    /**
     * Processes user input from the savings account menu and performs the corresponding action.
     * 
     * This method takes a character input from the user, determines which action to take
     * based on the input, and then performs that action. Actions include withdrawing from
     * the account, depositing into the account, getting the balance of the account, or
     * returning to the main menu. It provides feedback to the user based on the action taken.
     * 
     * @param userInput The character input from the user representing their menu choice.
     * @param currentAccount The current savings account being managed.
     * @param scanner The scanner object used for reading user input.
     */
    private static void processSavingsMenu(char userInput, Savings currentAccount, Scanner scanner) {
        switch (userInput) {
            case 'W':
                System.out.println("Enter withdrawal amount: ");
                currentAccount.withdraw(Double.parseDouble(scanner.nextLine()));
                break;
            case 'D':
                System.out.println("Enter deposit amount: ");
                currentAccount.deposit(Double.parseDouble(scanner.nextLine()));
                break;
            case 'B':
                System.out.println("Balance: " + currentAccount.getBalance());
                break;
            case 'R':
                System.out.println("Returning to main menu...");
                break;
            default:
                System.out.println("Invalid input. Please try again.");
                break;
        }
    }
}

    


    
