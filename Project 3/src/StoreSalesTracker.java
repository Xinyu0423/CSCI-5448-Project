import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class StoreSalesTracker implements PropertyChangeListener {
    // Round to 2 decimal places
    private static DecimalFormat df = new DecimalFormat("0.00");

    // Roll types and customer types, defined in constructor
    private String[] rollTypes;
    private String[] customerTypes;

    // Daily statistics
    private int day;
    private boolean storeClosed;
    private float currentOrderTotal;
    private String currentCustomerType;
    private int[] rollSales;
    private float[] customerOrderPayments;
    private int[] customerOutageImpacts;

    // Overall statistics
    private int totalDays;
    private int[] totalRollSales;
    private float totalSalesCost;
    private int totalOutageImpacts;

    // Keeping track of data for extra credit charts
    private ArrayList<Integer> dailySales;
    private ArrayList<Integer> dailyRollOutage;

    public StoreSalesTracker(String[] rolls, String[] customers) {
        rollTypes = rolls;
        customerTypes = customers;

        rollSales = new int[rollTypes.length];
        totalRollSales = new int[rollTypes.length];

        customerOrderPayments = new float[customerTypes.length];
        customerOutageImpacts = new int[customerTypes.length];

        dailySales = new ArrayList<>();
        dailyRollOutage = new ArrayList<>();
    }

    public ArrayList<Integer> getDailySales() {
        return dailySales;
    }

    public ArrayList<Integer> getDailyRollOutage() {
        return dailyRollOutage;
    }

    // New day, reset daily counts and update total counts
    private void newDay(int day) {
        totalDays += 1;

        this.day = day;
        storeClosed = false;

        int totalDailySales = 0;
        for (int i = 0; i < rollTypes.length; i++) {
            totalRollSales[i] += rollSales[i];
            totalDailySales += rollSales[i];
            rollSales[i] = 0;
        }

        dailySales.add(totalDailySales);

        int totalDailyOutages = 0;
        for (int i = 0; i < customerTypes.length; i++) {
            totalSalesCost += customerOrderPayments[i];
            customerOrderPayments[i] = 0;

            totalOutageImpacts += customerOutageImpacts[i];
            totalDailyOutages += customerOutageImpacts[i];
            customerOutageImpacts[i] = 0;
        }

        dailyRollOutage.add(totalDailyOutages);
    }

    // Increment the sale for the specified roll by one
    private void incrementRollSales(String rollName) {
        for (int i = 0; i < rollTypes.length; i++) {
            if (rollTypes[i].equals(rollName)) {
                rollSales[i] += 1;
            }
        }
    }

    // Add the total to the specified customer type
    private void incrementCustomerOrderPayments(String customerType, float total) {
        for (int i = 0; i < customerTypes.length; i++) {
            if (customerTypes[i].equals(customerType)) {
                customerOrderPayments[i] += total;
            }
        }
    }

    // Increment the total outage count by one for the specified customer type
    private void incrementCustomerOutageImpacts(String customerType) {
        for (int i = 0; i < customerTypes.length; i++) {
            if (customerTypes[i].equals(customerType)) {
                customerOutageImpacts[i] += 1;
            }
        }
    }

    // Print all daily statistics specified in the writeup
    public void printDailyStatistics() {
        System.out.println("\nTOTAL PAYMENTS:");
        double paymentSum = 0;
        for (int i = 0; i < customerTypes.length; i++) {
            paymentSum += customerOrderPayments[i];
            System.out.println("\t" + customerTypes[i] + ":\t" + df.format(customerOrderPayments[i]));
        }
        System.out.println("\tOVERALL:\t" + df.format(paymentSum));

        System.out.println("\nORDERS IMPACTED BY OUTAGES:");
        for (int i = 0; i < customerTypes.length; i++) {
            System.out.println("\t" + customerTypes[i] + ":\t" + customerOutageImpacts[i]);
        }

        System.out.println("\nINVENTORY ORDERS:");
        for (int i = 0; i < rollTypes.length; i++) {
            System.out.println("\t" + rollTypes[i] + ":\t" + rollSales[i]);
        }

        System.out.println("\nSTORE CLOSED TODAY:\t" + storeClosed);
    }

    // Print final (overall) statistics at the end of the simulation
    public void printFinalStatistics() {
        newDay(0);
        totalDays -= 1;

        System.out.println("TOTAL ROLLS SOLD:");
        int totalRolls = 0;
        for (int i = 0; i < rollTypes.length; i++) {
            totalRolls += totalRollSales[i];
            System.out.println("\t" + rollTypes[i] + ":\t" + totalRollSales[i]);
        }
        System.out.println("\tOVERALL:\t" + totalRolls);

        System.out.println("\nTOTAL SALES:\t" + df.format(totalSalesCost));
        System.out.println("\nTOTAL ROLL OUTAGE IMPACTS:\t" + totalOutageImpacts);
    }

    @Override
    // StoreSalesTracker is watching the StoreSalesManager, respond to emitted events here
    public void propertyChange(PropertyChangeEvent evt) {
        String propertyName = evt.getPropertyName();
        if (propertyName.equalsIgnoreCase("NewDay")) {
            // Reset all values when a new day happens
            newDay((int) evt.getNewValue());
        } else if (propertyName.equalsIgnoreCase("BeginCustomerOrder")) {
            // Begin a new customer order
            currentOrderTotal = 0;
            currentCustomerType = evt.getNewValue().toString();
            System.out.println("\nOrder for " + evt.getNewValue() + " Customer:");
        } else if (propertyName.equalsIgnoreCase("CustomerOrderItem")) {
            // Print an item name that a customer ordered
            incrementRollSales(evt.getOldValue().toString());
            System.out.println("\tItem:\t" + evt.getNewValue());
        } else if (propertyName.equalsIgnoreCase("CustomerOrderPrice")) {
            // Print the price of an item that a customer ordered
            currentOrderTotal += (double) evt.getNewValue();
            System.out.println("\tPrice:\t" + df.format(evt.getNewValue()));
        } else if (propertyName.equalsIgnoreCase("EndCustomerOrder")) {
            // End order, update necessary values
            incrementCustomerOrderPayments(currentCustomerType, currentOrderTotal);
            System.out.println("\tTOTAL:\t" + df.format(currentOrderTotal));
        } else if (propertyName.equalsIgnoreCase("RollOutage")) {
            // Notification of roll outage
            incrementCustomerOutageImpacts(evt.getNewValue().toString());
        }
    }
}
