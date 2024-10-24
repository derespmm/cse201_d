/**
 * Class: Customer
 * @author: Matt DeRespinis
 * @version: 1.0
 * Course: CSE 201, Section D, Fall 2024
 * Written: 10/24/2024 
 * 
 * This class contains the name and address information of a customer.
 */
public class Customer {
    String name;
    String addressInfo;

    public Customer(String name, String addressInfo) {
        this.name = name;
        this.addressInfo = addressInfo;
    }

    /**
     * Changes the address information associated with this customer.
     * 
     * This method changes the customer's address information to the given String.
     * It returns true if the change was successful, false otherwise.
     * 
     * @param newAddressInfo The new address information to be associated with this customer.
     * @return True if the change was successful, false otherwise.
     */
    public boolean changeAddressInfo(String newAddressInfo) {
        this.addressInfo = newAddressInfo;
        if (newAddressInfo == this.addressInfo) {
            return true;
        }
        return false;
    }

    /**
     * Retrieves the name of the customer.
     * 
     * This method returns the name associated with this customer.
     * 
     * @return The name associated with this customer.
     */
    public String getName() {
        return name;
    }
}
