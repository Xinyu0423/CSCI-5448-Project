import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Store {
    // List of Roll Types and Customer Types
    public static String[] rollTypes = {"Egg Roll", "Jelly Roll", "Pastry Roll", "Sausage Roll", "Spring Roll"};
    public static String[] customerTypes = {"Business", "Casual", "Catering"};

    public static void main(String[] args) {
        // Inventory restock amount
        int inventoryStockCount = 30;
        // Number of days to run the store
        int days = 30;
        Random random = new Random();

        // Initialize a Store Inventory
        StoreInventory inventory = new StoreInventory(rollTypes, inventoryStockCount);

        // Initialize a Store Sales Manager and set their inventory
        StoreSalesManager salesManager = new StoreSalesManager(rollTypes);
        salesManager.setInventory(inventory);

        // Store Sales Tracker keeps track of roll sales and costs (prints most of the daily/overall statistics)
        StoreSalesTracker salesTracker = new StoreSalesTracker(rollTypes, customerTypes);

        /*
        Observer Pattern - StoreSalesTracker observes StoreSalesManager and responds to emitted events and property changes
         */
        salesManager.addPropertyChangeListener(salesTracker);

        // Initialize a Customer Factory and ArrayList of Customers
        CustomerFactory customerFactory = new CustomerFactory();
        ArrayList<Customer> customers;

        for (int i = 0; i < days; i++) {
            // Current day number
            int currentDay = i + 1;
            // Print the current day (with separators so its easier to see)
            System.out.println("\n\n-----------------");
            System.out.println("DAY NUMBER:\t" + currentDay);
            System.out.println("-----------------");
            // Set the current day in the Sales Manager object
            salesManager.setCurrentDay(currentDay);

            // Generate the specified number of each type of customer, add them to the customer list
            int businessCustomerCount = random.nextInt(3) + 1;
            int casualCustomerCount = random.nextInt(12) + 1;
            int cateringCustomerCount = random.nextInt(3) + 1;
            int totalCustomerCount = businessCustomerCount + casualCustomerCount + cateringCustomerCount;
            customers = new ArrayList<>();
            for (int j = 0; j < totalCustomerCount; j++) {
                if (j < businessCustomerCount) {
                    customers.add(customerFactory.createCustomer("Business"));
                } else if (j < businessCustomerCount + casualCustomerCount) {
                    customers.add(customerFactory.createCustomer("Casual"));
                } else {
                    customers.add(customerFactory.createCustomer("Catering"));
                }
            }

            // Shuffle customers in the list so that they arrive in a random order
            Collections.shuffle(customers);

            // Print the start of day roll inventory
            System.out.println("START OF DAY INVENTORY:");
            inventory.printRollInventory();

            // Take and print all customer orders while the store is full
            System.out.println("\nCUSTOMER ORDERS:");
            for (Customer customer : customers) {
                if (salesManager.isInventoryEmpty()) {
                    break;
                } else {
                    salesManager.customerTransaction(customer);
                }
            }

            // Print end of day inventory
            System.out.println("\nEND OF DAY INVENTORY:");
            inventory.printRollInventory();

            // Print all the specified daily statistics
            salesTracker.printDailyStatistics();

            // Restock any empty rolls after all orders are taken for the day
            inventory.restock();
        }

        // Print overall statistics at the end of the simulation
        System.out.println("\n\n======================");
        System.out.println("OVERALL FOR " + days + " DAYS:");
        System.out.println("======================");
        salesTracker.printFinalStatistics();

        // Values for Extra Credit Charts (Commented out unless values are needed)
        // System.out.println(salesTracker.getDailySales());
        // System.out.println(salesTracker.getDailyRollOutage());
    }
}
