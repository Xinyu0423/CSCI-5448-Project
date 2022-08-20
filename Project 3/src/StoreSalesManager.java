import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Random;

public class StoreSalesManager {
    private String[] rollTypes;

    private StoreInventory inventory;
    private RollFactory rollFactory;
    private ExtrasFactory extrasFactory;

    // Used for observer messages
    private PropertyChangeSupport support;

    public StoreSalesManager(String[] rolls) {
        this.rollTypes = rolls;

        rollFactory = new RollFactory();
        extrasFactory = new ExtrasFactory();

        support = new PropertyChangeSupport(this);
    }

    // Add an observer to this object
    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    // Remove an observer from this object
    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }

    // Set the inventory
    public void setInventory(StoreInventory inventory) {
        this.inventory = inventory;
    }

    // Set the current day (and notify observers)
    public void setCurrentDay(int day) {
        support.firePropertyChange("NewDay", null, day);
    }

    // Check if inventory is empty (and notify observers)
    public boolean isInventoryEmpty() {
        if (inventory.inventoryEmpty()) {
            support.firePropertyChange("InventoryEmpty", null, null);
            return true;
        }
        return false;
    }

    // Purchase an item, add extras based on customer preference, notify observers of purchase
    public void purchaseItem(Customer customer, String itemName, int quantity) {
        for (int j = 0; j < quantity; j++) {
            inventory.buyItem(itemName);
            int extraFillings = customer.getExtraFillings();
            int extraToppings = customer.getExtraToppings();
            int extraSauces = customer.getExtraSauces();
            Roll boughtRoll = rollFactory.createRoll(itemName);
            boughtRoll = extrasFactory.getFilling(extraFillings, boughtRoll);
            boughtRoll = extrasFactory.getTopping(extraToppings, boughtRoll);
            boughtRoll = extrasFactory.getSauce(extraSauces, boughtRoll);

            support.firePropertyChange("CustomerOrderItem", boughtRoll.getBaseType(), boughtRoll.rollInfo());
            support.firePropertyChange("CustomerOrderPrice", null, boughtRoll.cost());
        }
    }

    // Handle a customer transaction
    public boolean customerTransaction(Customer customer) {
        Random rand = new Random();

        // Get customer order
        int[] order = customer.getOrder(rollTypes);

        // Check if the inventory for the order is available
        boolean inventoryAvailable = true;
        for (int i = 0; i < rollTypes.length; i++) {
            if (inventory.getItemQuantity(rollTypes[i]) < order[i]) {
                inventoryAvailable = false;
            }
        }

        if (inventoryAvailable) {
            // Begin an order if inventory is available, notify observers
            support.firePropertyChange("BeginCustomerOrder", null, customer.getCustomerType());
            // Purchase everything in the order
            for (int i = 0; i < rollTypes.length; i++) {
                purchaseItem(customer, rollTypes[i], order[i]);
            }
            // End of order, notify observers
            support.firePropertyChange("EndCustomerOrder", null, customer.getCustomerType());
            return true;
        } else {
            // If inventory not available, notify observers of outage
            support.firePropertyChange("RollOutage", null, customer.getCustomerType());

            // Get the customer's out of stock behavior
            String customerOOS = customer.getOutOfStockBehavior();

            if (customerOOS.equalsIgnoreCase("Leave")) {
                // "Leave" behaviour, failed order
                support.firePropertyChange("OrderFailed", null, customer.getCustomerType());
                return false;
            } else if (customerOOS.equalsIgnoreCase("FlexibleSingleType")) {
                // "FlexibleSingleType" behavior, casual customer doesn't care about type or quantity
                int amountOrdered = 0;
                // Try to order any available amount of the requested roll type
                for (int i = 0; i < rollTypes.length; i++) {
                    if (order[i] > 0) {
                        amountOrdered = order[i];
                        int thisQuantity = inventory.getItemQuantity(rollTypes[i]);
                        if (thisQuantity > 0) {
                            support.firePropertyChange("BeginCustomerOrder", null, customer.getCustomerType());
                            purchaseItem(customer, rollTypes[i], thisQuantity);
                            support.firePropertyChange("EndCustomerOrder", null, customer.getCustomerType());
                            return true;
                        } else {
                            break;
                        }
                    }
                }

                // Otherwise, pick a random roll, order up to the quantity requested previously (or as much is available)
                while (!inventory.inventoryEmpty()) {
                    String randomRoll = rollTypes[rand.nextInt(rollTypes.length)];
                    int itemQuantity = inventory.getItemQuantity(randomRoll);
                    if (itemQuantity > 0) {
                        purchaseItem(customer, randomRoll, Math.min(itemQuantity, amountOrdered));
                        support.firePropertyChange("EndCustomerOrder", null, customer.getCustomerType());
                        return true;
                    }
                }
            } else if (customerOOS.equalsIgnoreCase("AnyType")) {
                // "AnyType" behavior, catering customer wants any type of roll up to the total amount requested
                int totalRollOrder = 0;
                for (int i = 0; i < rollTypes.length; i++) {
                    totalRollOrder += order[i];
                }

                // Pick any rolls available up to the requested quantity
                if (totalRollOrder <= inventory.getInventoryQuantity()) {
                    support.firePropertyChange("BeginCustomerOrder", null, customer.getCustomerType());
                    for (String rollType : rollTypes) {
                        int itemQuantity = inventory.getItemQuantity(rollType);
                        if (itemQuantity > 0) {
                            if (itemQuantity <= totalRollOrder) {
                                purchaseItem(customer, rollType, itemQuantity);
                                totalRollOrder -= itemQuantity;
                            } else {
                                purchaseItem(customer, rollType, totalRollOrder);
                                support.firePropertyChange("EndCustomerOrder", null, customer.getCustomerType());
                                return true;
                            }
                        }
                    }
                }
            }
        }
        support.firePropertyChange("OrderFailed", null, customer.getCustomerType());
        return false;
    }

}
