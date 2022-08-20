/*
Factory Pattern - Used to Instantiate Customers
*/
public class CustomerFactory {
    // Create new customer object based on the specified customer type
    public Customer createCustomer(String customerType) {
        if (customerType.equals("Casual")) {
            return new CasualCustomer();
        } else if (customerType.equals("Business")) {
            return new BusinessCustomer();
        } else if (customerType.equals("Catering")) {
            return new CateringCustomer();
        }
        return null;
    }
}
